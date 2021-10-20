package com.jandy.plogging.dto.course;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CourseReviewListResponse {

    private Long review_id;

    private String member_name;

    private LocalDateTime create_time;

    private Long rating;

    private List<byte[]> images;

    private String content;
}
