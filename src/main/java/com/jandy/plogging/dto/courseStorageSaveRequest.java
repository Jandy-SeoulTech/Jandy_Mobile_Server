package com.jandy.plogging.dto;

import com.jandy.plogging.domain.CourseStorage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class courseStorageSaveRequest {

    private String title;

    private Long trashCan;

    private Long trashStatus;

    private Long memberId;

    private Long courseId;


    public CourseStorage toEntity() {
        return CourseStorage.builder()
                .title(title)
                .trashStatus(trashStatus)
                .trashCan(trashCan)
                .memberId(memberId)
                .courseId(courseId)
                .build();
    }

}
