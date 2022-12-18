package com.example.Puzzlogging.domain.member.service.dto;

import com.example.Puzzlogging.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class MemberRequest {
    private Long id;
    private String name;

    @Builder
    public MemberRequest(Long id, String name){
        this.id = id;
        this.name = name;

    }

    public Member toEntity(){
        return Member.builder().id(this.id).name(this.name).build();
    }
}
