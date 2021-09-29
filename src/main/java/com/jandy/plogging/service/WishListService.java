package com.jandy.plogging.service;

import com.jandy.plogging.domain.Member;
import com.jandy.plogging.domain.WishList;
import com.jandy.plogging.dto.wishList.WishListReadResponse;
import com.jandy.plogging.repository.MemberRepository;
import com.jandy.plogging.repository.TourismRepository;
import com.jandy.plogging.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository wishListRepository;
    private final TourismRepository tourismRepository;
    private final MemberRepository memberRepository;

    public void addWishList() {

    }

    public WishListReadResponse<List<WishList>> readWishList(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member member = memberOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        List<WishList> wishLists = wishListRepository.findWishListsByMember(member);

        return new WishListReadResponse<>(wishLists.size(), wishLists);
    }

}
