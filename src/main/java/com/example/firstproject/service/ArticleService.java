package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service    // 서비스 선언! (서비스 객체를 스프링부트에 선언!)
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();

        // entity의 ID가 있으면 null 리턴, ID가 없다면 새로 생성!
        if(article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {

        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        // 2. 대상 엔티티 찾기
        log.info("대상 엔티티 찾기");
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나, id가 다른경우)
        if(target == null || id != article.getId()){
            log.info("잘못된 요청! id: {}, article: {} ", id, article.toString());
            return null;
        }
        // 4. 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        log.info("업데이트 완료!");
        log.info("updated = " + updated);
        return updated;
    }

    public Article delete(Long id) {
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청처리
        if(target == null){
            return null;
        }
        // 대상 삭제
        articleRepository.delete(target);
        return target;
    }
    @Transactional // 해당 메소드를 트랜잭션으로 묶는다! ( 오류 이전상태로 RollBack!)
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음 -> entity 묶음으로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
//        // 위의 코드를 for문으로 작성했을 때
//        List<Article> articleList = new ArrayList<>();
//        for(int i = 0; i< dtos.size(); i++){
//            ArticleForm dto = dtos.get(i);
//            Article entity = dto.toEntity();
//            articleList.add(entity);
//        }
        // entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

//        // 위의 코드를 for문으로 작성했을 때
//        for(int i =0; i< articleList.size(); i++){
//            Article article = articleList.get(i);
//            articleRepository.save(article);
//        }

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결재 실패!")
        );
        return articleList;
    }
}
