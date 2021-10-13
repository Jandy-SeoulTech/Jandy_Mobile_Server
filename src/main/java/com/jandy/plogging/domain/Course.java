package com.jandy.plogging.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Course extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Waypoint> waypoints = new ArrayList<>();

    private String estimatedTime;

    private Integer distance;

    @ManyToOne
    private Member member;

    @Builder
    public Course(String estimatedTime, Integer distance, Member member) {
        this.estimatedTime = estimatedTime;
        this.distance = distance;
        this.member = member;
    }

    protected Course() {}
}
