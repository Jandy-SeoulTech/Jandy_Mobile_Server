package com.jandy.plogging.dto.course;

import com.jandy.plogging.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCourseResponse {

    private Long courseId;

    private String time;

    private Integer distance;

    public static CreateCourseResponse from(Course course) {
        return new CreateCourseResponse(course.getId(), course.getEstimatedTime(), course.getDistance());
    }

}
