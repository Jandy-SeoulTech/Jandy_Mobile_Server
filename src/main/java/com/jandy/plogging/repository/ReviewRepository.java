package com.jandy.plogging.repository;

import com.jandy.plogging.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    ArrayList<Review> findByTourism_Id(Long tourismId);

    ArrayList<Review> findByMember_Id(Long memberId);

}
