package com.jandy.plogging.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String host;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate applicationStartDate;

    private LocalDate applicationEndDate;

    private String location;

    private String cost;

    private String description;

    private String eventLocation;

    private String eventAddress;

    private String homepage;

    @OneToMany
    private List<Image> images;

}
