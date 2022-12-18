package com.example.Puzzlogging.domain.member.service;

import com.example.Puzzlogging.domain.member.entity.Member;
import com.example.Puzzlogging.domain.member.repository.MemberRepository;
import com.example.Puzzlogging.domain.member.service.dto.MemberRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void savePost(MemberRequest memberDto) {
        memberRepository.save(memberDto.toEntity());
    }

    @Transactional
    @Override
    public List<MemberRequest> getMemberList(Integer pageNum) {
        List<Member> all = memberRepository.findAll();
        List<MemberRequest> memberDtoList = new ArrayList<>();

        for (Member member : all) {
            MemberRequest memberDto = MemberRequest.builder().id(member.getId()).name(member.getName()).build();
            memberDtoList.add(memberDto);
        }

        return memberDtoList;
    }

    @Transactional
    @Override
    public MemberRequest getPost(Long id) {
        Optional<Member> memberWrapper = memberRepository.findById(id);
        Member member = memberWrapper.get();


        return MemberRequest.builder().id(member.getId()).name(member.getName()).build();
    }

    @Transactional
    @Override
    public void deletePost(Long id) {
        memberRepository.deleteById(id);
    }

//    @Transactional
//    @Override
//    public List<MemberRequest> searchPostsById(Long id) {
//        Member member = memberRepository.findById(id).get();
//        List<MemberRequest> List = new ArrayList<>();
//
//
//    }

    @Transactional
    @Override
    public void update(Long id, MemberRequest dto) {
    Optional<Member> byId = memberRepository.findById(id);
    Member member = byId.get();

    member.updateMember(dto.getId(), dto.getName());
    }
}
