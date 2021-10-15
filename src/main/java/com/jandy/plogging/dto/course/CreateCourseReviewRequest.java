package com.jandy.plogging.dto.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateCourseReviewRequest {

    private String email;

    private Long rating;

    private String content;

    private List<MultipartFile> file;


}
