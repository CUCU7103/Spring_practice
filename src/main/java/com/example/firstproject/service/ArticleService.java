package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Service // 서비스 객체 생성
@Slf4j
// 비즈니스 로직을 처리한다.
public class ArticleService {
    @Autowired // repository 객체를 주입한다.
    private ArticleRepository articleRepository;

    public List<Article> index() { // controller의 작업을 service에서 대신한다.
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity(); // dto -> entity로 변환
        if(article.getId() != null ){ // 만약 id 존재한다면 null을 반환시킨다.
            return null;
        }
        return articleRepository.save(article); // article(entity)를 repository에 저장
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. 수정용 entity 생성하기 (dto 엔티티 변환) // 수정한 데이터를 받는다.
        Article article = dto.toEntity();
        log.info("id: {}, article:{}",id, article.toString()); // 진행사항 확인을 위한 로그 찍기
        // 2. 타깃 조회하기 (DB에 대상 엔티티가 있는지 조회하기)
        Article target = articleRepository.findById(id).orElse(null); // repository의 값을 엔티티에 넣는다.
        // 3. 잘못된 요청처리하기 (대상 엔티티가 없거나 수정하려는 id가 잘못됬을경우 처리하기)
        if(target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답.
            log.info("잘못된 요청 id:{}, article:{}", id, article.toString());
            return null;
        }
        // 4. 대상 엔티티가 있으면 수정내용으로 업데이트 및 정상 응답(200)하기
        target.patch(article); // 기존 데이터에 새 데이터 붙이기
        Article updated = articleRepository.save(target); // 수정한 내용을 db에 최종적으로 저장한다.
        return updated;// 정상응답임을 반환하기
    }

    public Article delete(Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null); // 값을 조회하고 없으면 null을 반환한다.

        // 2. 잘못된 요청 처리하기
        if (target == null){
            return null; // 응답은 컨트롤러가 진행함으로 여기서는 null을 반환한다.
        }
        // 3. 대상 삭제하기
        articleRepository.delete(target); // 삭제 작업을 진행한다.
        return target; // db에서 삭제한 대상을 컨트롤러에 반환한다.

    }


    @Transactional // 트랜잭션 선언함.
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = new ArrayList<>(); // 여러개의 정보를 받기에 List type으로 받아줄 변수를 생성한다.
        for (int i = 0; i < dtos.size(); i++){ // 반복문을 실행함.
            ArticleForm dto = dtos.get(i); // 순서대로 변수를 dto에 담는다.
            Article entity = dto.toEntity(); // dto에 담은 변수를 entity로 변환한다.
            articleList.add(entity); // 변환한 entity를 List에 담아준다.

        }
        // 2. 엔티티 묶음을 DB에 저장하기
        for (int i = 0; i< articleList.size(); i++){
            Article article = articleList.get(i); // List에서 값을 하나씩 가져와서 entity에 저장하고
            articleRepository.save(article); // 엔티티를 repository에 저장한다.
        }
        // 3. 강제 예외 발생시키기
        articleRepository.findById(-1L) // id가 -1인 데이터를 찾기
                .orElseThrow(() -> new IllegalArgumentException("결제 실패"));  // 찾는 데이터가 없으면 예외를 발생시킨다.

        // 4. 결과 값 반환하기
        return articleList;
    }
}
