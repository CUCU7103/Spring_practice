package com.example.firstproject.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.autoconfigure.AutoConfiguration;

@ToString // toString() 대체
@AllArgsConstructor //
@NoArgsConstructor // 기본생성자 추가 어노테이션
@Entity // 엔티티임을 선언한다.
        // 이 어노테이션이 붙은 클래스를 기반으로 DB가 생성되어진다. 생성된 테이블의 이름은 클래스의 이름과 동일하다.
        // 자바 객체를 DB가 이해 할 수 있도록 만든것으로 이를 기반으로 테이블이 생성되어진다.
@Getter // 외부(다른클래스등)에서 객체의 데이터를 읽을때 사용하는 메서드 입니다.
public class Article { // DB에 테이블을 설정한다고 생각하면된다.
    @Id // 엔티티의 대표값을 지정함
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스가 auto increament 하여 기본키를 생성해준다.
    // strategy = GenerationType.IDENTITY : DB가 id를 자동생성함. 즉 자동적으로 id값을 부여한다는 것임.
    private Long id;
    @Column // title 필드 선언
    private String title;
    @Column // content 필드 선언
    private String content;

    // 수정할 내용이 있을 경우에만 동작.
    public void patch(Article article) { //
        if(article.title != null) // 갱신할 값이 있다면 갱신을 진행한다.
            this.title = article.title;
        if(article.content != null) {
            this.content = article.content;
        }
    }
}
