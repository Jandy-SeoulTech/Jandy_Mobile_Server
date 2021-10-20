package com.jandy.plogging.dto.course;


import com.jandy.plogging.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseDto {

    private String startLocation;

    private String endLocation;

    private Integer distance;

    private String time;

    public static CourseDto from(Course course) {
        return new CourseDto(course.getStartLocation(), course.getEndLocation(), course.getDistance(), course.getEstimatedTime());
    }

}