package com.example.firstproject.repository;

import com.example.firstproject.entity.NewOne;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.data.jpa.repository.JpaRepository;

// Jpa를 사용함.
public interface NewOnerepository extends JpaRepository<NewOne, Code> {

}
