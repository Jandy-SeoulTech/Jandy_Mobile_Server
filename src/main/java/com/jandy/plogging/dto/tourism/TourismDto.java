package com.jandy.plogging.dto.tourism;

import com.jandy.plogging.domain.Tourism;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TourismDto {

    private Long id;

    private String name;

    private String description;

    private String address;

    private String phoneNumber;

    private String operatingTime;

    private String homepage;

    public static TourismDto from(Tourism tourism) {
        return new TourismDto(tourism.getId(), tourism.getName(), tourism.getDescription(), tourism.getAddress(), tourism.getPhoneNumber(), tourism.getHomepage(), tourism.getOperatingTime());
    }
}
