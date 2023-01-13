package com.example.Puzzlogging.domain.member.controller;

import com.example.Puzzlogging.common.ApiResponse;
import com.example.Puzzlogging.domain.member.entity.Member;
import com.example.Puzzlogging.domain.member.service.MemberServiceImpl;
import com.example.Puzzlogging.domain.member.service.dto.LoginRequest;
import com.example.Puzzlogging.domain.member.service.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request){
        return ApiResponse.success(memberService.login(request));
    }
}
