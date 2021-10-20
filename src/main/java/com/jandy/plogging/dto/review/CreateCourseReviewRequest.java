package com.jandy.plogging.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateCourseReviewRequest {

    private Long memberId;

    private Long rating;

    private String content;

    private List<MultipartFile> file;

    private Long courseId;


}
