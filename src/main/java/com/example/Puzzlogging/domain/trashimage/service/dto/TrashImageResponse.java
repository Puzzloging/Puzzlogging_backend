package com.example.Puzzlogging.domain.trashimage.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrashImageResponse {
    private String imagePath;
    private String color;

    public static TrashImageResponse of(String imagePath, String color) {
        return new TrashImageResponse(imagePath, color);
    }
}
