package com.jandy.plogging.repository;


import com.jandy.plogging.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {



}
