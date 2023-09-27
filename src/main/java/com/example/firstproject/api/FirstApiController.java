package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Rest api용 컨트롤러
public class FirstApiController {
    @GetMapping("/api/hello")
    public String test(){
        return "hello world";
    }
}
