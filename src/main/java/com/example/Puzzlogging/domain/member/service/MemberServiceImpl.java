package com.example.Puzzlogging.domain.member.service;

import com.example.Puzzlogging.domain.member.entity.Member;
import com.example.Puzzlogging.domain.member.repository.MemberRepository;
import com.example.Puzzlogging.domain.member.service.dto.LoginRequest;
import com.example.Puzzlogging.domain.member.service.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    @Override
    public LoginResponse login(LoginRequest request) {
        return LoginResponse.of(memberRepository.findByName(request.getName()));
    }
}
