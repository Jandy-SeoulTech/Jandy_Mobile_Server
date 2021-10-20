package com.jandy.plogging.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Image extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uploadImageName;

    private String storeImageName;


    public Image(String uploadImageName, String storeImageName) {
        this.uploadImageName = uploadImageName;
        this.storeImageName = storeImageName;
    }

    protected Image() {}
}
