package com.jandy.plogging.repository;

import com.jandy.plogging.domain.Tourism;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourismRepository extends JpaRepository<Tourism, Long> {

    @Query("select t from Tourism as t where t.id in :ids")
    List<Tourism> findTourismsByIds(@Param("ids") List<Long> ids);

}
