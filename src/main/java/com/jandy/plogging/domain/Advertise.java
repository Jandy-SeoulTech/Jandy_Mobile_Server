package com.jandy.plogging.domain;

import javax.persistence.*;

@Entity
public class Advertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    private Waypoint waypoint;
}
