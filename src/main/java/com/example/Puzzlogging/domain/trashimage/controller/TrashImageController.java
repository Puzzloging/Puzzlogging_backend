package com.example.Puzzlogging.domain.trashimage.controller;

import com.example.Puzzlogging.common.ApiResponse;
import com.example.Puzzlogging.domain.trashimage.service.TrashImageService;
import com.example.Puzzlogging.domain.trashimage.service.dto.TrashImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class TrashImageController {

    private final TrashImageService trashImageService;

    @PostMapping("/upload")
    public ApiResponse<TrashImageResponse> uploadTrashImage(@RequestPart MultipartFile imageFile) {
        return ApiResponse.success(trashImageService.uploadTrashImage(imageFile));
    }
}
