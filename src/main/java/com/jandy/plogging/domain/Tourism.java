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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    public Tourism(String name, String description, String address, String phoneNumber, String operatingTime, String category) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.operatingTime = operatingTime;
        this.category = category;
    }

    public void addImage(Image image) {
        images.add(image);
    }

    protected Tourism() {}
}
