package com.jandy.plogging.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Waypoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    private String latitude;

    private String longitude;

    public Waypoint(Course course, String latitude, String longitude) {
        this.course = course;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Waypoint() {}
}
