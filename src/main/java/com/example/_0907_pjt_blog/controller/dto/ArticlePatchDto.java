package com.example._0907_pjt_blog.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ArticlePatchDto {
    private long articleId;
    private String title;
    private String content;

    public void setArticleId(long articleId){
        this.articleId = articleId;
    }

}
