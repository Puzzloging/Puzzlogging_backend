package com.example.Puzzlogging.domain.trashimage.service;

import com.example.Puzzlogging.domain.trashimage.entity.TrashImage;
import com.example.Puzzlogging.domain.trashimage.repository.TrashImageRepository;
import com.example.Puzzlogging.domain.trashimage.service.dto.CreateTrashImageRequest;
import com.example.Puzzlogging.utils.awsS3.AwsS3;
import com.example.Puzzlogging.domain.trashimage.service.dto.TrashImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrashImageServiceImpl implements TrashImageService {

    private final AwsS3 awsS3;

    private final TrashImageRepository trashImageRepository;

    @Override
    public TrashImageResponse uploadTrashImage(Long memberId, MultipartFile imageFile) {

        String filePath = awsS3.upload(imageFile, memberId + "/trashImage/" + UUID.randomUUID() + ".jpg");

        return TrashImageResponse.of(filePath, "");
    }

    @Override
    public List<TrashImageResponse> getTrashImageList(Long memberId) {
        return trashImageRepository.findAllByMemberId(memberId).stream()
                .map(trashImage -> TrashImageResponse.of(trashImage.getImagePath(), trashImage.getColor()))
                .collect(Collectors.toList());
    }

    @Override
    public void createTrashImage(Long memberId, CreateTrashImageRequest request) {
        trashImageRepository.save(new TrashImage(request.getFilePath(), request.getColor(), memberId));
    }
}
