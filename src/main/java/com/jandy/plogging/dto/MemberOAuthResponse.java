package com.jandy.plogging.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberOAuthResponse {

    private Long id;

    private String name;

    private String email;

    private String profileImage;
}
