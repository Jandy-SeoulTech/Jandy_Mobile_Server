package com.jandy.plogging.service;


import com.jandy.plogging.domain.Review;
import com.jandy.plogging.dto.MypageReviewResponse;
import com.jandy.plogging.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyPageService {


    private ReviewRepository reviewRepository;


    public List<MypageReviewResponse> getMyReviewList(Long memberId) {
        ArrayList<Review> reviewArrayList = reviewRepository.findByMember_Id(memberId);

        List<MypageReviewResponse> list = new ArrayList<>();

        for (Review review : reviewArrayList) {
//            MypageReviewResponse mypageReviewResponse= MypageReviewResponse.builder()
//                    .content(review.getContent())
//                    .courseId(review.get)
//                    .build()
        }

        return list;
    }


}
