package com.example.Puzzlogging.domain.trashimage.entity;

import com.example.Puzzlogging.common.BaseTimeEntity;
import com.example.Puzzlogging.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public TrashImage(String imagePath, String color, Long memberId) {
        this.imagePath = imagePath;
        this.color = color;
        this.memberId = memberId;
    }
}
