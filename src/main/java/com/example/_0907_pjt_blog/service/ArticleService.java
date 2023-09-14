package com.example._0907_pjt_blog.service;

import com.example._0907_pjt_blog.entity.Article;
import com.example._0907_pjt_blog.entity.Member;
import com.example._0907_pjt_blog.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberService memberService;

    public ArticleService(ArticleRepository articleRepository, MemberService memberService) {
        this.articleRepository = articleRepository;
        this.memberService = memberService;
        articleRepository.save(Article.builder().title("테스트1").content("tttt").build());
        articleRepository.save(Article.builder().title("테스트2").content("tttt").build());
        articleRepository.save(Article.builder().title("테스트3").content("tttt").build());
        articleRepository.save(Article.builder().title("테스트4").content("tttt").build());
    }

    public Page<Article> findAllArticle(int page, int size) {
        return articleRepository.findAll(PageRequest.of(page, size, Sort.by("articleId").descending()));
    }

    public Article findArticle(long articleId) {
        return findArticleByQuery(articleId);
    }


    public Article findArticleByQuery(long articleId){
        Optional<Article> optionalArticle =  articleRepository.findById(articleId);
        Article findArticle = optionalArticle.orElseThrow();
        return findArticle;
    }

    public Article updateArticle(Article article) {
        Article findAricle = findArticle(article.getArticleId());
        System.out.println("patch : "+article.getContent());
        //들어온 정보가 있으면 업데이트
        Optional.ofNullable(article.getContent()).ifPresent(content-> findAricle.setContent(content));
        Optional.ofNullable(article.getTitle()).ifPresent(title -> findAricle.setTitle(title));

        return articleRepository.save(findAricle);
    }

    public void deleteArticle(long articleId) {
        articleRepository.delete(findArticle(articleId));
    }

    public Article createArticle(Article article) {
        //가입된 멤버인지 확인 -> 없으면 게스트 정보 반환
        Member author = memberService.findMemberByLoginId(article.getMember().getLoginId());
        article.setMember(author);

        return articleRepository.save(article);
    }

    public List<Article> findAllArticleByAuthor(String memberId) {
        Member author = memberService.findVerifiedMember(memberId);
        List<Article> articles = articleRepository.findAllByMember(author);

        return articles;
    }
}
