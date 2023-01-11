package com.example.Puzzlogging.domain.photomosaic.entity;

import com.example.Puzzlogging.common.BaseTimeEntity;
import com.example.Puzzlogging.domain.trashimage.entity.TrashImage;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PhotoMosaicImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private String imagePath;

    @Builder(access = AccessLevel.PACKAGE)
    private PhotoMosaicImage(String imagePath, Long memberId) {
        this.imagePath = imagePath;
        this.memberId = memberId;
    }

    public static PhotoMosaicImage newInstance(String imagePath, Long memberId) {
        return PhotoMosaicImage.builder()
                .imagePath(imagePath)
                .memberId(memberId)
                .build();
    }
}
