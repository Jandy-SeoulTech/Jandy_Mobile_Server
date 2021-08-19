package com.jandy.plogging.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TourismReviewController {

    // 관광지 리뷰 확인
    @GetMapping("/{tourismId}/review")
    public static void showReview(){

    }


    // 관광지 리뷰 입력
    @PostMapping("/{tourismId}/review")
    public static void inputReview(){

    }

    // 관광지 리뷰 수정
    @PutMapping("/{tourismId}/review/{reviewId}")
    public static void updateReview(){

    }

    // 관광지 리뷰 삭제
    @DeleteMapping("/{tourismId}/review/{reviewId}")
    public static void deleteReview(){

    }

}
