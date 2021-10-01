package com.jandy.plogging.dto.wishList;

import lombok.Getter;

import java.util.List;

@Getter
public class DeleteWishListRequest {

    private List<Long> wishListIds;

}
