package com.jandy.plogging.dto.course;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCourseResponse {

    private Long courseId;

    private String startX;

    private String startY;

    private String endX;

    private String endY;

    private String time;

    private Integer distance;

}
