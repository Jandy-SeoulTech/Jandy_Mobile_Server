package com.jandy.plogging.dto.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class MyReviewDetailResponse {

    private Long courseId;

    private LocalDate localDate;

    private LocalTime time;

    private String startAddress;

    private String endAddress;

    private List<byte[]> images;

    private Long rating;

    private String content;

    private String tourismName;

    @Builder
    public MyReviewDetailResponse(Long courseId, LocalDate localDate, LocalTime time, String startAddress, String endAddress, List<byte[]> images, Long rating, String content, String tourismName) {
        this.courseId = courseId;
        this.localDate = localDate;
        this.time = time;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.images = images;
        this.rating = rating;
        this.content = content;
        this.tourismName = tourismName;
    }
}
