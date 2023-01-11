package com.example.Puzzlogging.domain.member.service;

import com.example.Puzzlogging.domain.member.entity.Member;
import com.example.Puzzlogging.domain.member.repository.MemberRepository;
import com.example.Puzzlogging.domain.member.service.dto.LoginRequest;
import com.example.Puzzlogging.domain.member.service.dto.LoginResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    @Override
    public LoginResponse login(LoginRequest request) {
        return LoginResponse.of(memberRepository.findByName(request.getName()));
    }
}
