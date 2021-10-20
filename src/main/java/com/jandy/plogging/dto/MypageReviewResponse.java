package com.jandy.plogging.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@NoArgsConstructor
public class MypageReviewResponse {

    private LocalDateTime createdAt;

    private String touismName;

    private Long rating;

    private String content;

    private List<byte[]> images;

    private Long courseId;

    @Builder
    public MypageReviewResponse(LocalDateTime createdAt, String tourismName, Long rating, String content, List<byte[]> images, Long courseId) {
        this.createdAt = createdAt;
        this.touismName = tourismName;
        this.rating = rating;
        this.content = content;
        this.images = images;
        this.courseId = courseId;
    }
}
