package com.example.Puzzlogging.domain.trashimage.service;

import com.example.Puzzlogging.domain.trashimage.service.dto.TrashImageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface TrashImageService {

    TrashImageResponse uploadTrashImage(MultipartFile imageFile);

}
