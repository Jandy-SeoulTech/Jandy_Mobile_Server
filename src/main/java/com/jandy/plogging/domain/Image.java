package com.jandy.plogging.domain;

import javax.persistence.*;

@Entity
public class Image extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private WishList wishList;



}
