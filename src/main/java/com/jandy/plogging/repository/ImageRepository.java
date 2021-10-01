package com.jandy.plogging.repository;

import com.jandy.plogging.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
