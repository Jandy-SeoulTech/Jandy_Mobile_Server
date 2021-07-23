package com.jandy.plogging.domain;


import javax.persistence.*;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course courseId;

}
