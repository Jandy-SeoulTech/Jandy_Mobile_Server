package com.jandy.plogging.domain;


import javax.persistence.*;

@Entity
public class CourseStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Course courseId;

    private String review;

    @ManyToOne
    private Member memberId;
}
