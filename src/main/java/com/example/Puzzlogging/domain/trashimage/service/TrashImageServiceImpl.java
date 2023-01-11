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
    public TrashImage uploadTrashImage(CreateTrashImageRequest request, MultipartFile image) {
        return request.toEntity(awsS3.upload(image, request.getMemberId() + "/trashImage/" + UUID.randomUUID() + ".jpg"));
    }

    @Override
    public List<TrashImageResponse> getTrashImageList(Long memberId) {
        return trashImageRepository.findAllByMemberId(memberId).stream()
                .map(TrashImageResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public TrashImageResponse addTrashImage(TrashImage trashImages) {
        return TrashImageResponse.of(trashImageRepository.save(trashImages));
    }

}
