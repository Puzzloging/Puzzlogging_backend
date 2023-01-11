package com.example.Puzzlogging.domain.trashimage.service.dto;

import com.example.Puzzlogging.domain.trashimage.entity.TrashImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrashImageResponse {
    private Long imageId;
    private String imagePath;
    private String color;

    public static TrashImageResponse of(TrashImage trashImage) {
        return new TrashImageResponse(trashImage.getId(), trashImage.getImagePath(), trashImage.getColor());
    }
}
