package com.jandy.plogging.domain;

import javax.persistence.*;

@Entity
public class Waypoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    private String latitude;

    private String longitude;
}
