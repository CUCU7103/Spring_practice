package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
// 리퍼지토리는 사용자가 직접 구현할 수도 있고 jpa에서 제공하는 리파지터리 인터페이스를 활용해 만들 수도 잇다.
// CrudRepository를 상속받아서 별다른 작업 없이 CRUD 작업이 가능하도록 해준다.
// repository의 역할: 엔티티가 DB속 테이블에 저장 및 관리될 수 있게 하는 인터페이스이다.
// 단일 데이터를 조회할때는 엔티티를 반환하고
// 데이터 목록을 조회할때는 List<>를 반환한다.
public interface ArticleRepository extends CrudRepository<Article, Long> { // crud 작업을 가능하게 해준다.
    @Override  // Iterable -> ArrayList 수정
    ArrayList<Article> findAll(); // <관리 대상 엔티티의 클래스타입 , 관리대상 엔티티의 대표값 타입>


}

// view의 form에서 보낸 데이터를 dto가 받고 엔티티(jpa)에게 전달 그리고 리파지토리(jpa)를 통해 crud작업을 거쳐 db에 저장되어진다.