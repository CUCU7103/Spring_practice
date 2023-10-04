package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
* 서비스와 함께 협업할 리파지터리, 즉 댓글 리파지터리(CommentRepository)와 게시글 리파지터리(articleRepository) 객체를 주입합니다.
* 댓글 리파지터리뿐만 아니라 게시글 리파지터리까지 필요한 이유는
* 게시글 리파지터리가 있어야 댓글(comment)을 생성할때 대상 게시글(article)의 존재 여부를 파악할 수 있기 때문입니다
*/
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository; // 댓글 repository 객체 주입
    @Autowired
    private ArticleRepository articleRepository; // 게시글 repository 객체 주입

    public List<CommentDto> comments(Long articleId) {
        // 1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 2. 엔티티 -> Dto 변환
        // CommentDto를 저장하는 빈 ArrayList를 만들고 변수에 저장함.
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for(int i = 0; i < comments.size(); i++){ // 조회한 entity 수 만큼 반복진행
            Comment c = comments.get(i); // 하나씩 가져오기
            CommentDto dto = CommentDto.createCommentDto(c); // 엔티티를 Dto로 변환함.
            dtos.add(dto); // 반환한 dto를  dtos 리스트에 삽입하기
        }
        // 3. 결과 반환
        return dtos;

    }
}
