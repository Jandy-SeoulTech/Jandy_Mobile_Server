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
    @JoinColumn(name="member_id")
    private Member member;

    private Long rating;

    @ManyToOne
    @JoinColumn(name = "tourism_id")
    private Tourism tourism;

    @OneToMany(mappedBy="review")
    private List<Image> imageList;

    private String content;

    @Builder
    public Review(Member member, Long rating, Tourism tourism, List<Image> images, String content) {
        this.member = member;
        this.rating = rating;
        this.tourism = tourism;
        this.imageList = images;
        this.content = content;
    }
}
