package com.jandy.plogging.dto.course;


import com.jandy.plogging.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class CourseDto {

    private Long courseId;

    private String startLocation;

    private String endLocation;

    private Integer distance;

    private LocalTime time;

    private LocalDate createdAt;

    public static CourseDto from(Course course) {
        return new CourseDto(course.getId(), course.getStartLocation(), course.getEndLocation(), course.getDistance(), course.getEstimatedTime(),course.getCreatedDate().toLocalDate());
    }

}
