package com.example.firstproject.service;

import com.example.firstproject.entity.Article;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void show_Fail() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected,article);
    }
    // committ 용 변경사항
}