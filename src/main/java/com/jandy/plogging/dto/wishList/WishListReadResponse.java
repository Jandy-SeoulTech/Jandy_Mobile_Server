package com.jandy.plogging.dto.wishList;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WishListReadResponse<T> {

    private Long count;

    private T data;

}
