package com.jandy.plogging.controller;


import com.jandy.plogging.dto.MypageReviewResponse;
import com.jandy.plogging.dto.review.MyReviewDetailResponse;
import com.jandy.plogging.dto.course.MyCourseListResponse;
import com.jandy.plogging.dto.mypage.MyActivityResponse;
import com.jandy.plogging.service.CourseService;
import com.jandy.plogging.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/mypage")
public class MyPageController {

    private final MyPageService myPageService;
    private final CourseService courseService;

    // 내가 쓴 리뷰 목록 -> 날짜, 장소 이름, 별점, 사진, 평점 내용
    @GetMapping("/{memberId}/review")
    public List<MypageReviewResponse> getMyReviewList(@PathVariable Long memberId) {
        return myPageService.getMyReviewList(memberId);
    }

    // 내가 쓴 리뷰 상세보기
    @GetMapping("/{memberId}/review/{reviewId}")
    public MyReviewDetailResponse getMyReviewDetail(@PathVariable Long memberId, @PathVariable Long reviewId) {
        return myPageService.getMyReviewDetail(memberId, reviewId);
    }

    @GetMapping("/{memberId}/activity")
    public ResponseEntity<MyActivityResponse> getMyActivity(@PathVariable Long memberId) {
        return ResponseEntity.ok()
                .body(myPageService.getMyActivity(memberId));
    }


    @GetMapping("/{memberId}/course")
    public ResponseEntity<MyCourseListResponse> myCourseList(@PathVariable Long memberId) {
        return ResponseEntity.ok()
                .body(courseService.myCourseList(memberId));
    }
}
