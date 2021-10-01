package com.jandy.plogging.service;

import com.jandy.plogging.repository.ImageRepository;
import com.jandy.plogging.repository.TourismRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourismService {


    private final TourismRepository tourismRepository;
    private final ImageRepository imageRepository;

    public void readByCategory() {

    }

}
