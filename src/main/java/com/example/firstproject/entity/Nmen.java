package com.example.firstproject.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// CSV 연습용

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nmen {
    private String name;
    private String email;
}
