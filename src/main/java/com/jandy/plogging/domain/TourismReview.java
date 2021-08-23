package com.jandy.plogging.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class TourismReview extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 관광지 id
    private Long tourismId;

    // 유저 이름
    private String userName;

    // 리뷰 내용
   private String content;


}
