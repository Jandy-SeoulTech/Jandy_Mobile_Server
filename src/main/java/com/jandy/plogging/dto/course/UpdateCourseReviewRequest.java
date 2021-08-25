package com.jandy.plogging.dto.course;

import lombok.Getter;

@Getter
public class UpdateCourseReviewRequest {
    private String content;

    UpdateCourseReviewRequest(String content){
        this.content = content;
    }
}
