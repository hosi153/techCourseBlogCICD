package com.example._0907_pjt_blog;
import com.example._0907_pjt_blog.controller.dto.ArticlePatchDto;
import com.example._0907_pjt_blog.entity.Article;
import com.google.gson.Gson;
import com.example._0907_pjt_blog.controller.dto.ArticlePostDto;
import com.example._0907_pjt_blog.controller.mapper.ArticleMapper;
import com.example._0907_pjt_blog.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest
public class ArticleApiControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @MockBean
    private ArticleService articleService;
    @Autowired
    private ArticleMapper mapper;



    @DisplayName("CONTROLLER : article 신규생성 테스트")
    @Test
    //게시글 생성 dto-컨트롤러 테스트
    void postArticleTest() throws Exception {
        //given 테스트용 req body 생성
        ArticlePostDto post = new ArticlePostDto("id","pwd","name");
        //given(articleService.createArticle(mapper.articlePostDtoToArticle(post)));
        given(articleService.createArticle(Mockito.any(Article.class))).willReturn(mapper.articlePostDtoToArticle(post));
        //MockService(가칭)의 create() 메서드가 리턴할 Stub 데이터

        //when MockMvc 객체로 테스트 대상 controller 호출
        String content = gson.toJson(post);
        ResultActions actions = mockMvc.perform(
                post("/api/new")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );
        //then Controller 핸들러 메서드에서 응답으로 수신한 HTTP Status 및 response body 검증
        actions.andExpect(status().isCreated());
    }

    void updateArticleTest(){
        long articleId = 1L;
        ArticlePatchDto patchDto = new ArticlePatchDto();

    }
}
