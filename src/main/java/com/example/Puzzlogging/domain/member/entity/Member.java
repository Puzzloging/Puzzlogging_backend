package com.example.Puzzlogging.domain.member.entity;

import com.example.Puzzlogging.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Builder
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void updateMember(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
