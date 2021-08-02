package com.jandy.plogging.dto;

import com.jandy.plogging.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class LocalMemberRegisterRequestDto {

    private String name;

    private String email;

    private String userId;

    private String password;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .name(name)
                .userId(userId)
                .password(password)
                .build();
    }
}
