package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller // 컨트롤러
@Slf4j // 로깅 기능을 위한 어노테이션
public class ArticleController {
    @Autowired // spring boot에 의한 객체주입 (의존성 주입)
    private ArticleRepository articleRepository; // repository 객체 주입됨
    @Autowired
    private CommentService commentService; // 서비스 객체를 주입함.

    // 입력페이지
    @GetMapping("/articles/new") // url 요청을 받는다.
    public String newArticleForm() {
        return "articles/new";

    }
    // 입력 후 상세페이지로 redirect 진행
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        // 1. DTO를 엔티티로 전환
        Article article = form.toEntity();
        log.info(form.toString()); // 로깅 코드 추가
        // 2. 리파지터리로 엔티티를 DB에 저장
        // 엔티티를 저장하여 saved에 반환함.
        Article saved = articleRepository.save(article); // save는 crudrepository의 기능이다.
        log.info(saved.toString()); // 로깅 코드 추가
        return "redirect:/articles/" + saved.getId();
    }

    //개별 아이디 조회
    @GetMapping("/articles/{id}")  // 요청시 id 값도 같이 넘어온다. {}를 사용한다.
    public String show(@PathVariable Long id, Model model) { // @PathVariable은 url요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져오는 @
        // Model은 DB의 데이터를 view에 사용하기 위함이다.
        // String 타입으로 선언하는건 결국 return 값이 url 주소를 지정하기 때문이다.
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        // findById()는 jpa의 crudrepository가 제공하는 메서드로 특정 엔티티의 id 기준으로 데이터를 찾아  Optinal 타입으로 반환합니다.
        // Article articleEntity = articleRepository.findById(id); // 반환 타입이 맞지 않아 오류가 발생한다.
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);
        // orElse()를 사용함
        // 2. 모델에 데이터 조회하기
        model.addAttribute("article",articleEntity); // article이라는 이름으로 객채를 등록함.
        model.addAttribute("commentsDtos",commentsDtos); // 댓글 목록을 모델에 등록하였다.
        // 3. 뷰 페이지 전환하기
        return "articles/show";
    }
    // 전체 목록 조회
    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 데이터 가져오기
        // findAll()의 원래 type은 Iterable이기에 crudRepository에서 오버라이딩을 통해 재정의하여
        // ArrayList type으로 다운캐스트 하였다.
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList);
        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }
    // 수정을 담당하는 view 으로 정보를 전달한다.
    @GetMapping("/articles/{id}/edit") // Controller에서 url 변수는 {}만을 사용한다.
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null); // DB에서 수정할 데이터 가져오기
        // 모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);
        // 뷰 페이지 설정하기
        return "articles/edit";
    }
    // 수정 폼에서 받은 데이터
    @PostMapping("/articles/update")
    public String update(ArticleForm form){ // 수정 폼에서 전송한 데이터를 매개변수로 DTO 받아오기
        log.info(form.toString());
        // 1. Dto를 entity로 반환하기
        Article articleEntity = form.toEntity(); // dto를 엔티티로 변환하기
        log.info(articleEntity.toString()); // entity로 잘 반환됬는지 확인하기
        // 2. 엔티티를 DB에 저장하기 (기존 데이터를 불러오고 -> 불러온 기존 데이터 값을 갱신한다.)
        // 2-1. DB에서 기존데이터를 불러오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2. 기존 데이터 값을 갱신하기
        if (target != null) {
            articleRepository.save(articleEntity); // entity를 DB에 저장하기
        }
        // 3. 수정 결과 페이지로 redirect하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    // 삭제 메서드
    @GetMapping("/articles/{id}/delete")
    public String deleteArticle(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제요청이 들어왔습니다.");
        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 대상 엔티티 삭제하기
        log.info(target.toString()); // 정보의 유무확인
        if (target != null){ // 삭제할 데이터가 존재한다면
            articleRepository.delete(target); // delete()메서드를 통해서 대상을 삭제한다.
            rttr.addFlashAttribute("msg","삭제되었습니다.");
        }
        // 3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }

}






































