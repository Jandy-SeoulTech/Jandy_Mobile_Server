package com.jandy.plogging.service;

import com.jandy.plogging.domain.CourseReview;
import com.jandy.plogging.dto.course.CourseReviewListResponse;
import com.jandy.plogging.dto.course.CreateCourseReviewRequest;
import com.jandy.plogging.dto.course.UpdateCourseReviewRequest;
import com.jandy.plogging.repository.CourseReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CourseReviewService {

    private final CourseReviewRepository courseReviewRepository;

    public List<CourseReviewListResponse> showReview(Long courseId){
        return courseReviewRepository.findByCourseId(courseId);
    }

    public Long saveReview(Long courseId, CreateCourseReviewRequest request){

        CourseReview courseReview = new CourseReview(courseId, request);

        courseReviewRepository.save(courseReview);

        return courseReview.getId();
    }

    public void delete(Long reviewId){
        Optional<CourseReview> courseReview=courseReviewRepository.findById(reviewId);

        courseReview.ifPresent(courseReviewRepository::delete);
    }

    public void updateReview(Long reviewId, UpdateCourseReviewRequest request){
        Optional<CourseReview> courseReview = courseReviewRepository.findById(reviewId);

        courseReview.ifPresent(review -> {
            review.update(request);
        });


    }
}
