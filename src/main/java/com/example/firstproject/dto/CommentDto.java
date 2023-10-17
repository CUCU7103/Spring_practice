package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
// CommentDto는 Comment를 담을 그릇이다.
public class CommentDto {
    private Long id; // 댓글의 id
    private Long articleId; // 댓글의 부모 id
    private String nickname; // 댓글 작성자
    private String body; // 댓글 본문

    // 생성 메서드
    // entity를 dto로 변경해준다.
    public static CommentDto createCommentDto(Comment comment) { // Dto 생성 메서드
        return new CommentDto(
                comment.getId(), // 댓글 entity의 id
                comment.getArticle().getId(), // 댓글 entity가 속한 부모 게시글의 id
                comment.getNickname(), // 댓글 entity의 nickname
                comment.getBody() // 댓글 entity의 body
        );
    }


}
