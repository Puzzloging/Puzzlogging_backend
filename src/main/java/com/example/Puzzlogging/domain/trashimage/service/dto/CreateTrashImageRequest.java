package com.example.Puzzlogging.domain.trashimage.service.dto;

import com.example.Puzzlogging.domain.trashimage.entity.TrashImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateTrashImageRequest {

    private Long memberId;
    private String color;

    public TrashImage toEntity(String imagePath) {
        return TrashImage.newInstance(imagePath, color, memberId);
    }
}
