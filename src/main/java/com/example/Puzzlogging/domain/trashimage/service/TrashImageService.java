package com.example.Puzzlogging.domain.trashimage.service;

import com.example.Puzzlogging.domain.trashimage.service.dto.CreateTrashImageRequest;
import com.example.Puzzlogging.domain.trashimage.service.dto.TrashImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TrashImageService {

    TrashImageResponse uploadTrashImage(Long memberId, MultipartFile imageFile);

    List<TrashImageResponse> getTrashImageList(Long memberId);

    void createTrashImage(Long memberId, CreateTrashImageRequest request);
}
