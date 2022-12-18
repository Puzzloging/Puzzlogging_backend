package com.example.Puzzlogging.domain.photomosaic.controller;

import com.example.Puzzlogging.common.ApiResponse;
import com.example.Puzzlogging.domain.photomosaic.service.PhotoMosaicImageService;
import com.example.Puzzlogging.domain.photomosaic.service.dto.PhotoMosaicImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class PhotoMosaicImageController {

    private final PhotoMosaicImageService photoMosaicImageService;

    @GetMapping("/generate")
    public ApiResponse<PhotoMosaicImageResponse> uploadPhotoMosaicImage() {
        return ApiResponse.success(photoMosaicImageService.uploadPhotoMosaicImage());
    }
}
