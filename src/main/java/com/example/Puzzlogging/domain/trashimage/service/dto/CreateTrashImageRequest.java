package com.example.Puzzlogging.domain.trashimage.service.dto;

import com.example.Puzzlogging.domain.trashimage.entity.TrashImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateTrashImageRequest {

    private Long memberId;
    private String color;

    public TrashImage toEntity(String imagePath, String imageKey, String imageName) {
        return TrashImage.newInstance(imagePath, imageKey, imageName, color, memberId);
    }
}
