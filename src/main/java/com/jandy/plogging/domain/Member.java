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

    private String userId;

    private String picture;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Member(String name, String email, String picture){
        this.name=name;
        this.email=email;
        this.picture=picture;
    }

    @Builder
    public Member(Long id, String name, String email, String userId, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userId = userId;
    }

    protected Member() {}
}
