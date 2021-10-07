package com.jandy.plogging.dto.course;

import lombok.Getter;

@Getter
public class CreateCourseReviewRequest {

    private String email;

    private Long rating;

    private String content;

    CreateCourseReviewRequest(String content){

        this.content = content;
    }

}
