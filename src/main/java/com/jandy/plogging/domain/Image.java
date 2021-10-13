package com.jandy.plogging.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Image extends BaseTimeEntity {

    @Id
    private String storeImageName;

    private String uploadImageName;

    public Image(String uploadImageName, String storeImageName) {
        this.uploadImageName = uploadImageName;
        this.storeImageName = storeImageName;
    }

    protected Image() {}
}
