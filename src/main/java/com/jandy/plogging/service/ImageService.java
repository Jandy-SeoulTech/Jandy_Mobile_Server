package com.jandy.plogging.service;

import com.jandy.plogging.domain.Image;
import com.jandy.plogging.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${fileDir}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    @Transactional
    public List<Image> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<Name> collect = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()) {
                String originalFilename = multipartFile.getOriginalFilename();
                String storeFileName = createStoreFileName(originalFilename);
                multipartFile.transferTo(new File(getFullPath(storeFileName)));
                Name name = new Name(originalFilename, storeFileName);
                collect.add(name);
            }
        }

        List<Image> images = collect.stream()
                .map((name) -> new Image(name.getOriginalFilename(), name.getStoreFileName()))
                .collect(Collectors.toList());
        imageRepository.saveAll(images);

        return images;
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    @Data
    @AllArgsConstructor
    private static class Name {
        private String originalFilename;
        private String storeFileName;
    }

}
