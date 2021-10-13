package com.jandy.plogging.dto.tourism;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TourismListResponse<T> {

    private int count;

    private T data;

}
