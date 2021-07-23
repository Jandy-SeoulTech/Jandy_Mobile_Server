package com.jandy.plogging.service;

import com.jandy.plogging.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface MemberService {

    void kakaoApiSignUp(String accessToken);

    void localSignUp();
}
