package com.example.Puzzlogging.domain.trashimage.controller;

import com.example.Puzzlogging.common.ApiResponse;
import com.example.Puzzlogging.domain.trashimage.service.TrashImageService;
import com.example.Puzzlogging.domain.trashimage.service.dto.CreateTrashImageRequest;
import com.example.Puzzlogging.domain.trashimage.service.dto.TrashImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrashImageController {

    private final TrashImageService trashImageService;

    @PostMapping("/upload/{memberId}")
    public ApiResponse<TrashImageResponse> uploadTrashImage(@RequestPart MultipartFile imageFile, @PathVariable Long memberId) {
        return ApiResponse.success(trashImageService.uploadTrashImage(memberId, imageFile));
    }

    @PostMapping("/trash/{memberId}")
    public ApiResponse<String> getTrashImageList(@PathVariable Long memberId, @RequestBody CreateTrashImageRequest request) {
        trashImageService.createTrashImage(memberId, request);

        return ApiResponse.success(null);
    }

    @GetMapping("/trash/{memberId}")
    public ApiResponse<List<TrashImageResponse>> getTrashImageList(@PathVariable Long memberId) {
        return ApiResponse.success(trashImageService.getTrashImageList(memberId));
    }

}
