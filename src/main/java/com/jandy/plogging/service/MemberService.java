package com.jandy.plogging.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jandy.plogging.domain.Member;
import com.jandy.plogging.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.h2.util.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;


    public static String googleURL = "https://oauth2.googleapis.com/tokeninfo?id_token=";

    @Transactional
    public Long joinGoogle(String accessToken) {
        String urlString=googleURL+accessToken;

        String line=getGoogleJson(urlString);

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(line);
        String email = jsonObject.get("email").getAsString();
        String name=jsonObject.get("name").getAsString();
        String picture=jsonObject.get("picture").getAsString();

        Optional<Member> member=memberRepository.findByEmail(email);

        if(member.isEmpty()){
            Member newMember=new Member(email,name,picture);
            return newMember.getId();
        }

        return member.get().getId();
    }

    public String getGoogleJson(String urlString){


        try {
            URL url=new URL(urlString);
            URLConnection urlConnection=url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String line = br.readLine();

            return line;

        } catch (IOException e) {
            throw new IllegalArgumentException("url 요청 실패");
        }

    }

}
