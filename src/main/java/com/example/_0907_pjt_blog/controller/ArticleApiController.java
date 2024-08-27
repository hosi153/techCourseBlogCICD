package com.example._0907_pjt_blog.controller;

import com.example._0907_pjt_blog.controller.dto.ArticlePatchDto;
import com.example._0907_pjt_blog.controller.dto.ArticlePostDto;
import com.example._0907_pjt_blog.controller.mapper.ArticleMapper;
import com.example._0907_pjt_blog.entity.Article;
import com.example._0907_pjt_blog.entity.Member;
import com.example._0907_pjt_blog.repository.ArticleRepository;
import com.example._0907_pjt_blog.service.ArticleService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Api(tags={"POST"}, description="게시글 관리")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ArticleApiController {
    private final ArticleService articleService;
    private final ArticleMapper mapper;

    //게시글 생성
    @ApiOperation(value="게시글 작성", notes="게시글 신규로 생성하는 API 설명")
    @PostMapping("/new")

    public ResponseEntity postArticle(@ApiParam(value="게시글 작성정보", required = true) @RequestBody ArticlePostDto articlePostDto){
        Article mappedArticle = mapper.articlePostDtoToArticle(articlePostDto);
        //Article mappedArticle = ArticlePostDto.articlePostDtoToArticle(articlePostDto);
        articleService.createArticle(mappedArticle);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //게시글 업데이트
    @ApiOperation(value="게시글 수정", notes="게시글 수정API 입니다.")
    @ApiImplicitParam(
            name = "articleId",
            value = "게시글id",
            required = true,
            example = "1",
            dataType = "String",
            paramType = "path",
            defaultValue = "None"
    )
    @ApiResponses({
            @ApiResponse(code=200, message="성공"),
            @ApiResponse(code=401, message="접근 권한이 없습니다.")
    })
    @PatchMapping("/{articleId}")
    public ResponseEntity updateArticle(@PathVariable("articleId") long articleId, @RequestBody ArticlePatchDto articlePatchDto){
        articlePatchDto.setArticleId(articleId);
        Article article = articleService.updateArticle(mapper.articlePatchDtoToArticle(articlePatchDto));
        return new ResponseEntity(HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/{articleId}")
    public ResponseEntity deleteArticle(@PathVariable("articleId") long articleId){
        articleService.deleteArticle(articleId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
