package com.example._0907_pjt_blog.controller.mapper;

import com.example._0907_pjt_blog.controller.dto.ArticlePatchDto;
import com.example._0907_pjt_blog.controller.dto.ArticlePostDto;
import com.example._0907_pjt_blog.controller.dto.ArticleResponseDto;
import com.example._0907_pjt_blog.entity.Article;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    Article articlePatchDtoToArticle(ArticlePatchDto articlePatchDto);
    Article articlePostDtoToArticle(ArticlePostDto articlePostDto);
    //ArticleResponseDto articleToArticleResponseDto(Article article);
    //List<ArticleResponseDto> articleToArticleResponseDtos(List<Article> articles);
}

