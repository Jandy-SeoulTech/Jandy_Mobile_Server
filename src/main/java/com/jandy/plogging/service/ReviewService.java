package com.jandy.plogging.service;

import com.jandy.plogging.domain.Course;
import com.jandy.plogging.domain.Image;
import com.jandy.plogging.domain.Member;
import com.jandy.plogging.domain.Review;
import com.jandy.plogging.dto.course.CourseReviewListResponse;
import com.jandy.plogging.dto.course.CreateCourseReviewRequest;
import com.jandy.plogging.dto.course.UpdateCourseReviewRequest;
import com.jandy.plogging.repository.CourseRepository;
import com.jandy.plogging.repository.MemberRepository;
import com.jandy.plogging.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    public List<CourseReviewListResponse> showReview(Long courseId){
        return reviewRepository.findByCourseId(courseId);
    }

    public Long saveReview(List<Image> images, Long courseId, CreateCourseReviewRequest request){

        Optional<Course> course = courseRepository.findById(courseId);
        Optional<Member> member = memberRepository.findMemberByEmail(request.getEmail());

        if (course.isPresent() && member.isPresent()) {
            Review review = Review.builder()
                    .course(course.get())
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
