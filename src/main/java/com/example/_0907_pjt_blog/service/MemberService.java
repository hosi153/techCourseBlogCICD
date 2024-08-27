package com.example._0907_pjt_blog.service;

import com.example._0907_pjt_blog.entity.Member;
import com.example._0907_pjt_blog.exception.BusinessLogicException;
import com.example._0907_pjt_blog.exception.ExceptionCode;
import com.example._0907_pjt_blog.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
        memberRepository.save(Member.builder().loginId("guest").loginPwd("1").name("게스트").build());
        memberRepository.save(Member.builder().loginId("jy").loginPwd("jy").name("박지영").build());
        memberRepository.save(Member.builder().loginId("jy1").loginPwd("jy1").name("박지영1").build());
    }

    //가입되어 있지 않으면 오류 반환
    public Member findVerifiedMember(String loginId){
        Member findMember;
        if(loginId.equals("guest")) findMember = findGuestMember();
        else {
            Optional<Member> optionalMember = memberRepository.findByLoginId(loginId);
            findMember = optionalMember.orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        }

        return findMember;
    }

    public Member findMemberByLoginId(String loginId) {
        Optional<Member> optionalMember = memberRepository.findByLoginId(loginId);
        Member findMember = optionalMember.orElseGet(()->findGuestMember());

        return findMember;
    }

    private Member findGuestMember() {
        Optional<Member> optionalMember = memberRepository.findById(1L);
        Member findMember = optionalMember.orElseGet(Member::new);
        return findMember;
    }

    public Member createMember(Member member) {
        verifyExistLoginId(member.getLoginId());
        return memberRepository.save(member);
    }

    private void verifyExistLoginId(String loginId) {
        Optional<Member> member = memberRepository.findByLoginId(loginId);
        if(member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
