package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
/*        // 1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 2. 엔티티 -> Dto 변환
        // CommentDto를 저장하는 빈 ArrayList를 만들고 변수에 저장함.
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for(int i = 0; i < comments.size(); i++){ // 조회한 entity 수 만큼 반복진행
            Comment c = comments.get(i); // 하나씩 가져오기
            CommentDto dto = CommentDto.createCommentDto(c); // 엔티티를 Dto로 변환함.
            dtos.add(dto); // 반환한 dto를  dtos 리스트에 삽입하기
        }

        */
        // 3-1 결과 반환
//            return dtos;
        // 3-2 결과 반환 (stream)으로 개선함
        return commentRepository.findByArticleId(articleId).stream()
//                .map(a -> b) // 스트림의 각 요소(a)를 꺼내 b를 수행한 결과로 맵핑함.
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList()); // 스트림을 list로 변환한다.

    }

    @Transactional // create, update, delete에는  @Transactional을 필수적으로 사용해 준다.
    public CommentDto create(Long articleId, CommentDto dto) {
        // 1. 게시글 조회 및 예외 발생
        /*
         orElseThrow() 메서드는 Optional 객체(존재할 수도 있지만 안 할 수도 있는 객체, 즉 nullO| 될 수도 있는 객체)에 값이 존재하면 그 값을 반환하고.
         값이 존재하지 않으면 전달값으로 보낸 예외를 발생시키는 메서드입니다.
         전달값으로 IllegalArgumentException 클래스를 사용하면 메서드가 잘못됐거나 부적합한 전달값을 보냈음을 나타냅니다.
         */
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!" +  "대상 게시글이 없습니다."));
        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        // 3. 댓글 앤티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        // 4. DTO로 변환해 반환함.
        return CommentDto.createCommentDto(created);
    }
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!" + "대상 댓글이 없습니다."));
        // 2. 댓글 수정
        // Comment에서 patch method 사용함.
        target.patch(dto);
        // 3. DB로 갱신
        Comment updated = commentRepository.save(target);
        // 4. 댓글 entity를 Dto로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }
    @Transactional
    public CommentDto delete(Long id) {
        // 1. 댓글 조회 및 예외를 발생
        Comment target = commentRepository.findById(id) // 삭제할 댓글을 가져오고
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패!"+"대상이 없습니다.")); // 댓글 삭제 실패시 오류메시지 출력합니다.
        // 2. 댓글 삭제
        commentRepository.delete(target);
        // 3. 삭제 댓글을 DTO로 변환 및 반환하기
        return CommentDto.createCommentDto(target);
    }
}
