package com.jandy.plogging.controller;


import com.jandy.plogging.dto.course.CreateCourseRequest;
import com.jandy.plogging.dto.course.CreateCourseResponse;
import com.jandy.plogging.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CreateCourseResponse> createCourse(CreateCourseRequest request) {
        return ResponseEntity.ok()
                .body(courseService.createCourse(request));
    }


}
