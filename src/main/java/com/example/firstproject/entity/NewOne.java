package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity // entity 클래스 지정함.
@AllArgsConstructor
@NoArgsConstructor
public class NewOne {

    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "품목코드") // 컬럼명에 맵핑 진행함.
    private long Code;

    @Column(name = "품목명")
    private String Product_name;

    @Column(name = "단위")
    private String Unit ;

    @Column(name = "출고단가")
    private String Output_price;

    @Column(name = "입고단가")
    private String Input_price;

    @Column(name = "단위당수량")
    private String Unit_count;

    @Column(name = "검색창내용")
    private String Search;

    @Column(name = "사용구분")
    private String Useable;

    @Column(name = "표준단가")
    private String Real_price;

    @Column(name = "적요")
    private String Etc;

}
