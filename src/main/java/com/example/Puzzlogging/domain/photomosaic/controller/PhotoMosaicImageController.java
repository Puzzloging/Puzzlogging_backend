package com.example.Puzzlogging.domain.photomosaic.controller;

import com.example.Puzzlogging.common.ApiResponse;
import com.example.Puzzlogging.common.exception.BaseException;
import com.example.Puzzlogging.common.exception.type.ErrorCode;
import com.example.Puzzlogging.domain.photomosaic.entity.PhotoMosaicImage;
import com.example.Puzzlogging.domain.photomosaic.service.PhotoMosaicImageService;
import com.example.Puzzlogging.domain.photomosaic.service.dto.CreatePhotoMosaicImageRequest;
import com.example.Puzzlogging.domain.photomosaic.service.dto.PhotoMosaicImageResponse;
import com.example.Puzzlogging.domain.trashimage.service.dto.TrashImageResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhotoMosaicImageController {

    private final PhotoMosaicImageService photoMosaicImageService;

    @Operation(summary = "모자이크 사진 생성", description = "memebrId를 PathValue로 받아 생성한다.")
    @PostMapping("/photo-mosaic/{memberId}")
    public ApiResponse<PhotoMosaicImageResponse> uploadPhotoMosaicImage(
            @RequestPart MultipartFile image,
            @RequestPart CreatePhotoMosaicImageRequest request) throws IOException {

        if (image == null) {
            throw new BaseException(ErrorCode.INVALID);
        }

        PhotoMosaicImage photoMosaicImage = photoMosaicImageService.uploadPhotoMosaicImage(request, image);
        PhotoMosaicImageResponse response = photoMosaicImageService.addPhotoMosaicImage(photoMosaicImage);

        return ApiResponse.success(response);
    }

    @Operation(summary = "모자이크 사진 조회", description = "memebrId를 PathValue로 받아 조회한다.")
    @GetMapping("/photo-mosaic/{memberId}")
    public ApiResponse<List<PhotoMosaicImageResponse>> getTrashImageList(@PathVariable Long memberId) {
        return ApiResponse.success(photoMosaicImageService.getPhotoMosaicImageList(memberId));
    }
}
