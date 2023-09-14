package com.example._0907_pjt_blog.controller.dto;

import com.example._0907_pjt_blog.entity.Article;
import com.example._0907_pjt_blog.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //test용
public class MemberPostDto {
    private String loginId;
    private String loginPwd;
    private String name;

    public static Member memberPostDtoToMember(MemberPostDto memberPostDto){
        if(memberPostDto == null) return null;
        else{
            Member.MemberBuilder member = Member.builder();
            member.name(memberPostDto.getName());
            member.loginId(memberPostDto.getLoginId());
            member.loginPwd(memberPostDto.getLoginPwd());
            return member.build();
        }
    }
}
