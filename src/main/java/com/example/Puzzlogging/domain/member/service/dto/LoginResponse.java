package com.example.Puzzlogging.domain.member.service.dto;

import com.example.Puzzlogging.domain.member.entity.Member;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private Long id;
    private String name;

    public static LoginResponse of(Member member) {
        return new LoginResponse(member.getId(), member.getName());
    }
}
