package com.jandy.plogging.service;

import com.jandy.plogging.domain.Image;
import com.jandy.plogging.domain.Review;
import com.jandy.plogging.domain.Tourism;
import com.jandy.plogging.dto.ListResponse;
import com.jandy.plogging.dto.tourism.TourismCategoryDto;
import com.jandy.plogging.dto.tourism.TourismListResponse;
import com.jandy.plogging.dto.tourism.TourismNameDto;
import com.jandy.plogging.dto.tourism.TourismOneResponse;
import com.jandy.plogging.repository.ImageRepository;
import com.jandy.plogging.repository.ReviewRepository;
import com.jandy.plogging.repository.TourismRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class TourismService {


    private final TourismRepository tourismRepository;
    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final ImageService imageService;


    public void createTourism(MultipartHttpServletRequest servletRequest) throws IOException {

        MultipartFile file = servletRequest.getFile("file");
        Image image = imageService.storeFile(file);

        String name = servletRequest.getParameter("name");
        String description = servletRequest.getParameter("description");
        String address = servletRequest.getParameter("address");
        String phoneNumber = servletRequest.getParameter("phoneNumber");
        String operatingTime = servletRequest.getParameter("operatingTime");
        String category = servletRequest.getParameter("category");

        Tourism tourism = new Tourism(name, description, address, phoneNumber, operatingTime, category);
        tourismRepository.save(tourism);

    }

    public TourismListResponse readByCategory(String category) {

        List<Tourism> tourismList = tourismRepository.findTourismsByCategory(category);
        List<TourismCategoryDto> collect = tourismList.stream()
                .map(tourism -> new TourismCategoryDto(tourism.getId(), tourism.getName(), tourism.getDescription(), tourism.getImage().getStoreImageName(),reviewService.totalRating(tourism.getId()),reviewRepository.findByTourism_Id(tourism.getId()).size()))
                .collect(toList());

        return new TourismListResponse(collect.size(), collect);
    }

    public TourismOneResponse readOneById(Long id) {

        Optional<Tourism> tourismOptional = tourismRepository.findById(id);
        Tourism tourism = tourismOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 관광지 입니다."));

        return TourismOneResponse.from(tourism);
    }

    public ListResponse getTourismByName(String name) {

        List<Tourism> tourismList = tourismRepository.findTourismsByName(name);
        List<TourismNameDto> collect = tourismList.stream()
                .map(tourism -> new TourismNameDto(tourism.getId(), tourism.getName(), tourism.getDescription(), tourism.getImage().getStoreImageName(), reviewService.totalRating(tourism.getId()), reviewRepository.findByTourism_Id(tourism.getId()).size()))
                .collect(toList());

        return new ListResponse(collect.size(), collect);
    }

}
