package com.jandy.plogging.controller;

import com.jandy.plogging.domain.Image;
import com.jandy.plogging.dto.review.CreateCourseReviewRequest;
import com.jandy.plogging.dto.review.CourseReviewListResponse;
import com.jandy.plogging.dto.review.OtherReviewResponse;
import com.jandy.plogging.service.ImageService;
import com.jandy.plogging.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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


    // 관광지 리뷰 별점
    @GetMapping("/review/{locationId}/rating")
    public Long getRating(@PathVariable String locationId){
        Long id = Long.parseLong(locationId);
        return reviewService.totalRating(id);
    }

    // 다른 사람 관광지 리뷰 자세히 보기
    @GetMapping("/review/{locationId}/{reviewId}")
    public OtherReviewResponse getOtherReview(@PathVariable String locationId, @PathVariable String reviewId) {
        Long id = Long.parseLong(reviewId);
        return reviewService.getOtherReview(id);
    }




    @Data
    @AllArgsConstructor
    static class UpdateCourseReviewResponse {
        private Long courseReviewId;
    }

    @Data
    @AllArgsConstructor
    static class CreateCourseReviewResponse{

        private Long courseReviewId;
    }
}
