package com.jandy.plogging.service;


import com.jandy.plogging.domain.Member;
import com.jandy.plogging.dto.LocalMemberRegisterRequestDto;
import com.jandy.plogging.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocalMemberService {

    private final MemberRepository memberRepository;

    public Long register(LocalMemberRegisterRequestDto requestDto) {
        Member member = requestDto.toEntity();

        duplicate(member.getUserId());

        memberRepository.save(member);

        return member.getId();
    }

    public void duplicate(String userId){
        memberRepository.findByUserId(userId).ifPresent(
                m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                }
        );
    }
}
