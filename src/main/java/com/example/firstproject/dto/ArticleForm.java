package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

// DTO 클래스이다.
// DTO는 form 태그에 실어보낸 데이터를 서버의 컨트롤러가 객체에 담아서 받는데 이 객체를 DTO라고 한다.
// DTO는 즉 DTO는 컨트롤러가 데이터를 사용할때 받는 객체라고 할 수있다.
@AllArgsConstructor // 클래스 안쪽 필드의 모든 생성자를 자동으로 생성해준다
@ToString // toString() 메서드를 대신해준다.
// dto 클래스 선언함
public class ArticleForm {
    private Long id; // 아이디 필드
    private String title; // 제목 필드
    private String content; // 내용 필드

    public Article toEntity() { // form 데이터를 담은 DTO 객체를 엔티티로 반환합니다.
                                // 전달 값은 Aritcle 클래스의 생성자 형식에 맞게 작성하면된다.
        return new Article(id,title,content);
    }
}
