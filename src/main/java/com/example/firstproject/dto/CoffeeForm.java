package com.example.firstproject.dto;

import com.example.firstproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class CoffeeForm {
    private Long id; // 아이디 필드
    private String name; // 커피이름
    private String price; // 커피 가격

    public Coffee toEntity(){ // dto 객체를 엔티티로 반환한다.
        return new Coffee(id,name,price);
    }
}
