package com.jandy.plogging.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class WishList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tourism tourism;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}
