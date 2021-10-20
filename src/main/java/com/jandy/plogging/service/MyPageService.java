package com.jandy.plogging.service;


import com.jandy.plogging.domain.Course;
import com.jandy.plogging.domain.Member;
import com.jandy.plogging.domain.Review;
import com.jandy.plogging.dto.MypageReviewResponse;
import com.jandy.plogging.dto.mypage.MyActivityResponse;
import com.jandy.plogging.repository.CourseRepository;
import com.jandy.plogging.repository.MemberRepository;
import com.jandy.plogging.repository.ReviewRepository;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyPageService {


    private final ReviewRepository reviewRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    public List<MypageReviewResponse> getMyReviewList(Long memberId) {
        ArrayList<Review> reviewArrayList = reviewRepository.findByMember_Id(memberId);

        List<MypageReviewResponse> list = new ArrayList<>();

        for (Review review : reviewArrayList) {
//            MypageReviewResponse mypageReviewResponse= MypageReviewResponse.builder()
//                    .content(review.getContent())
//                    .courseId(review.get)
//                    .build()
        }

        return list;
    }

    public MyActivityResponse getMyActivity(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member member = memberOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
        List<Course> courseList = courseRepository.findAllByMember(member);
        Integer distance = 0;
        LocalTime time = LocalTime.parse("00:00:00");
        for (Course course : courseList) {
            distance += course.getDistance();
            LocalTime temp = course.getEstimatedTime();
            int hour = temp.getHour();
            int minute = temp.getMinute();
            int second = temp.getSecond();
            time = time.plusHours(hour);
            time = time.plusMinutes(minute);
            time = time.plusSeconds(second);
        }

        return new MyActivityResponse(courseList.size(), distance, time);
    }


}
