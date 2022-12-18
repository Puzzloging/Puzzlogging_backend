package com.example.Puzzlogging.domain.trashimage.entity;

import com.example.Puzzlogging.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity

@Getter
@NoArgsConstructor
public class TrashImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalImageName;

    private String imageFullPath;

    private Long imageSize;
}
