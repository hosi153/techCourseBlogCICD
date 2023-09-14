package com.example._0907_pjt_blog.controller;

import com.example._0907_pjt_blog.controller.dto.ArticleResponseDto;
import com.example._0907_pjt_blog.controller.dto.MemberPostDto;
import com.example._0907_pjt_blog.controller.dto.MemberResponseDto;
import com.example._0907_pjt_blog.entity.Article;
import com.example._0907_pjt_blog.entity.Member;
import com.example._0907_pjt_blog.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/member/{loginId}")
    public ResponseEntity loginMember(@PathVariable("loginId") String loginId){
        System.out.println("GET LoginID : "+loginId);
        Member loginMember = memberService.findVerifiedMember(loginId);
        return new ResponseEntity(MemberResponseDto.builder().loginId(loginMember.getLoginId()).name(loginMember.getName()).build(), HttpStatus.OK);
    }

    @PostMapping("/member/new")
    public ResponseEntity postMember(@RequestBody MemberPostDto memberPostDto){
        Member member = MemberPostDto.memberPostDtoToMember(memberPostDto);
        memberService.createMember(member);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
