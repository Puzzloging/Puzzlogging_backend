package com.example.Puzzlogging.domain.trashimage.entity;

import com.example.Puzzlogging.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class TrashImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagePath;

    private String color;

    private Long memberId;

    @Builder(access = AccessLevel.PACKAGE)
    private TrashImage(String imagePath, String color, Long memberId) {
        this.imagePath = imagePath;
        this.color = color;
        this.memberId = memberId;
    }

    public static TrashImage newInstance(String imagePath, String color, Long memberId) {
        return TrashImage.builder()
                .imagePath(imagePath)
                .color(color)
                .memberId(memberId)
                .build();
    }
}
