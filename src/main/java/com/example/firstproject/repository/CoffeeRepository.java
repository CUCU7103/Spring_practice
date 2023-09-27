package com.example.firstproject.repository;

import com.example.firstproject.entity.Coffee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository // repository 선언
public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    @Override // Iterable에서 ArrayList로 형 변환 진행.
    ArrayList<Coffee> findAll();

}
