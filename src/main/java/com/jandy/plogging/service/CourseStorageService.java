package com.jandy.plogging.service;

import com.jandy.plogging.domain.CourseStorage;
import com.jandy.plogging.dto.courseStorageSaveRequest;
import com.jandy.plogging.repository.CourseStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CourseStorageService {

    private final CourseStorageRepository courseStorageRepository;

    @Transactional
    public Long save(courseStorageSaveRequest request) {

        CourseStorage courseStorage = request.toEntity();


        courseStorageRepository.save(courseStorage);

        return courseStorage.getId();
    }
}
