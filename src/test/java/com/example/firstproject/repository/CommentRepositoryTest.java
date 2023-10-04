package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest // 해당 클래스를 jpa와 연동하여 테스트를 진행한다.
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글을 조회합니다.")
    void findByArticleId() {
        /* case 1 : 4번 게시글의 모든 댓글 조회 */
        // 실제 코딩은 2. 실제 데이터 1. 예상 데이터 3. 비교 및 검증 순 으로 진행한다.
        {
            // 1. 입력 데이터 준비
            Long articleId = 4L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(4L,"당신의 인생영화는?","댓글 고"); // 부모 게시글의 객체를 생성합니다.
            Comment a = new Comment(1L,article,"park","굿 윌 헌팅"); // 댓글 객체를 생성함.
            Comment b = new Comment(2L,article,"kim","I am Sam"); // 댓글 객체를 생성함.
            Comment c = new Comment(3L,article,"Choi","쇼생크 탈출"); // 댓글 객체를 생성함.
            List<Comment> expected = Arrays.asList(a,b,c); // 댓글 객체 합치기
            // 4. 비교 및 검증을 진행
            assertEquals(expected.toString(),comments.toString(),"4번 글의 모든 댓글을 출력함.");
            // (예상 데이터, 실제 데이터, 검증 후 오류가 발생하면 출력할 메시지 )
        }

        /* case 2 : 1번 게시글의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = 1L; // 1번 게시글을 조회
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(1L,"가가가가","1111"); // 부모 게시글의 객체를 생성합니다.
            List<Comment> expected = Arrays.asList(); // 1번글에는 댓글 데이터가 없다.
            // 4. 비교 및 검증을 진행
            assertEquals(expected.toString(),comments.toString(),"1번 글을 댓글이 없다.");
            // (예상 데이터, 실제 데이터, 검증 후  출력할 메시지 )
        }
        /* case 3: 9번 게시글의 댓글을 조회*/
        {
            // 1. 입력 데이터 준비
            Long articleId = 9L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = null; // 9번 게시글은 존재하지 않습니다.
            List<Comment> expected = Arrays.asList();  // 댓글도 존재하지 x
            // 4. 비교 및 검증을 진행
            assertEquals(expected.toString(),comments.toString(),"9번 게시글은 존재하지 않습니다.");
        }

        /* case 4: 999번 게시글의 댓글을 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = 999L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = null; // 999번 게시글은 존재하지 않습니다.
            List<Comment> expected = Arrays.asList();  // 댓글도 존재하지 x
            // 4. 비교 및 검증을 진행
            assertEquals(expected.toString(),comments.toString(),"999번 게시글은 존재하지 않습니다.");
        }

        /* case 5: -1번 게시글은 존재하지 않습니다. */
        /* case 4: 999번 게시글의 댓글을 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = -1L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = null; // -1번 게시글은 존재하지 않습니다.
            List<Comment> expected = Arrays.asList();  // 댓글도 존재하지 x
            // 4. 비교 및 검증을 진행
            assertEquals(expected.toString(),comments.toString(),"-1번 게시글은 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글을 조회함.")
    void findByNickname() {
        /* case 1 : "park의 " 모든 댓글을 조회 */
        {
            // 1. 입력 데이터 준비
            String nickname = "park";
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터

            /*
            * park이 작성한 댓글 1, 4, 7번 데이터를 Comment a, b, c 객체에 저장합니다.
            * 그런데 1번 댓글의 부모(article)는 4번,4번 댓글의 부모(article)는 5번,
            * 7번 댓글의 부모 (article)는 6번으로 부모 게시글이 모두 다릅니다.
            * 경우 하나의 article 객체를 만들어 참조할 수 없으므로 a, b, c 객체 생성 시 article 필드에 각각 객체를 생성
            */

            Comment a = new Comment(1L,new Article(4L,"당신의 인생영화는?","댓글 고"),
                    nickname,"굿 윌 헌팅"); // 각 댓글의 부모가 전부 다르다.
            Comment b = new Comment(4L,new Article(5L,"당신의 소울 푸드는?","댓글 고고"),
                    nickname,"치킨");
            Comment c = new Comment(7L,new Article(6L,"당신의 취미는?","댓글 고고고"),
                    nickname,"조깅");
            List<Comment> expected = Arrays.asList(a,b,c); // 댓글 객체 전체 합치기

            // 4. 비교 및 검증
            assertEquals(expected.toString(),comments.toString(),"park의 모든 댓글을 출력합니다.");
        }

        /* case 2 : "kim"의 모든 댓글을 조회 */
        {
            // 1.입력 데이터 준비
            String nickname = "kim";
            // 2.실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3.예상 데이터
            Comment a = new Comment(2L,new Article(4L,"당신의 인생영화는?","댓글 고"),
                    nickname,"I am Sam");
            Comment b = new Comment(5L,new Article(5L,"당신의 소울 푸드는?","댓글 고고"),
                    nickname,"샤브샤브");
            Comment c = new Comment(8L,new Article(6L,"당신의 취미는?","댓글 고고고"),
                    nickname,"유튜브 시청");
            List<Comment> expected = Arrays.asList(a,b,c);
            // 4. 비교 및 검증
            assertEquals(expected.toString(),comments.toString(),"kim의 댓글은 존재합니다. 다시 확인하세요.");
        }
        /* case 3 : null의 모든 댓글을 조회 */
        {
            // 1. 입력 데이터 준비
            String nickname = null;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            List<Comment> expected =Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected,comments,"닉네임의 값이 없습니다.");
        }
        /* case 4 : "" 의 모든 댓글을 조회 */
            // 1. 입력 데이터 준비
            String nickname = "";
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected,comments,"\"\"의 모든 댓글을 출력!");
    }
}