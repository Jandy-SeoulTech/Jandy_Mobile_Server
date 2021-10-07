package com.jandy.plogging.domain;

import com.jandy.plogging.dto.course.CreateCourseReviewRequest;
import com.jandy.plogging.dto.course.UpdateCourseReviewRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Review extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_name")
    private Member member;

    private Long rating;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    @OneToMany(mappedBy = "image")
    private List<Image> imageList;

    private String content;

    @Builder
    public Review(Member member, Long rating, Course course, List<Image> images, String content) {
        this.member = member;
        this.rating = rating;
        this.course = course;
        this.imageList = images;
        this.content = content;
    }
}
