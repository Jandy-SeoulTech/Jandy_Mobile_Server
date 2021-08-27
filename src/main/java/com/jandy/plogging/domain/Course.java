package com.jandy.plogging.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startX;

    private String startY;

    private String endX;

    private String endY;

    private String estimatedTime;

    private Integer distance;

    @ManyToOne
    private Member member;

    @Builder
    public Course(String startX, String startY, String endX, String endY, String estimatedTime, Integer distance, Member member) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.estimatedTime = estimatedTime;
        this.distance = distance;
        this.member = member;
    }

    protected Course() {}
}
