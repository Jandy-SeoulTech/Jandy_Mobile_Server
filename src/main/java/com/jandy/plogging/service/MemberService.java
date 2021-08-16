package com.jandy.plogging.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.jandy.plogging.domain.Member;
import com.jandy.plogging.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;


    public static String CLIENT_ID="test";

    public Long joinGoogle(String accessToken) {

        HttpTransport httpTransport=new NetHttpTransport();
        JsonFactory jsonFactory=new JacksonFactory();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        GoogleIdToken idToken = null;

        try {
            idToken = verifier.verify(accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");

            Optional<Member> optionalMember=memberRepository.findByEmail(email);


            if(optionalMember.isPresent()){
                return optionalMember.get().getId();
            }else{
                Member member=new Member(name,email);
                memberRepository.save(member);
                return member.getId();
            }

        } else {
            System.out.println("Invalid ID token.");
        }

        return 0L;
    }


}
