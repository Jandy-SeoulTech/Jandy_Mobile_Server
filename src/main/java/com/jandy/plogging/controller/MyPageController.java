package com.jandy.plogging.controller;


import com.jandy.plogging.dto.MypageReviewResponse;
import com.jandy.plogging.service.MyPageService;
import lombok.RequiredArgsConstructor;
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

    // 내가 쓴 리뷰 목록 -> 날짜, 장소 이름, 별점, 사진, 평점 내용
    @GetMapping("/{userId}")
    public List<MypageReviewResponse> getMyReviewList(@PathVariable String userId) {
        Long id = Long.parseLong(userId);
        return myPageService.getMyReviewList(id);
    }

}
