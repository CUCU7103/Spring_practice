package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Slf4j
@Service // 서비스임을 명시함
public class CoffeeServices {
    @Autowired
    CoffeeRepository coffeeRepository;

    //get: 전체 목록을 반환한다.
    public List<Coffee> list(){ // 여러개의 게시글을 가져오기에  list를 사용한다.
        return coffeeRepository.findAll();
    }

    public Coffee list_get_id(@PathVariable Long id) { // 단일 게시글을 가져온다.
        return coffeeRepository.findById(id).orElse(null);
    }

    // post 신규 게시글을 입력한다.
    public Coffee input_menu(@PathVariable CoffeeForm dto){
        Coffee coffee = dto.toEntity(); // dto -> entity
        return coffeeRepository.save(coffee); // entity -> dto

    }

    // update
    public Coffee update_menu(@PathVariable Long id
                                ,@RequestBody CoffeeForm dto){
        // update 할 데이터를 담는 엔티티
        Coffee coffee = dto.toEntity();
        // 수정 대상이 db에 있는지 확인하기
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 대상이 없거나 아이디가 잘못된 경우 ( 즉 잘못된 요청을 처리하기)
        if( target == null || id != coffee.getId()){
            log.info("잘못된 요청입니다.");
            return null; // 잘못된 요청이라고 응답한다.
        }
        // 정상적인 경우
        target.patch(coffee); // 기존 데이터에 새로운 데이터 붙이기
        // 수정 내용을 db에 반영한다. entity -> repository
        Coffee updated = coffeeRepository.save(target);
        return updated; // 정상응답임을 컨트롤러에 전달
    }

    // delete
    public Coffee delete_menu(@PathVariable Long id){
        // 타겟 찾기
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 잘못된 요청 처리하기
        if(target == null || id != target.getId()){
            return null;
        }
        // 대상 삭제하기
        coffeeRepository.delete(target);
        // DB에서 삭제한 대상을 controller에 반환한다.
        return target;
    }
}
