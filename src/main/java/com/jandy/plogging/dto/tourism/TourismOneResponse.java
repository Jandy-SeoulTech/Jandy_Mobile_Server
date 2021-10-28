package com.jandy.plogging.dto.tourism;


import com.jandy.plogging.domain.Image;
import com.jandy.plogging.domain.Tourism;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@AllArgsConstructor
public class TourismOneResponse {

    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String operatingTime;

    private String category;

    private String homepage;

    private String thumbnailImage;

    public static TourismOneResponse from(Tourism tourism) {
        return new TourismOneResponse(tourism.getId(), tourism.getName(), tourism.getAddress(), tourism.getPhoneNumber(), tourism.getOperatingTime(), tourism.getCategory(), tourism.getHomepage() ,tourism.getImage().getStoreImageName());
    }
}
