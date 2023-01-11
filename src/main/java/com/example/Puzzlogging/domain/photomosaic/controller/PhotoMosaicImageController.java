package com.example.Puzzlogging.domain.photomosaic.controller;

import com.example.Puzzlogging.common.ApiResponse;
import com.example.Puzzlogging.domain.photomosaic.entity.PhotoMosaicImage;
import com.example.Puzzlogging.domain.photomosaic.service.PhotoMosaicImageService;
import com.example.Puzzlogging.domain.photomosaic.service.dto.PhotoMosaicImageResponse;
import com.example.Puzzlogging.domain.trashimage.service.dto.TrashImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhotoMosaicImageController {

    private final PhotoMosaicImageService photoMosaicImageService;

    @PostMapping("/photo-mosaic/{memberId}")
    public ApiResponse<PhotoMosaicImageResponse> uploadPhotoMosaicImage(@PathVariable Long memberId) {
        PhotoMosaicImage photoMosaicImage = photoMosaicImageService.uploadPhotoMosaicImage(memberId);
        PhotoMosaicImageResponse response = photoMosaicImageService.addPhotoMosaicImage(photoMosaicImage);

        return ApiResponse.success(response);
    }

    @GetMapping("/photo-mosaic/{memberId}")
    public ApiResponse<List<PhotoMosaicImageResponse>> getTrashImageList(@PathVariable Long memberId) {
        return ApiResponse.success(photoMosaicImageService.getPhotoMosaicImageList(memberId));
    }
}
