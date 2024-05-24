package com.study.springstudy.springmvc.chap05.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/rest")
@Controller
public class RestController {

    @GetMapping("/rest/hello")// 서버사이드렌더링(SSR) 방식 직접 html을 만들어 보내는 것
    @ResponseBody // 서버가 클라이언크에게 순수한 데이터를 전달할 때 사용
    public String hello() {
        // ...

        return "안녕안녕 메롱메롱";
    }

    @GetMapping("/hobby")
    @ResponseBody
    public List<String> hobby() {
        List<String> hobbies = List.of("태권도", "장기", "댄스");
        return hobbies;
    }

}
