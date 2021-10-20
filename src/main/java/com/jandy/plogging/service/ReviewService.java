package com.jandy.plogging.service;

import com.jandy.plogging.domain.*;
import com.jandy.plogging.dto.course.CourseReviewListResponse;
import com.jandy.plogging.dto.course.CreateCourseReviewRequest;
import com.jandy.plogging.dto.course.UpdateCourseReviewRequest;
import com.jandy.plogging.repository.CourseRepository;
import com.jandy.plogging.repository.MemberRepository;
import com.jandy.plogging.repository.ReviewRepository;
import com.jandy.plogging.repository.TourismRepository;
import lombok.RequiredArgsConstructor;
import org.h2.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final TourismRepository tourismRepository;
    private final MemberRepository memberRepository;

    @Value("${fileDir}")
    private String fileDir;

    @Transactional
    public Long totalRating(Long tourismId){
        ArrayList<Review> list = reviewRepository.findByTourism_Id(tourismId);
        Long sum = 0L;

        for (Review review : list) {
            sum += review.getRating();
        }

        return sum / list.size();
    }

    @Transactional
    public ArrayList<CourseReviewListResponse> showReview(Long tourismId){
        ArrayList<Review> list = reviewRepository.findByTourism_Id(tourismId);
        ArrayList<CourseReviewListResponse> ret = new ArrayList<>();
        for (Review review : list) {
            CourseReviewListResponse make = new CourseReviewListResponse();

            make.setContent(review.getContent());
            make.setReview_id(review.getId());
            make.setCreate_time(review.getCreatedDate());

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
                make.setImages(image_list);
            }catch(IOException e){
                e.printStackTrace();
            }


            make.setRating(review.getRating());
            make.setMember_name(review.getMember().getName());

            ret.add(make);
        }

        return ret;
    }

    @Transactional
    public Long saveReview(List<Image> images, Long tourismId, CreateCourseReviewRequest request){

        Optional<Tourism> tourism = tourismRepository.findById(tourismId);
        Optional<Member> member = memberRepository.findById(request.getMemberId());

        if (tourism.isPresent() && member.isPresent()) {
            Review review = Review.builder()
                    .tourism(tourism.get())
                    .content(request.getContent())
                    .rating(request.getRating())
                    .images(images)
                    .member(member.get())
                    .build();

            reviewRepository.save(review);

            return review.getId();
        } else {
            return -1L;
        }

    }


}
