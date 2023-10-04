package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// 단순하게 crud 작업만 진행할 경우 crudrepository를 사용해도 충분하다.
// 하지만 페이지 정렬 작업까지 해야한다면 jparepository를 사용하는 것이 좋다.
public interface CommentRepository extends JpaRepository<Comment,Long>   {
    // 특정 게시글의 모든 댓글을 조회한다.
    // @Query은 sql과 유사한 jpql이라는 객체지향쿼리 언어를 통해 복잡한 쿼리를 처리함. 여기서는 native 속성을 true로 사용하여 기존 sql문을
    // 그대로 작성하였다.
    @Query(value="select * from comment where article_id = :articleId",
            nativeQuery = true ) // value 속성에 실행하려는 query를 작성함.
    List<Comment> findByArticleId(@Param("articleId")Long articleId); // 명시적으로 @Param을 사용해 주어야 한다.

    // nickname을 받아가지고...
    // 특정 닉네임의 모든 댓글을 조회한다.
    List<Comment> findByNickname(String nickname);
}
