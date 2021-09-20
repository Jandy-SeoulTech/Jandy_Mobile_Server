package com.jandy.plogging.repository;

import com.jandy.plogging.domain.CourseStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStorageRepository extends JpaRepository<CourseStorage,Long> {
}
