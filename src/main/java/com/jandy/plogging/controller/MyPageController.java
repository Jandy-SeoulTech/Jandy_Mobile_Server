package com.jandy.plogging.controller;


import com.jandy.plogging.dto.MypageReviewResponse;
import com.jandy.plogging.dto.course.MyCourseListResponse;
import com.jandy.plogging.dto.mypage.MyActivityResponse;
import com.jandy.plogging.service.CourseService;
import com.jandy.plogging.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/mypage")
@Controller
public class MyPageController {

    private final MyPageService myPageService;
    private final CourseService courseService;

    // 내가 쓴 리뷰 목록 -> 날짜, 장소 이름, 별점, 사진, 평점 내용
    @GetMapping("/{memberId}/review")
    public List<MypageReviewResponse> getMyReviewList(@PathVariable Long memberId) {
        return myPageService.getMyReviewList(memberId);
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
