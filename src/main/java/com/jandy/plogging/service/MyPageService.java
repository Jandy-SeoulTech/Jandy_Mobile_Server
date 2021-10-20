package com.jandy.plogging.service;


import com.jandy.plogging.domain.Course;
import com.jandy.plogging.domain.Image;
import com.jandy.plogging.domain.Member;
import com.jandy.plogging.domain.Review;
import com.jandy.plogging.dto.MypageReviewResponse;
import com.jandy.plogging.dto.review.MyReviewDetailResponse;
import com.jandy.plogging.dto.mypage.MyActivityResponse;
import com.jandy.plogging.repository.CourseRepository;
import com.jandy.plogging.repository.MemberRepository;
import com.jandy.plogging.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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

    @Value("${file.dir}")
    private String fileDir;


    @Transactional
    public MyReviewDetailResponse getMyReviewDetail(Long memberId, Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        MyReviewDetailResponse myReviewDetailResponse = null;

        if (reviewOptional.isPresent()) {

            Review review = reviewOptional.get();
            Course course = review.getCourse();

             myReviewDetailResponse= myReviewDetailResponse.builder()
                    .content(review.getContent())
                    .localDate(review.getCreatedDate().toLocalDate())
                    .endAddress(course.getEndLocation())
                    .startAddress(course.getStartLocation())
                    .time(course.getEstimatedTime())
                    .tourismName(review.getTourism().getName())
                    .images(getImageList(review))
                    .rating(review.getRating()).build();

        }

        return myReviewDetailResponse;
    }


    @Transactional
    public List<MypageReviewResponse> getMyReviewList(Long memberId) {
        ArrayList<Review> reviewArrayList = reviewRepository.findByMember_Id(memberId);

        List<MypageReviewResponse> list = new ArrayList<>();

        for (Review review : reviewArrayList) {

            System.out.println(review.getId());
            MypageReviewResponse mypageReviewResponse = MypageReviewResponse.builder()
                    .content(review.getContent())
                    .createdAt(review.getCreatedDate().toLocalDate())
                    .rating(review.getRating())
                    .tourismName(review.getTourism().getName())
                    .images(getImageList(review))
                    .build();

            list.add(mypageReviewResponse);
        }

        return list;
    }


    public List<byte[]> getImageList(Review review) {
        List<byte[]> image_list = new ArrayList<>();
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (Image image : review.getImageList()) {


                BufferedImage original = ImageIO.read(new File(fileDir + image.getStoreImageName()));
                ImageIO.write(original, "png", baos);
                baos.flush();
                image_list.add(baos.toByteArray());


            }
            baos.close();

        }catch(IOException e){
            e.printStackTrace();
        }

        return image_list;
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
