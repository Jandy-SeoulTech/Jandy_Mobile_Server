package com.jandy.plogging.repository;

import com.jandy.plogging.domain.CourseReview;
import com.jandy.plogging.dto.course.CourseReviewListResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {

    List<CourseReviewListResponse> findByCourseId(Long courseId);


}
