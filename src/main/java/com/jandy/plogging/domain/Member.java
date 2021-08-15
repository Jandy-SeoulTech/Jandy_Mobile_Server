package com.jandy.plogging.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String profileImage;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Member(String name, String email, String profileImage, LocalDateTime createdAt) {
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
        this.createdAt = createdAt;
    }

    protected Member() {}
}
