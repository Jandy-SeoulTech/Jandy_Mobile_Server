//package com.jandy.plogging.controller;
//
//import com.jandy.plogging.dto.course.CreateCourseReviewRequest;
//import com.jandy.plogging.dto.course.CourseReviewListResponse;
//import com.jandy.plogging.dto.course.UpdateCourseReviewRequest;
//import com.jandy.plogging.service.CourseReviewService;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/v1")
//public class CourseReviewController {
//
//    private final CourseReviewService courseReviewService;
//
//    // 코스 리뷰 리스트
//    @GetMapping("/{courseId}/review")
//    public List<CourseReviewListResponse> showReview(@PathVariable Long courseId){
//        return courseReviewService.showReview(courseId);
//    }
//
//
//    // 코스 리뷰 입력
//    @PostMapping("/{courseId}/review")
//    public CreateCourseReviewResponse inputReview(@PathVariable Long courseId, @RequestBody CreateCourseReviewRequest request){
//
//        Long id=courseReviewService.saveReview(courseId,request);
//
//        return new CreateCourseReviewResponse(id);
//    }
//
//    // 코스 리뷰 수정
//    @PutMapping("/{courseId}/review/{reviewId}")
//    public UpdateCourseReviewResponse updateReview(@PathVariable Long courseId, @PathVariable Long reviewId, @RequestBody UpdateCourseReviewRequest request){
//
//        courseReviewService.updateReview(reviewId, request);
//
//        return new UpdateCourseReviewResponse(reviewId);
//
//    }
//
//    // 코스 리뷰 삭제
//    @DeleteMapping("/{courseId}/review/{reviewId}")
//    public void deleteReview(@PathVariable Long courseId, @PathVariable Long reviewId){
//        courseReviewService.delete(reviewId);
//    }
//
//    @Data
//    @AllArgsConstructor
//    static class UpdateCourseReviewResponse{
//        private Long courseReviewId;
//    }
//
//    @Data
//    @AllArgsConstructor
//    static class CreateCourseReviewResponse{
//
//        private Long courseReviewId;
//    }
//}
