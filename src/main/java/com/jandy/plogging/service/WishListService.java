package com.jandy.plogging.service;

import com.jandy.plogging.domain.Member;
import com.jandy.plogging.domain.Tourism;
import com.jandy.plogging.domain.WishList;
import com.jandy.plogging.dto.tourism.TourismDto;
import com.jandy.plogging.dto.wishList.*;
import com.jandy.plogging.repository.MemberRepository;
import com.jandy.plogging.repository.TourismRepository;
import com.jandy.plogging.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository wishListRepository;
    private final TourismRepository tourismRepository;
    private final MemberRepository memberRepository;

    public WishListAddResponse addWishList(AddWishListRequest request, Long memberId) {

        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member member = memberOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        Optional<Tourism> tourismOptional = tourismRepository.findById(request.getTourismId());
        Tourism tourism = tourismOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 관광지입니다."));

        WishList wishList = new WishList(tourism, member);

        WishList savedWishList = wishListRepository.save(wishList);

        return new WishListAddResponse(savedWishList.getId());
    }

    @Transactional
    public WishListReadResponse readWishList(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member member = memberOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        List<WishList> wishLists = wishListRepository.findWishListsByMember(member);

        List<Long> tourismIds = wishLists.stream()
                .map((wishList) -> wishList.getTourism().getId())
                .collect(Collectors.toList());

        List<Tourism> tourismList = tourismRepository.findAllById(tourismIds);

        List<TourismDto> tourismDtoList = tourismList.stream()
                .map(TourismDto::from)
                .collect(Collectors.toList());

        List<WishListDto> collect = new ArrayList<>();

        for (WishList wishList : wishLists) {
            WishListDto wishListDto = new WishListDto();
            wishListDto.setId(wishList.getId());

            Optional<TourismDto> tourismDtoOptional = tourismDtoList.stream()
                    .filter(tourismDto -> wishList.getTourism().getId().equals(tourismDto.getId()))
                    .findFirst();

            TourismDto tourism = tourismDtoOptional.get();
            wishListDto.setTourism(tourism);
            collect.add(wishListDto);
        }
        Long count = collect.stream().count();

        return new WishListReadResponse(count, collect);
    }

    public void deleteWishList(DeleteWishListRequest request, Long memberId) {

        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member member = memberOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다"));
        wishListRepository.deleteAllByIdInQuery(request.getWishListIds(), member);

    }

}
