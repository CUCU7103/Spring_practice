package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // rest controller 선언함
public class CommentApiController {
    @Autowired
    private CommentService commentService; // 댓글 서비스 객체 주입
    // 1. 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments") // 댓글 조회 요청을 접수함.
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // 서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        // 결과를 응답한다. 보통 삼항연산자 보다 예외처리를 실무에서는 많이 사용한다.
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 2. 댓글 생성

    // 3. 댓글 수정

    // 4. 댓글 삭제

}
