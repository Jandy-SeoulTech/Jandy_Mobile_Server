package com.jandy.plogging.controller;

import com.jandy.plogging.dto.MemberOAuthResponse;
import com.jandy.plogging.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/oauthKakao")
    public ResponseEntity<MemberOAuthResponse> kakaoLogin(HttpServletRequest request, HttpServletResponse response) {
        String access_token = request.getHeader("Authorization");
        Long memberId = memberService.kakaoApi(access_token);
        Cookie cookie = new Cookie("memberId", String.valueOf(memberId));
        response.addCookie(cookie);
        return ResponseEntity.ok()
                .body(new MemberOAuthResponse(memberId));
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}