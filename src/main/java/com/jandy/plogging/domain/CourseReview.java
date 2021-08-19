package com.jandy.plogging.domain;

import com.jandy.plogging.dto.course.CreateCourseReviewRequest;
import com.jandy.plogging.dto.course.UpdateCourseReviewRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Setter
@Getter
public class CourseReview extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long courseId;

    private String userName;

    private String content;

    public CourseReview(Long courseId,CreateCourseReviewRequest request){
        this.content = request.getContent();
        this.courseId = courseId;
    }

    public void update(UpdateCourseReviewRequest request){
        this.content = request.getContent();
    }

}
