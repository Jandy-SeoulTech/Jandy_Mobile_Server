package com.jandy.plogging.dto.course;


import com.jandy.plogging.dto.waypoint.WaypointDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateCourseRequest {

    private String startX;

    private String startY;

    private String endX;

    private String endY;

    private String time;

    private Integer distance;

    private Long memberId;

    private List<WaypointDto> waypoints;

}
