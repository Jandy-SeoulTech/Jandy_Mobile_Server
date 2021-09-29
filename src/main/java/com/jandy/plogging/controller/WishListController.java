package com.jandy.plogging.controller;


import com.jandy.plogging.dto.wishList.WishListReadResponse;
import com.jandy.plogging.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    @GetMapping("/{memberId}")
    public ResponseEntity<WishListReadResponse> readAllWishList(Long memberId) {
        return ResponseEntity.ok()
                .body(wishListService.readWishList(memberId));

    }



}
