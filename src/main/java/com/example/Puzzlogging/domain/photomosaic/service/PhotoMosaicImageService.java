package com.example.Puzzlogging.domain.photomosaic.service;

import com.example.Puzzlogging.domain.photomosaic.service.dto.PhotoMosaicImageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoMosaicImageService {

    PhotoMosaicImageResponse uploadPhotoMosaicImage();

}
