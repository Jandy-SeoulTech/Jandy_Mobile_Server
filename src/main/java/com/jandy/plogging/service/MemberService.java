package com.jandy.plogging.service;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jandy.plogging.domain.Member;
import com.jandy.plogging.repository.MemberRepository;
import com.jandy.plogging.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long kakaoApi(String accessToken) {
        String requestURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer" + accessToken);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            String result = "";

            while((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            JsonObject profile = kakaoAccount.getAsJsonObject().get("profile").getAsJsonObject();

            String nickname = profile.getAsJsonObject().get("nickname").getAsString();
            String email = kakaoAccount.getAsJsonObject().get("email").getAsString();

            Optional<Member> findMember = memberRepository.findMemberByEmail(email);

            if(findMember.isEmpty()) {
                Member member = Member.builder()
                        .name(nickname)
                        .email(email)
                        .build();

                Member savedMember = memberRepository.save(member);
                return savedMember.getId();
            }
            return findMember.get().getId();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
