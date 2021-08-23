package com.jandy.plogging.domain;


import javax.persistence.*;

@Entity
public class CourseStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Course course;

    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
