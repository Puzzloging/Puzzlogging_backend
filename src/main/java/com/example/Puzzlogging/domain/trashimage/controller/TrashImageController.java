package com.example.Puzzlogging.domain.trashimage.controller;

import com.example.Puzzlogging.common.ApiResponse;
import com.example.Puzzlogging.common.exception.BaseException;
import com.example.Puzzlogging.common.exception.type.ErrorCode;
import com.example.Puzzlogging.domain.trashimage.entity.TrashImage;
import com.example.Puzzlogging.domain.trashimage.service.TrashImageService;
import com.example.Puzzlogging.domain.trashimage.service.dto.CreateTrashImageRequest;
import com.example.Puzzlogging.domain.trashimage.service.dto.TrashImageResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrashImageController {

    private final TrashImageService trashImageService;

    @Operation(summary = "쓰리게 사진 업로드", description = "MultipartFile 하나와 memebrId를 받아 업로드한다.")
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<TrashImageResponse> uploadTrashImage(
            @RequestPart MultipartFile image,
            CreateTrashImageRequest request) {

        if (image == null) {
            throw new BaseException(ErrorCode.INVALID);
        }

        TrashImage trashImage = trashImageService.uploadTrashImage(request, image);
        TrashImageResponse responses = trashImageService.addTrashImage(trashImage);

        return ApiResponse.success(responses);
    }

    @Operation(summary = "쓰리게 사진 조회", description = "memebrId를 PathValue로 받아 조회한다.")
    @GetMapping("/trash/{memberId}")
    public ApiResponse<List<TrashImageResponse>> getTrashImageList(@PathVariable Long memberId) {
        return ApiResponse.success(trashImageService.getTrashImageList(memberId));
    }

}
