package com.jandy.plogging.repository;

import com.jandy.plogging.domain.Review;
import com.jandy.plogging.dto.course.CourseReviewListResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<CourseReviewListResponse> findByCourseId(Long courseId);


}
