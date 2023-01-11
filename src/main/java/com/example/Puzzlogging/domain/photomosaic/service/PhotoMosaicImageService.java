package com.example.Puzzlogging.domain.photomosaic.service;

import com.example.Puzzlogging.domain.photomosaic.entity.PhotoMosaicImage;
import com.example.Puzzlogging.domain.photomosaic.service.dto.PhotoMosaicImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoMosaicImageService {

    PhotoMosaicImage uploadPhotoMosaicImage(Long memberId);

    List<PhotoMosaicImageResponse> getPhotoMosaicImageList(Long memberId);

    PhotoMosaicImageResponse addPhotoMosaicImage(PhotoMosaicImage photoMosaicImage);
}
