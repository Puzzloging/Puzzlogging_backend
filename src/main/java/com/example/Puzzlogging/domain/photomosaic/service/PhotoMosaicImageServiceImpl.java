package com.example.Puzzlogging.domain.photomosaic.service;

import com.example.Puzzlogging.domain.photomosaic.entity.PhotoMosaicImage;
import com.example.Puzzlogging.domain.photomosaic.repository.PhotoMosaicImageRepository;
import com.example.Puzzlogging.utils.awsS3.AwsS3;
import com.example.Puzzlogging.domain.photomosaic.service.dto.PhotoMosaicImageResponse;
import com.example.Puzzlogging.utils.photomosaic_generator.PhotoMosaicGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoMosaicImageServiceImpl implements PhotoMosaicImageService {

    private final AwsS3 awsS3;

    private final PhotoMosaicGenerator generator;
    private final PhotoMosaicImageRepository photoMosaicImageRepository;

    @Override
    public PhotoMosaicImage uploadPhotoMosaicImage(Long memberId) {
        File file = generator.generatePhotoMosaic();
        String imagePath = awsS3.upload(file, "1/photoMosaicImage/" + UUID.randomUUID() + ".jpg");

        return PhotoMosaicImage.newInstance(imagePath, memberId);
    }

    @Override
    public List<PhotoMosaicImageResponse> getPhotoMosaicImageList(Long memberId) {
        return photoMosaicImageRepository.findByMemberId(memberId).stream()
                .map(photoMosaicImage -> PhotoMosaicImageResponse.of(photoMosaicImage.getImagePath()))
                .collect(Collectors.toList());
    }

    @Override
    public PhotoMosaicImageResponse addPhotoMosaicImage(PhotoMosaicImage photoMosaicImage) {
        photoMosaicImageRepository.save(photoMosaicImage);
        return PhotoMosaicImageResponse.of(photoMosaicImage.getImagePath());
    }


}
