package com.jandy.plogging.dto.tourism;

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
}
