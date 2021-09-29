package com.jandy.plogging.dto.wishList;

import com.jandy.plogging.domain.Tourism;
import com.jandy.plogging.dto.tourism.TourismDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WishListDto {

    private Long id;

    private TourismDto tourism;
}
