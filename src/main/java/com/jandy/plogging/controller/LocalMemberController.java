package com.jandy.plogging.controller;

import com.jandy.plogging.dto.LocalMemberRegisterRequestDto;
import com.jandy.plogging.service.LocalMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LocalMemberController {

    private final LocalMemberService localMemberService;

    @PostMapping("/api/v1/localmembers/register")
    public Long LocalRegister(@RequestBody LocalMemberRegisterRequestDto requestDto) {
        return localMemberService.register(requestDto);
    }


}
