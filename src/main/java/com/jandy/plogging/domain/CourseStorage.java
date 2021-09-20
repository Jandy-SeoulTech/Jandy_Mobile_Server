package com.jandy.plogging.domain;


import com.jandy.plogging.dto.courseStorageSaveRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class CourseStorage extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long courseId;

    private String title;

    private Long trashCan;

    private Long trashStatus;

    private Long memberId;

    @Builder
    public CourseStorage(Long courseId, String title, Long trashCan, Long trashStatus, Long memberId) {
        this.courseId = courseId;
        this.title = title;
        this.trashCan = trashCan;
        this.trashStatus = trashStatus;
        this.memberId = memberId;
    }


}
