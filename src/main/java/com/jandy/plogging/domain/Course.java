package com.jandy.plogging.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startX;

    private String startY;

    private String endX;

    private String endY;

    private String estimatedTime;

    private Integer distance;

    @ManyToOne
    private Member member;
}
