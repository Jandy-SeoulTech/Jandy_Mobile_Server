package com.jandy.plogging.service;

import com.jandy.plogging.domain.*;
import com.jandy.plogging.dto.course.CourseReviewListResponse;
import com.jandy.plogging.dto.course.CreateCourseReviewRequest;
import com.jandy.plogging.dto.course.UpdateCourseReviewRequest;
import com.jandy.plogging.repository.CourseRepository;
import com.jandy.plogging.repository.MemberRepository;
import com.jandy.plogging.repository.ReviewRepository;
import com.jandy.plogging.repository.TourismRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final TourismRepository tourismRepository;
    private final MemberRepository memberRepository;

    public List<CourseReviewListResponse> showReview(Long tourismId){
        return reviewRepository.findByTourism_Id(tourismId);
    }

    public Long saveReview(List<Image> images, Long tourismId, CreateCourseReviewRequest request){

        Optional<Tourism> tourism = tourismRepository.findById(tourismId);
        Optional<Member> member = memberRepository.findById(request.getMemberId());

        if (tourism.isPresent() && member.isPresent()) {
            Review review = Review.builder()
                    .tourism(tourism.get())
                    .content(request.getContent())
                    .rating(request.getRating())
                    .images(images)
                    .member(member.get())
                    .build();

            reviewRepository.save(review);

            return review.getId();
        } else {
            return -1L;
        }

    }


}
