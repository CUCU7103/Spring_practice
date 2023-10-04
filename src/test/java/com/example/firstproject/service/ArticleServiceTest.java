package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import lombok.ToString;
import org.hibernate.grammars.hql.HqlParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ToString
@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService; // 객체 주입 진행함.
    @Test
    @Transactional
    void create_성공_title_content만_있는_dto_입력() {
        // 1. 예상 데이터
        String title = "라라라라"; // 임의의 값 배정
        String content = "4444";  // 임의의 값 배정함.
        ArticleForm dto = new ArticleForm(null,title,content); // dto 생성함
        Article expected = new Article(4L,title,content); // 예상 데이터 저장
        // 2. 실제 데이터
        Article article = articleService.create(dto); // 실제 데이터를 저장함.
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void index() {
        // 1. 예상 데이터
        Article a = new Article(1L,"가가가가","1111");
        Article b = new Article(2L,"나나나나","2222");
        Article c = new Article(3L,"다다다다","3333");
        // Arrays.asList() : 입력된 배열 또는 2개이상의 동일한 type 데이터를 정적 리스트로 만들어 반환합니다.
        // 정적 리스트는 고정 크기이므로 add()나 remove()메서드를 사용할 수 없습니다.
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        // 2. 실제 데이터
        List<Article> articles = articleService.index();

        // 3. 비교 및 검증 진행.
        // assertEquals(x,y)는 JUnit에서 제공하는 메서드로 예상데이터와 실제 데이터를 비교에 일치하면 테스트를 진행시킵니다.
        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void show_Fail() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected,article);
    }



    @Test
    @Transactional // 데이터를 조회(Read)하는 테스트를 제외하고 Create, Update, delete,하는 테스트를 진행할때는 Transacitinoal를 사용하여 롤백시켜 주어야 한다.
    void create_실패_id가_포함된_dto_입력() {
        // 1. 예상 데이터 입력
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected,article); // null 값은 toString()을 사용할 수없다.
    }


    @Test
    void show_Success () {
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id,"가가가가","1111");
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }


    @Test
    @Transactional
    void update_성공_존재하는_id와_title_content가_있는_dto_입력() {
        // 1. 예상 데이터
        Long id = 3L;
        String title = "가나다라";
        String content = "3343";
        ArticleForm dto = new ArticleForm(id,title,content); // 실제 데이터를 위한 dto 설정
        Article expected = new Article(id,title,content); // 예상 값

        // 2. 실제 데이터
        Article article = articleService.update(id,dto);

        // 3. 비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는_dto_입력() {
        // update를 하려면 대상의 id를 알아야하고, 변경할 내용을 넣어줘야한다.
        // 1. 예상 데이터
        Long id = 3L; // 대상의 아이디
        String title = "가나다"; // 변경사항
        String content = "null"; // 변경사항
        ArticleForm dto = new ArticleForm(id,title,content); // 실제 데이터를 위한 dto 설정
        Article expected = new Article(id,title,content); // 예상 값

        // 2. 실제 데이터
        Article article = articleService.update(id,dto);

        // 3. 비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void update_실패_존재하지않는_id_dto_입력() {
        // 1. 예상 데이터
        Long id = 4L;
        String title = "가나다";
        String content = "null";
        ArticleForm dto = new ArticleForm(id,title,content); // 실제 데이터를 위한 dto 설정
        Article expected = null; // 예상 값

        // 2. 실제 데이터
        Article article = articleService.update(id,dto);

        // 3. 비교 및 검증
        assertEquals(expected,article);
    }


    @Test
    @Transactional
    void delete_성공_존재하는_id_입력() {
        // 1. 예상 데이터 입력
        Long id = 2L;
        Article expected = new Article(id,"나나나나","2222");
        // 2. 실제 데이터
        Article article = articleService.delete(id);
        // 3. 비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void delete_실패_존재하지않는_id_입력() {
        // 1. 예상 데이터 입력
        Long id = 4L;
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.delete(id);
        // 3. 비교 및 검증
        assertEquals(expected,article);
    }
}
