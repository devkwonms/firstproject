package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestAPI용 컨트롤러! 기본적으로 JSON(데이터) 텍스트 ,,,,을 반환 ! ( 기존 컨트롤러는 View 템플릿을 반환함)
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello(){
        return "hello world!";
    }
}
