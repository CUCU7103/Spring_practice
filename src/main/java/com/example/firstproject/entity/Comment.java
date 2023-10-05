package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // 해당 클래스가 엔티티임을 선언, 클래스 필드를 바탕으로 DB에 테이블 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성함.
public class Comment {
    @Id // 대표키로 지정함
    @GeneratedValue(strategy = GenerationType.IDENTITY)// DB가 자동으로 1씩 증가한다.
    private Long id; // 대표키
    @ManyToOne // Comment entity와 Article 엔티티를 다대일 관계로 설정
    @JoinColumn(name="article_id") // 외래키 생성 Article entity의 기본키와 맵핑되었다.
    private Article article; // 해당 댓글의 부모 게시글 = 이 댓글이 위치하는 게시글
    @Column // 해당 필드를 데이터의 속성으로 맵핑한다.
    private String nickname; // 댓글을 단 사람
    @Column // 해당 필드를 데이터의 속성으로 맵핑한다.
    private String body; // 댓글 본문

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 발생
        // 예외 발생 코드는 2가지로 나누어서 작성함.
        /*  의도적으로 IlleagalArgumentException 예외발생
         * throw new IllegalArgumentException("메시지");
         *
         */
        // dto에 id가 존재하는 경우  -> entity가 자동으로 생성하기에 값이 없어야 합니다.
        if(dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패!, 댓글의 아이디가 없어야 합니다.");
        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패!, 게시글의 아이디가 잘못되었습니다.");
        // 엔티티 생성 및 반환
        //
        // Service에서 받아온 값들을 사용한다.
        return new Comment(
                dto.getId(), // 댓글 아이디
                article, // 부모 게시글
                dto.getNickname() ,// 댓글 닉네임
                dto.getBody() // 댓글 본문
        );
    }

    public void patch(CommentDto dto) {
        // 예외발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");

        // 객체 갱신
        if(this.getNickname() != null)  // 수정할 닉네임 데이터가 있다면
            this.nickname = dto.getNickname(); //  내용을 반영합니다
        if(this.getBody() != null) // 수정할 본문 데이터가 있다면
            this.body = dto.getBody(); // 내용을 반영합니다.
    }
}
