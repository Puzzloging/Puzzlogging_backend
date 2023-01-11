package com.example.Puzzlogging.domain.trashimage.service;

import com.example.Puzzlogging.domain.trashimage.entity.TrashImage;
import com.example.Puzzlogging.domain.trashimage.service.dto.CreateTrashImageRequest;
import com.example.Puzzlogging.domain.trashimage.service.dto.TrashImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TrashImageService {

    TrashImage uploadTrashImage(CreateTrashImageRequest request, MultipartFile image);

    List<TrashImageResponse> getTrashImageList(Long memberId);

    TrashImageResponse addTrashImage(TrashImage trashImages);
}
