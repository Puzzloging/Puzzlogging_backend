package com.example.Puzzlogging.domain.photomosaic.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePhotoMosaicImageRequest {
    private Long memberId;
    private List<String> colors;

}
