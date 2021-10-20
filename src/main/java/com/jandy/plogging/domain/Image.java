package com.jandy.plogging.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Image extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeImageName;

    private String uploadImageName;

    public Image(String uploadImageName, String storeImageName) {
        this.uploadImageName = uploadImageName;
        this.storeImageName = storeImageName;
    }

    protected Image() {}
}
