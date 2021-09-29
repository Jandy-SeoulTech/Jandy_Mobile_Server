package com.jandy.plogging.service;

import com.jandy.plogging.domain.Member;
import com.jandy.plogging.domain.Tourism;
import com.jandy.plogging.domain.WishList;
import com.jandy.plogging.dto.tourism.TourismDto;
import com.jandy.plogging.dto.wishList.WishListDto;
import com.jandy.plogging.dto.wishList.WishListReadResponse;
import com.jandy.plogging.repository.MemberRepository;
import com.jandy.plogging.repository.TourismRepository;
import com.jandy.plogging.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository wishListRepository;
    private final TourismRepository tourismRepository;
    private final MemberRepository memberRepository;

    public void addWishList() {

    }

    public WishListReadResponse readWishList(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member member = memberOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        List<WishList> wishLists = wishListRepository.findWishListsByMember(member);

        List<Long> tourismIds = wishLists.stream()
                .map((wishList) -> wishList.getTourism().getId())
                .collect(Collectors.toList());

        List<Tourism> tourismList = tourismRepository.findTourismsByIds(tourismIds);

        List<TourismDto> tourismDtoList = tourismList.stream()
                .map(this::createTourismDto)
                .collect(Collectors.toList());

        List<WishListDto> collect = null;
        for (WishList wishList : wishLists) {
            WishListDto wishListDto = new WishListDto();
            wishListDto.setId(wishList.getId());
//            tourismList.stream().map((tourism) -> tourism.getId().equals(wishList.getTourism().getId()));
//            for(TourismDto tourism : tourismDtoList) {
//                if(wishList.getTourism().getId().equals(tourism.getId())) {
//                    wishListDto.setTourism(tourism);
//                }
//            }
            Optional<TourismDto> tourismDtoOptional = tourismDtoList.stream()
                    .filter(tourismDto -> wishList.getTourism().getId().equals(tourismDto.getId()))
                    .findFirst();

            TourismDto tourism = tourismDtoOptional.get();
            wishListDto.setTourism(tourism);
            collect.add(wishListDto);
        }

        return new WishListReadResponse(collect.size(), collect);
    }

    public void deleteWishList(Long memberId) {

    }

    private TourismDto createTourismDto(Tourism tourism) {
        return new TourismDto(tourism.getId(), tourism.getName(), tourism.getDescription(), tourism.getAddress(), tourism.getPhoneNumber(), tourism.getOperatingTime(), tourism.getReview());
    }

}
