package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class MemberForm {
    private Long id;
    private String email; // 이메일 주소
    private String password; // 비밀번호



    public Member toEntity() {  // form 데이터를 담은 DTO 객체를 엔티티로 반환합니다.
                                // 전달 값은 해당 클래스의 생성자 형식에 맞게 작성하면된다.

        return new Member(id,email,password);
    }
}
