package com.jandy.plogging.service;


import com.jandy.plogging.domain.Course;
import com.jandy.plogging.domain.Member;
import com.jandy.plogging.domain.Waypoint;
import com.jandy.plogging.dto.course.CreateCourseRequest;
import com.jandy.plogging.dto.course.CreateCourseResponse;
import com.jandy.plogging.repository.CourseRepository;
import com.jandy.plogging.repository.MemberRepository;
import com.jandy.plogging.repository.WaypointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final WaypointRepository waypointRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CreateCourseResponse createCourse(CreateCourseRequest request) {

        Optional<Member> memberOptional = memberRepository.findById(request.getMemberId());
        Member member = memberOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        Course course = Course.builder()
                .startX(request.getStartX())
                .startY(request.getStartY())
                .endX(request.getEndX())
                .endY(request.getEndY())
                .estimatedTime(request.getTime())
                .distance(request.getDistance())
                .member(member)
                .build();

        Course savedCourse = courseRepository.save(course);

        List<Waypoint> waypoints = request.getWaypoints()
                .stream()
                .map(waypoint -> new Waypoint(savedCourse, waypoint.getLatitude(), waypoint.getLongitude()))
                .collect(Collectors.toList());

        waypointRepository.saveAll(waypoints);
        return response(savedCourse);
    }

    private CreateCourseResponse response(Course savedCourse) {
        return new CreateCourseResponse(savedCourse.getId(), savedCourse.getStartX(), savedCourse.getStartY(), savedCourse.getEndX(), savedCourse.getEndY(), savedCourse.getEstimatedTime(), savedCourse.getDistance());
    }

}
