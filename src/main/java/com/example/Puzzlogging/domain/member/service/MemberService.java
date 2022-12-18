package com.example.Puzzlogging.domain.member.service;

import com.example.Puzzlogging.domain.member.service.dto.MemberRequest;

import java.util.List;

public interface MemberService {

    void savePost(MemberRequest memberDto);

    List<MemberRequest> getMemberList(Integer pageNum);

    MemberRequest getPost(Long id);

    void deletePost(Long id);

//    List<MemberRequest> searchPostsById(Long id);

    void update(Long id, MemberRequest dto);
}
