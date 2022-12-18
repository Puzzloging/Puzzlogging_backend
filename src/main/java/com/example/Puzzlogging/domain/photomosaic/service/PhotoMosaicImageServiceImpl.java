package com.example.Puzzlogging.domain.photomosaic.service;

import com.example.Puzzlogging.utils.awsS3.AwsS3;
import com.example.Puzzlogging.domain.photomosaic.service.dto.PhotoMosaicImageResponse;
import com.example.Puzzlogging.utils.photomosaic_generator.PhotoMosaicGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoMosaicImageServiceImpl implements PhotoMosaicImageService {

    private final AwsS3 awsS3;

    private final PhotoMosaicGenerator generator;

    @Override
    public PhotoMosaicImageResponse uploadPhotoMosaicImage() {
        File file = generator.generatePhotoMosaic();
        return PhotoMosaicImageResponse.of(awsS3.upload(file, "1/trashImage/" + UUID.randomUUID() + ".jpg"));
    }


}
