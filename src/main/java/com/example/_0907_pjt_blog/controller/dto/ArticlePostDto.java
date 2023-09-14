package com.example._0907_pjt_blog.controller.dto;

import com.example._0907_pjt_blog.entity.Article;
import com.example._0907_pjt_blog.entity.Member;
import com.example._0907_pjt_blog.service.MemberService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value="게시글 생성 요청 dto")
@Getter
@AllArgsConstructor //테스트용
public class ArticlePostDto {
    @ApiModelProperty(value="게시글 title", example = "제목1", required = true)
    private String title;
    private String content;
    @Setter
    private String loginId;

    public Member getMember(){
        Member member = new Member();
        member.setLoginId(loginId);
        return member;
    }

    public static Article articlePostDtoToArticle(ArticlePostDto articlePostDto) {
        if (articlePostDto == null) {
            return null;
        } else {
            Article.ArticleBuilder article = Article.builder();
            article.title(articlePostDto.getTitle());
            article.content(articlePostDto.getContent());
            article.member(articlePostDto.getMember());
            return article.build();
        }
    }


}
