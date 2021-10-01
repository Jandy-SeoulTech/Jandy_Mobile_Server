package com.jandy.plogging.controller;


import com.jandy.plogging.dto.wishList.AddWishListRequest;
import com.jandy.plogging.dto.wishList.DeleteWishListRequest;
import com.jandy.plogging.dto.wishList.WishListAddResponse;
import com.jandy.plogging.dto.wishList.WishListReadResponse;
import com.jandy.plogging.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    @PostMapping("/{memberId}")
    public ResponseEntity<WishListAddResponse> addWishList(@RequestBody AddWishListRequest request, @PathVariable Long memberId) {
        return ResponseEntity.ok()
                .body(wishListService.addWishList(request, memberId));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<WishListReadResponse> readAllWishList(@PathVariable Long memberId) {
        return ResponseEntity.ok()
                .body(wishListService.readWishList(memberId));
    }

    @PostMapping("/delete/{memberId}")
    public ResponseEntity deleteWishList(@RequestBody DeleteWishListRequest request, @PathVariable Long memberId) {

        wishListService.deleteWishList(request, memberId);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }



}
