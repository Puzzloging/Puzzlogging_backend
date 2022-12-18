package com.example.Puzzlogging.domain.photomosaic.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoMosaicImageResponse {
    private String imagePath;

    public static PhotoMosaicImageResponse of(String imagePath) {
        return new PhotoMosaicImageResponse(imagePath);
    }
}
