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

    public static TrashImageResponse of(String imagePath) {
        return new TrashImageResponse(imagePath);
    }
}
