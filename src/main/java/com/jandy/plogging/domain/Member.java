package com.jandy.plogging.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String userId;

    private String password;

    @OneToMany
    private List<Course> activityCourse;

    @OneToMany
    private List<Course> favoriteCourse;

    @CreatedDate
    private LocalDateTime createdAt;
}
