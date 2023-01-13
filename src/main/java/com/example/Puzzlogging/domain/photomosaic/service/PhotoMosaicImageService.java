package com.example.Puzzlogging.domain.photomosaic.service;

import com.example.Puzzlogging.domain.photomosaic.entity.PhotoMosaicImage;
import com.example.Puzzlogging.domain.photomosaic.service.dto.CreatePhotoMosaicImageRequest;
import com.example.Puzzlogging.domain.photomosaic.service.dto.PhotoMosaicImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoMosaicImageService {

    PhotoMosaicImage uploadPhotoMosaicImage(CreatePhotoMosaicImageRequest request, MultipartFile image) throws IOException;

    List<PhotoMosaicImageResponse> getPhotoMosaicImageList(Long memberId);

    PhotoMosaicImageResponse addPhotoMosaicImage(PhotoMosaicImage photoMosaicImage);
}
