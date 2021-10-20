package com.jandy.plogging.dto.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class OtherReviewResponse {

    private LocalDate localDate;

    private LocalTime time;

    private String startAddress;

    private String endAddress;

    private List<byte[]> images;

    private Long rating;

    private String content;

    private String userName;

    @Builder
    public OtherReviewResponse(LocalDate localDate, LocalTime time, String startAddress, String endAddress, List<byte[]> images, Long rating, String content, String userName) {
        this.localDate = localDate;
        this.time = time;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.images = images;
        this.rating = rating;
        this.content = content;
        this.userName = userName;
    }

}
