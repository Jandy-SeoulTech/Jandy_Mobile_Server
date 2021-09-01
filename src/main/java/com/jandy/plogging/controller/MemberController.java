package com.jandy.plogging.controller;

import com.jandy.plogging.dto.CreateGoogleMemberResponse;
import com.jandy.plogging.dto.MemberOAuthResponse;
import com.jandy.plogging.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Api(tags = "회원")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "id 로그인")
    @GetMapping("/login/{id}")
    public ResponseEntity<MemberOAuthResponse> loginMember(@PathVariable Long id) {
        return ResponseEntity.ok().body(memberService.loginById(id));
    }

    // 구글 로그인
    @ApiOperation(value = "구글 로그인")
    @PostMapping("/google")
    public ResponseEntity<MemberOAuthResponse> loginGoogleMember(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String accessToken=httpServletRequest.getHeader("Authorization");
        MemberOAuthResponse memberOAuthResponse=memberService.joinGoogle(accessToken);

        Cookie cookie=new Cookie("memberId",String.valueOf(memberOAuthResponse.getId()));
        httpServletResponse.addCookie(cookie);

        return new ResponseEntity<>(memberOAuthResponse, HttpStatus.ACCEPTED);
    }

    // 구글 로그아웃
    @GetMapping("/google")
    public void logoutGoogleMember(HttpServletResponse httpServletResponse){
        Cookie cookie=new Cookie("memberId",null);
        httpServletResponse.addCookie(cookie);
    }

    @ApiOperation(value = "카카오 로그인")
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

    @ApiOperation(value = "네이버 로그인")
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

