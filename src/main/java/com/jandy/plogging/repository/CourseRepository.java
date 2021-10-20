package com.jandy.plogging.repository;

import com.jandy.plogging.domain.Course;
import com.jandy.plogging.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByMember(Member member);

}
