package com.example.Puzzlogging.domain.member;

import com.example.Puzzlogging.domain.member.entity.Member;
import com.example.Puzzlogging.domain.member.repository.MemberRepository;
import com.example.Puzzlogging.domain.member.service.MemberServiceImpl;
import com.example.Puzzlogging.domain.member.service.dto.MemberRequest;
import com.example.Puzzlogging.domain.member.service.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberServiceImpl memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/api/post")
    public MemberResponse savePost(@RequestBody MemberRequest request){
        memberService.savePost(request);

        return new MemberResponse(request.toEntity().getId(), request.toEntity().getName());

    }

    @PutMapping("/api/post/{id}")
    public MemberResponse updatePost(@PathVariable("id") Long id, @RequestBody MemberRequest request){
        memberService.update(id, request);
        Optional<Member> findPost = memberRepository.findById(id);
        Member member = findPost.get();

        return new MemberResponse(member.getId(), member.getName());
    }

    @GetMapping("/api/board/posts")
    public List<MemberRequest> findPosts(){
        List<Member> findAll = memberRepository.findAll();
        List<MemberRequest> allPost = new ArrayList<>();

        for(Member member : findAll){
            MemberRequest build = MemberRequest.builder().id(member.getId()).name(member.getName()).build();

            allPost.add(build);
        }

        return allPost;
    }

    @GetMapping("/api/board/post/{id}")
    public MemberResponse findPost(@PathVariable("id") Long id){
        MemberRequest post = memberService.getPost(id);

        return new MemberResponse(post.getId(), post.getName());
    }

    @DeleteMapping("/api/post/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        memberService.deletePost(id);
    }
}
