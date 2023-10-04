package com.example.firstproject.entity;

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
}
