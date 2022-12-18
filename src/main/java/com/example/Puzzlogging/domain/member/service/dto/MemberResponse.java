package com.example.Puzzlogging.domain.member.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberResponse {

    private Long id;
    private String name;

    @Builder
    public MemberResponse(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
