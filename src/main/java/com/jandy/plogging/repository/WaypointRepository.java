package com.jandy.plogging.repository;

import com.jandy.plogging.domain.Waypoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaypointRepository extends JpaRepository<Waypoint, Long> {
}
