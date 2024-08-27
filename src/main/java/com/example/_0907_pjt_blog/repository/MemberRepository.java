package com.example._0907_pjt_blog.repository;

import com.example._0907_pjt_blog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String LoginId);
}
