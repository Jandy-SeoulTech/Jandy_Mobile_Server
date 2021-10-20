package com.jandy.plogging.dto.course;

import com.jandy.plogging.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class CreateCourseResponse {

    private Long courseId;

    private LocalTime time;

    private Integer distance;

    public static CreateCourseResponse from(Course course) {
        return new CreateCourseResponse(course.getId(), course.getEstimatedTime(), course.getDistance());
    }

}
