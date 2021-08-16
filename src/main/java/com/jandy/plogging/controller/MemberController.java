package com.jandy.plogging.controller;


import com.jandy.plogging.dto.CreateGoogleMemberResponse;
import com.jandy.plogging.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

//    @PostMapping("/")
//    public ResponseEntity<MemberSignUpResponse> signUp() {
//
//
//        return ResponseEntity.ok()
//                .body();
//    }

    // 구글 로그인
    @PostMapping("/google")
    public CreateGoogleMemberResponse saveGoogleMember(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String accessToken=httpServletRequest.getHeader("Authorization");
        Long id=memberService.joinGoogle(accessToken);
        Cookie cookie=new Cookie("memberId",String.valueOf(id));
        httpServletResponse.addCookie(cookie);

        return new CreateGoogleMemberResponse(id);
    }

    // 구글 로그아웃
    @GetMapping("/google")
    public void logoutGoogleMember(HttpServletResponse httpServletResponse){
        Cookie cookie=new Cookie("memberId",null);
        httpServletResponse.addCookie(cookie);
    }
}
