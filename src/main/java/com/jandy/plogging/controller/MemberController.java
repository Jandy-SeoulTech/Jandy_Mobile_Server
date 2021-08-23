package com.jandy.plogging.controller;

import com.jandy.plogging.dto.CreateGoogleMemberResponse;
import com.jandy.plogging.dto.MemberOAuthResponse;
import com.jandy.plogging.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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


    // 구글 로그인
    @PostMapping("/google")
    public ResponseEntity<CreateGoogleMemberResponse> loginGoogleMember(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String accessToken=httpServletRequest.getHeader("Authorization");
        Long id=memberService.joinGoogle(accessToken);

        Cookie cookie=new Cookie("memberId",String.valueOf(id));
        httpServletResponse.addCookie(cookie);

        return new ResponseEntity<>(new CreateGoogleMemberResponse(id), HttpStatus.ACCEPTED);
    }

    // 구글 로그아웃
    @GetMapping("/google")
    public void logoutGoogleMember(HttpServletResponse httpServletResponse){
        Cookie cookie=new Cookie("memberId",null);
        httpServletResponse.addCookie(cookie);
    }

    @GetMapping("/oauthKakao")
    public ResponseEntity<MemberOAuthResponse> kakaoLogin(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        String access_token = servletRequest.getHeader("Authorization");
        MemberOAuthResponse oAuthResponse = memberService.kakaoApi(access_token);
        Long memberId = oAuthResponse.getId();
        Cookie cookie = new Cookie("memberId", String.valueOf(memberId));
        servletResponse.addCookie(cookie);
        return ResponseEntity.ok()
                .body(oAuthResponse);
    }

    @GetMapping("/oauthNaver")
    public ResponseEntity<MemberOAuthResponse> naverLogin(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        String access_token = servletRequest.getHeader("Authorization");
        MemberOAuthResponse oAuthResponse = memberService.naverApi(access_token);
        Long memberId = oAuthResponse.getId();
        Cookie cookie = new Cookie("memberId", String.valueOf(memberId));
        servletResponse.addCookie(cookie);
        return ResponseEntity.ok()
                .body(oAuthResponse);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}

