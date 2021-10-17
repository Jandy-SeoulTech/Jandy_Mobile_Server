package com.jandy.plogging.controller;


import com.jandy.plogging.domain.CourseStorage;
import com.jandy.plogging.dto.courseStorageSaveRequest;
import com.jandy.plogging.service.CourseStorageService;
import io.swagger.annotations.Api;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "코스 활동 기록")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course-storage")
public class CourseStorageController {

    private final CourseStorageService courseStorageService;

    @PostMapping("/")
    public saveResponse save(@RequestBody courseStorageSaveRequest request){
        Long id = courseStorageService.save(request);
        return new saveResponse(id);
    }

    @Data
    static class saveResponse{
        private Long id;

        public saveResponse(Long id) {
            this.id = id;
        }
    }

}
