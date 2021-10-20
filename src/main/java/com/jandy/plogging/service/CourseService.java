package com.jandy.plogging.service;


import com.jandy.plogging.domain.Course;
import com.jandy.plogging.domain.Member;
import com.jandy.plogging.domain.Waypoint;
import com.jandy.plogging.dto.course.CourseDto;
import com.jandy.plogging.dto.course.CreateCourseRequest;
import com.jandy.plogging.dto.course.CreateCourseResponse;
import com.jandy.plogging.dto.course.MyCourseListResponse;
import com.jandy.plogging.repository.CourseRepository;
import com.jandy.plogging.repository.MemberRepository;
import com.jandy.plogging.repository.WaypointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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

        Course course = request.toEntity(member);

        Course savedCourse = courseRepository.save(course);

        List<Waypoint> waypoints = request.getWaypoints()
                .stream()
                .map(waypoint -> new Waypoint(savedCourse, waypoint.getLatitude(), waypoint.getLongitude()))
                .collect(toList());

        waypointRepository.saveAll(waypoints);
        return CreateCourseResponse.from(savedCourse);
    }

    public MyCourseListResponse myCourseList(Long memberId) {

        Optional<Member> memberOptional = memberRepository.findById(memberId);

        Member member = memberOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
        List<Course> courseList = courseRepository.findAllByMember(member);
        List<CourseDto> collect = courseList.stream()
                .map(CourseDto::from)
                .collect(toList());

        return new MyCourseListResponse(collect.size(), collect);
    }
}
