package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity // 엔티티 클래스를 선언함.
@AllArgsConstructor // argument를 가지고 있는 생성자 생성
@NoArgsConstructor // 기본 생성자를 생성한다.
@ToString
@Getter
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String price;

    public void patch(Coffee coffee) {
        if(coffee.name !=null)
            this.name = coffee.name;
        if(coffee.price != null)
            this.price = coffee.price;
    }
}
