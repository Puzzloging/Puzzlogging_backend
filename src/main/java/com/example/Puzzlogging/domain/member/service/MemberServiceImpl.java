package com.example.Puzzlogging.domain.member.service;

import com.example.Puzzlogging.common.exception.BaseException;
import com.example.Puzzlogging.common.exception.type.ErrorCode;
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

        System.out.println(request.getName());
        Member member = memberRepository.findByName(request.getName()).orElseThrow(
                () -> new BaseException(ErrorCode.NOTFOUND_USER)
        );

        return LoginResponse.of(member);
    }
}
