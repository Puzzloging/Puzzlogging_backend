package com.example.Puzzlogging.domain.member.service;

import com.example.Puzzlogging.domain.member.service.dto.LoginRequest;
import com.example.Puzzlogging.domain.member.service.dto.LoginResponse;

public interface MemberService {

    LoginResponse login(LoginRequest request);
}
