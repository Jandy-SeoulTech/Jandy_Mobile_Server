package com.jandy.plogging.repository;

import com.jandy.plogging.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
