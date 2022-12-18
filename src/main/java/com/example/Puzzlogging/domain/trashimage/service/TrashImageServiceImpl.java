package com.example.Puzzlogging.domain.trashimage.service;

import com.example.Puzzlogging.utils.awsS3.AwsS3;
import com.example.Puzzlogging.domain.trashimage.service.dto.TrashImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrashImageServiceImpl implements TrashImageService {

    private final AwsS3 awsS3;

    @Override
    public TrashImageResponse uploadTrashImage(MultipartFile imageFile) {
        return TrashImageResponse.of(awsS3.upload(imageFile, "1/trashImage/" + UUID.randomUUID() + ".jpg"));
    }
}
