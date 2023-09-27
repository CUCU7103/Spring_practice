package com.example.firstproject.api;

import com.example.firstproject.controller.ArticleController;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.sqm.mutation.internal.temptable.UpdateExecutionDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j // 로깅하기 위함.
@RestController // rest api 용 컨트롤러 선언함.
public class ArticleApi2Controller {
//    @Autowired // repository 객체 주입
//    ArticleRepository articleRepository;
//    // GET
//    // 게시글 목록 조회
//    @GetMapping("/api/articles")
//    public List<Article> index (){ // 메서드 수행결과로 게시글 목록을 반환해야 함으로 List를 사용한다. (리파지토리는 여러 목록을 반환하면 list로, 단일 객체를 수행하면 entity로)
//        return articleRepository.findAll(); // 게시글 목록 조회하기
//    }
//
//    @GetMapping("/api/articles/{id}") // 단일 게시글을 조회한다.
//    public Article show (@PathVariable Long id){
//        return articleRepository.findById(id).orElse(null);
//    }
//
//    // Post
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto){ // 웹 페이지의 게시판 폼을 만들고 데이터를 생성할때는 controller의 매개변수에 dto만 받아오면 되었다.
//                                                         // 하지만 rest api에서 데이터를 생성할때는 json 데이터를 받아와야 함으로 단순히 매개변수로 dto를 쓴다고해서 받아올 수 없다.
//                                                         // 그래서 @Requestbody를 사용하게 된다.
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//
//    }
//    // Patch
//    @PatchMapping("/api/articles/{id}") // ResponseEntity는 rest controller의 반환형, rest api의 응답을 위해 사용하는 클래스 입니다.
//                                        // rest api의 요청을 받아 응답할때 이 클래스에 http 상태코드, 해더,본문을 실어 보낼 수 있습니다.
//    public ResponseEntity<Article> update(@PathVariable Long id,
//                                         @RequestBody ArticleForm dto){ // 수정하기 할 게시글을 정보를 가져오기 위해 id 변수사용,
//                                                         // 수정할 결과를 다시 보낼때 json 데이터를 반환함으로 @Requestbody 사용함.
//        // 1. 수정용 entity 생성하기 (dto 엔티티 변환) // 수정한 데이터를 받는다.
//        Article article = dto.toEntity();
//        log.info("id: {}, article:{}",id, article.toString()); // 진행사항 확인을 위한 로그 찍기
//        // 2. 타깃 조회하기 (DB에 대상 엔티티가 있는지 조회하기)
//        Article target = articleRepository.findById(id).orElse(null); // repository의 값을 엔티티에 넣는다.
//        // 3. 잘못된 요청처리하기 (대상 엔티티가 없거나 수정하려는 id가 잘못됬을경우 처리하기)
//        if(target == null || id != article.getId()) {
//            // 400, 잘못된 요청 응답.
//            log.info("잘못된 요청 id:{}, article:{}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 4. 대상 엔티티가 있으면 수정내용으로 업데이트 및 정상 응답(200)하기
//        target.patch(article); // 기존 데이터에 새 데이터 붙이기
//        Article updated = articleRepository.save(target); // 수정한 내용을 db에 최종적으로 저장한다.
//        return ResponseEntity.status(HttpStatus.OK).body(updated); // 정상응답임을 반환하기
//    }
//
//    // Delete
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id){
//        // 1. 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null); // 값을 조회하고 없으면 null을 반환한다.
//
//        // 2. 잘못된 요청 처리하기
//        if (target == null){
//            // target이 null이면 responseEntity의 상태는 BAD_Requsest, 본문에는 null을 실어 보냅니다.
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 3. 대상 삭제하기
//        articleRepository.delete(target); // 삭제 작업을 진행한다.
//        //
//        return ResponseEntity.status(HttpStatus.OK).body(null); // body(null)은  build()와 같은 의미이다.
//
//    }

}
