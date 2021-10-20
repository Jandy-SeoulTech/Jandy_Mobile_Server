package com.jandy.plogging.dto.course;


import com.jandy.plogging.domain.Course;
import com.jandy.plogging.domain.Member;
import com.jandy.plogging.dto.waypoint.WaypointDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class CreateCourseRequest {

    private String startLocation;

    private String endLocation;

    private LocalTime time;

    private Integer distance;

    private Long memberId;

    private List<WaypointDto> waypoints;
    
    public Course toEntity(Member member) {
        return Course.builder()
                .startLocation(this.startLocation)
                .endLocation(this.endLocation)
                .estimatedTime(this.time)
                .distance(this.distance)
                .member(member)
                .build();
    }

}
