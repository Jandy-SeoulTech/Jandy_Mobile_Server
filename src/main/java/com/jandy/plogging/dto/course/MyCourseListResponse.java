package com.jandy.plogging.dto.course;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyCourseListResponse<T> {

    private Integer count;

    private T data;

}
