package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // 엔티티 클래스임을 명시한다.
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class Member {
    @Id // 엔티티의 대표값을 정한다.
    @GeneratedValue
    private Long id;
    @Column
    private String email;
    @Column
    private String password;

}
