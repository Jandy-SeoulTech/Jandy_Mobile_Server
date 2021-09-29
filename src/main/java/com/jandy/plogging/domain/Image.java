package com.jandy.plogging.domain;

import javax.persistence.*;

@Entity
public class Image extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tourism tourism;

    private Member member;



}
