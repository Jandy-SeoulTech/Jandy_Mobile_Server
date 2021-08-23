package com.jandy.plogging.service;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jandy.plogging.domain.Member;
import com.jandy.plogging.dto.MemberOAuthResponse;
import com.jandy.plogging.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public MemberOAuthResponse kakaoApi(String accessToken) {
        String apiUrl = "https://kapi.kakao.com/v2/user/me";
        String responseBody = get(apiUrl, accessToken);

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(responseBody);

        JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
        JsonObject profile = kakaoAccount.getAsJsonObject().get("profile").getAsJsonObject();

        String nickname = profile.getAsJsonObject().get("nickname").getAsString();
        String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
        String imageUrl = profile.getAsJsonObject().get("profile_image_url").getAsString();

        Optional<Member> memberOptional = memberRepository.findMemberByEmail(email);

        if(memberOptional.isEmpty()) {
            Member member = Member.builder()
                    .name(nickname)
                    .email(email)
                    .profileImage(imageUrl)
                    .build();

            Member savedMember = memberRepository.save(member);
            return response(savedMember);
        }

        return response(memberOptional.get());
    }

    @Transactional
    public MemberOAuthResponse naverApi(String accessToken) {
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        String responseBody = get(apiURL, accessToken);

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(responseBody);

        String email = element.getAsJsonObject().get("email").getAsString();
        String name = element.getAsJsonObject().get("name").getAsString();
        String profileImage = element.getAsJsonObject().get("profile_image").getAsString();

        Optional<Member> memberOptional = memberRepository.findMemberByEmail(email);

        if(memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return response(member);
        }

        Member member = Member.builder()
                .name(name)
                .email(email)
                .profileImage(profileImage)
                .build();

        Member savedMember = memberRepository.save(member);
        return response(savedMember);
    }


    private String get(String apiUrl, String accessToken) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();

        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    private MemberOAuthResponse response(Member member) {
        return new MemberOAuthResponse(member.getId(), member.getName(), member.getEmail(), member.getProfileImage());
    }
}
