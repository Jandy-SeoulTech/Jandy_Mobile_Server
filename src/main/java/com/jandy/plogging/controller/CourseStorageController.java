package com.jandy.plogging.controller;


import com.jandy.plogging.domain.CourseStorage;
import com.jandy.plogging.dto.courseStorageSaveRequest;
import com.jandy.plogging.service.CourseStorageService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "코스 활동 기록")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course-storage")
public class CourseStorageController {

    private final CourseStorageService courseStorageService;

    @PostMapping("")
    public Long save(@RequestBody courseStorageSaveRequest request){
        return courseStorageService.save(request);
    }

}
