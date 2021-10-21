package com.jandy.plogging.controller;


import com.jandy.plogging.dto.tourism.TourismListResponse;
import com.jandy.plogging.dto.tourism.TourismOneResponse;
import com.jandy.plogging.service.TourismService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;


@Api(tags = "관광지")
@RestController
@RequestMapping("/api/v1/tourism")
@RequiredArgsConstructor
public class TourismController {

    private final TourismService tourismService;

    @PostMapping
    public ResponseEntity uploadTourism(MultipartHttpServletRequest servletRequest) throws IOException {
        tourismService.createTourism(servletRequest);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/list/{category}")
    public ResponseEntity<TourismListResponse> listByCategory(@PathVariable String category) {
        return ResponseEntity.ok().body(tourismService.readByCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourismOneResponse> readTourism(@PathVariable Long id) {
        return ResponseEntity.ok().body(tourismService.readOneById(id));
    }


}
