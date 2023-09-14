package com.example._0907_pjt_blog.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
@AllArgsConstructor @Builder
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Member extends BasicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String loginId;
    private String loginPwd;

    @OneToMany(mappedBy = "member")
    private List<Article> articles = new ArrayList<>();

//    public Member(String guest) {
//        this.name = "임의생성_게스트";
//        this.loginId = "guest0";
//        this.loginPwd = "0";
//    }

    public void setArticle(Article article) {
        articles.add(article);
        if (article.getMember() != this) {
            article.setMember(this);
        }
    }

    public String getName() {

        return Optional.ofNullable(name).orElse("탈퇴한 사용자");
    }
}
