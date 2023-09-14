package com.example._0907_pjt_blog.controller;

import com.example._0907_pjt_blog.controller.dto.ArticleResponseDto;
import com.example._0907_pjt_blog.controller.mapper.ArticleMapper;
import com.example._0907_pjt_blog.entity.Article;
import com.example._0907_pjt_blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleViewController {
    private final ArticleService articleService;
    private final ArticleMapper articleMapper;

    @GetMapping("/")
    public String startPage(){
        return "index";
    }

    //전체 게시글 조회
    @GetMapping("/articles")
    public String getAllArticels(Model model){
        Page<Article> pageArticles = articleService.findAllArticle(0, 10);
        //List<ArticleResponseDto> articles = articleMapper.articleToArticleResponseDtos(pageArticles.getContent());
        List<ArticleResponseDto> articles = ArticleResponseDto.articleToArticleResponseDtos(pageArticles.getContent());
        model.addAttribute("articles", articles);
        return "articleList";
    }

    //게시글 단건 조회
    @GetMapping("/articles/{articleId}")
    public String getArticle(Model model, @PathVariable("articleId") long articleId){
        //ArticleResponseDto articleResponseDto = articleMapper.articleToArticleResponseDto(articleService.findArticle(articleId));
        ArticleResponseDto article = ArticleResponseDto.articleToArticleResponseDto(articleService.findArticle(articleId));
        model.addAttribute("article", article);
        return "eachArticle";
    }

    //작성한 게시글만 조회
    @GetMapping("/articles/only/{memberId}")
    public String getArticle(Model model, @PathVariable("memberId") String memberId){
        List<Article> articles = articleService.findAllArticleByAuthor(memberId);
        List<ArticleResponseDto> articleResDtos = ArticleResponseDto.articleToArticleResponseDtos(articles);
        model.addAttribute("articles", articleResDtos);
        return "articleListAuthorOnly";
    }

    //게시글 편집
    @GetMapping("/articles/{articleId}/edit")
    public String editArticle(Model model, @PathVariable("articleId") long articleId){
        ArticleResponseDto article = ArticleResponseDto.articleToArticleResponseDto(articleService.findArticle(articleId));
        model.addAttribute("article", article);
        return "editArticle";
    }

    //게시글 생성
    @GetMapping("/newArticle")
    public String newArticle(){
        return "newArticle";
    }

}
