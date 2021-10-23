package com.jandy.plogging.dto.tourism;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TourismNameDto {

    private Long id;

    private String name;

    private String description;

    private String imageName;

    private Long rating;

    private Integer ratingNumber;

}
