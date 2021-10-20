package com.jandy.plogging.dto.tourism;

import com.jandy.plogging.domain.Tourism;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TourismCategoryDto {

    private String name;

    private String description;

    private String imageName;

    private Long rating;

    private Integer ratingNumber;

}
