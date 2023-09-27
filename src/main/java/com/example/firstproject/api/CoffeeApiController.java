package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import com.example.firstproject.service.CoffeeServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CoffeeApiController {
    @Autowired
    CoffeeRepository coffeeRepository;
    @Autowired
    CoffeeServices coffeeServices;

    // get
    // 게시글 목록 가져오기
    @GetMapping("/api/coffee") // 전체 리스트 조회 진행함.
    public List<Coffee> coffee_index() { // service를 사용함.
       return coffeeServices.list();
    }
    // 단일 게시글 가져오기
    @GetMapping("/api/coffee/{id}")
    public Coffee coffee_target(@PathVariable Long id){
        return coffeeServices.list_get_id(id); // 엔티티에 값 전달

    }

    // post
    @PostMapping("/api/coffee") // 새로운 글을 작성한다.
    public Coffee create(@RequestBody CoffeeForm dto){
        return coffeeServices.input_menu(dto);

    }
    // update
    @PatchMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id,
                                         @RequestBody CoffeeForm dto){
        Coffee updated = coffeeServices.update_menu(id,dto); // 서비스에서 updated를 받아온다.
        return (updated != null)? // update가 null이 아니라면  (삼항연산자 사용함.)
        ResponseEntity.status(HttpStatus.OK).body(updated): // 정상적인 요청이라고 응답한다.
        ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // null 경우 잘못된 요청이라고 응답한다.
    }
    // delete
    @DeleteMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee deleted  = coffeeServices.delete_menu(id);
        return (deleted != null)?
        ResponseEntity.status(HttpStatus.NO_CONTENT).build(): // 성공적으로 삭제 되었을시
        ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 실패시
    }
  /*



    @PostMapping("/api/")*/
}
