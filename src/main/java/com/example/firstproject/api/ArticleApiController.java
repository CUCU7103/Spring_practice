package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.sqm.mutation.internal.temptable.UpdateExecutionDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 로깅하기 위함.
@RestController // rest api 용 컨트롤러 선언함.
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;  // 서비스 객체 주입
                                            // 서비스를 통해서 controller의 비즈니스 로직들을 대신 수행한다.
                                            // 이제 Controller는 클라이언트의 요청과 , 이에 대한 응답만을 반환한다.
//    @Autowired // repository 객체 주입
//    ArticleRepository articleRepository;
    // GET
    // 게시글 목록 조회
    @GetMapping("/api/articles")
    public List<Article> index (){ // 메서드 수행결과로 게시글 목록을 반환해야 함으로 List를 사용한다. (리파지토리는 여러 목록을 반환하면 list로, 단일 객체를 수행하면 entity로)
        return articleService.index(); // 게시글 목록 조회하기
    }

    @GetMapping("/api/articles/{id}") // 단일 게시글을 조회한다.
    public Article show (@PathVariable Long id){
        return articleService.show(id);

    }

 // Post
    @PostMapping("/api/articles")
   public ResponseEntity<Article> create(@RequestBody ArticleForm dto){ // 웹 페이지의 게시판 폼을 만들고 데이터를 생성할때는 controller의 매개변수에 dto만 받아오면 되었다.
                                                        // 하지만 rest api에서 데이터를 생성할때는 json 데이터를 받아와야 함으로 단순히 매개변수로 dto를 쓴다고해서 받아올 수 없다.
                                                         // 그래서 @Requestbody를 사용하게 된다.
        Article created = articleService.create(dto); // 서비스에서 결과를 받고 entity에 저장
        return (created != null)? // 삼항연산자
                ResponseEntity.status(HttpStatus.OK).body(created): // 참일경우
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 거짓일경우

    }
    // Patch
    @PatchMapping("/api/articles/{id}") // ResponseEntity는 rest controller의 반환형, rest api의 응답을 위해 사용하는 클래스 입니다.
                                        // rest api의 요청을 받아 응답할때 이 클래스에 http 상태코드, 해더,본문을 실어 보낼 수 있습니다.
    public ResponseEntity<Article> update(@PathVariable Long id,
                                         @RequestBody ArticleForm dto){ // 수정하기 할 게시글을 정보를 가져오기 위해 id 변수사용,
                                                         // 수정할 결과를 다시 보낼때 json 데이터를 반환함으로 @Requestbody 사용함.
        Article updated = articleService.update(id, dto); // 서비스를 통하여 게시글을 수정한다.
        return (updated != null)?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id); // 서비스를 통해서 게시글의 삭제를 진행한다.
        return (deleted != null)?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build(): // 성공적으로 삭제되었을시
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 실패 시

    }

    @PostMapping("/api/transaction-test") // 여러 게시글 생성 요청을 접수한다.
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos); // 서비스를 호출한다.
        return (createdList != null)?
                ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

}
