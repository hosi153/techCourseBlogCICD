package com.example._0907_pjt_blog.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article extends BasicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long articleId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }

    public Member getMember(){
        //Member가 null일 때 처리 필요
       return Optional.ofNullable(member).orElseGet(Member::new);

    }


}
