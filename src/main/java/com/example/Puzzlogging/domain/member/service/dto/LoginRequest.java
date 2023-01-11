package com.example.Puzzlogging.domain.member.service.dto;

import com.example.Puzzlogging.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class LoginRequest {
    private String name;
}
