package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅 된다.
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        // 예상 시나리오
        Article a = new Article(1L,"가가가가","1111");
        Article b = new Article(2L,"나나나나","2222");
        Article c = new Article(3L,"다다다다","3333");

        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        // 실제 결과
        List<Article> articles = articleService.index();

        // 예상과 실제를 비교

        assertEquals(expected.toString(), articles.toString());

    }

    @Test
    void show_성공____존재하는_id_입력() {
        // 예상시나리오
        Long id = 1L;
        Article expected = new Article(id,"가가가가","1111");
        // 실제코드
        Article article = articleService.show(id);
        // 예상과 실제를 비교
        assertEquals(expected.toString(),article.toString());
        
    }
    
    @Test
    void show_실패____존재하지_않는_id_입력() {
        // 예상시나리오
        Long id = -1L;
        Article expected = null;
        // 실제코드
        Article article = articleService.show(id);
        // 예상과 실제를 비교
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void create_성공___title과_content만_있는_dto_입력() {
        // 예상시나리오
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null,title,content);
        Article expected = new Article(4L,title,content);
        // 실제코드
        Article article = articleService.create(dto);
        // 예상과 실제를 비교
        assertEquals(expected.toString(),article.toString());
    }
    @Test
    @Transactional
    void create_실패____id가_포함된_dto_입력(){
        // 예상시나리오
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L,title,content);
        Article expected = null;
        // 실제코드
        Article article = articleService.create(dto);
        // 예상과 실제를 비교
        assertEquals(expected,article);
        
    }

    @Test
    @Transactional
    void update_성공____존재하는_id와_title_content가_있는_dto_입력(){
        // 예상시나리오
        Long id = 1L;
        String title = "라라라라";
        String content = "1234";

        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = new Article(id,title,content);

        // 실제코드
        Article article = articleService.update(id, dto);
        // 예상과 실제를 비교
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void update_성공____존재하는_id와_title만_있는_dto_입력(){
        // 예상 시나리오

        Long id = 2L;
        String title = "권민서";


        ArticleForm dto = new ArticleForm(id,title,null);
        Article expected = new Article(id,title,"2222");

        // 실제코드
        Article article = articleService.update(id, dto);
        // 예상과 실제를 비교
        assertEquals(expected.toString(),article.toString());

    }

    @Test
    @Transactional
    void update_실패____존재하지_않는_id의_dto_입력(){

        // 예상 시나리오
        Long id = -1L;

        ArticleForm dto = new ArticleForm(id,null,null);
        Article expected = null;

        // 실제코드
        Article article = articleService.update(id, dto);

        // 예상과 실제를 비교
        assertEquals(expected,article);

    }
    @Test
    @Transactional
    void update_실패____id만_있는_dto_입력(){

        // 예상 시나리오
        Long id = 1L;

        ArticleForm dto = new ArticleForm(id,null,null);
        Article expected = new Article(id,null,null);

        // 실제코드
        Article article = articleService.update(id, dto);

        // 예상과 실제를 비교
        assertEquals(expected,article);

    }

    @Test
    @Transactional
    void delete_성공____존재하는_id_입력(){
        // 예상시나리오
        Long id = 1L;
        Article expected = new Article(id,"가가가가","1111");
        // 실제코드
        Article article = articleService.delete(id);
        // 예상과 실제를 비교
        assertEquals(expected.toString(),article.toString());

    }

    @Test
    @Transactional
    void delete_실패____존재하지_않는_id_입력(){
        // 예상시나리오
        Long id = -1L;

        Article expected = null;
        // 실제코드
        Article article = articleService.delete(id);
        // 예상과 실제를 비교
        assertEquals(expected,article);
    }
}