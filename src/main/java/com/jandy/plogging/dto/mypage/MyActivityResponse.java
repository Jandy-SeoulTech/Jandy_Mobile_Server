package com.jandy.plogging.dto.mypage;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class MyActivityResponse {

    private int count;

    private Integer distance;

    private LocalTime time;

}
