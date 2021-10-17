package com.jandy.plogging.controller;

import com.jandy.plogging.domain.Image;
import com.jandy.plogging.dto.course.CreateCourseReviewRequest;
import com.jandy.plogging.dto.course.CourseReviewListResponse;
import com.jandy.plogging.dto.course.UpdateCourseReviewRequest;
import com.jandy.plogging.service.ImageService;
import com.jandy.plogging.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;
    private final ImageService imageService;

    // 코스 리뷰 리스트
    @GetMapping("/review/{locationId}")
    public List<CourseReviewListResponse> showReview(@PathVariable String locationId){
        Long id = Long.parseLong(locationId);
        return reviewService.showReview(id);
    }


    // 관광지 리뷰 입력
    @PostMapping("/review/{locationId}")
    public CreateCourseReviewResponse inputReview(@PathVariable String locationId, CreateCourseReviewRequest request) throws IOException {

        Long id = Long.parseLong(locationId);

        List<Image> images=imageService.storeFiles(request.getFile());

        Long reviewId = reviewService.saveReview(images, id, request);

        return new CreateCourseReviewResponse(reviewId);
    }


    @Data
    @AllArgsConstructor
    static class UpdateCourseReviewResponse{
        private Long courseReviewId;
    }

    @Data
    @AllArgsConstructor
    static class CreateCourseReviewResponse{

        private Long courseReviewId;
    }
}
