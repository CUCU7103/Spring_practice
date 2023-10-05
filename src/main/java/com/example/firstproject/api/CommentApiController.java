package com.example.firstproject.api;

import com.example.firstproject.controller.ArticleController;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // rest controller 선언함
@RestControllerAdvice
public class CommentApiController {
    @Autowired
    private CommentService commentService; // 댓글 서비스 객체 주입
    // 1. 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments") // 댓글 조회 요청을 접수함.
    // ResponseEntity<>는 rest controller의 반환형 즉 rest api의 응답을 위해 사용하는 클래스입니다.
    // 응답시 status를 사용하여 상태코드를 반환할 수 있습니다.
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // 서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        // 결과를 응답한다. 보통 삼항연산자 보다 예외처리를 실무에서는 많이 사용한다.
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 2. 댓글 생성
    @PostMapping("/api/article/{articleId}/comments") // 댓글 생성 요청 접수
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto) {
        // 서비스에 위임
        CommentDto createDto = commentService.create(articleId,dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }
    // 3. 댓글 수정
    @PatchMapping("/api/comments/{id}") // 댓글 수정 요청을 접수함.
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto) { // update method를 생성한다.
        // 서비스에 위임
        CommentDto updateDto = commentService.update(id,dto);
        // 결과 응답하기 (댓글을 성공적으로 수정했을 경우 응답)
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }


    // 4. 댓글 삭제

    // 5. 예외 처리를 진행함.
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
