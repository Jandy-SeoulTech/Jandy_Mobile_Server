package com.jandy.plogging.domain;


import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Tourism {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String address;

    private String phoneNumber;

    private String operatingTime;

    private String category;

    private String homepage;

    @OneToOne
    private Image image;

    public Tourism(String name, String description, String address, String phoneNumber, String operatingTime, String category, String homepage, Image image) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.operatingTime = operatingTime;
        this.category = category;
        this.homepage = homepage;
        this.image = image;
    }

    protected Tourism() {}
}
