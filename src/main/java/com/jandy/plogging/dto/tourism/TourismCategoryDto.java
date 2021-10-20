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

    public static TourismCategoryDto from(Tourism tourism) {
        return new TourismCategoryDto(tourism.getName(), tourism.getDescription(), tourism.getImage().getStoreImageName());
    }
}
