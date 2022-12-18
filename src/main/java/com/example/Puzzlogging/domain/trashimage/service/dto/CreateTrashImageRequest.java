package com.example.Puzzlogging.domain.trashimage.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateTrashImageRequest {

    private String filePath;
    private String color;
}
