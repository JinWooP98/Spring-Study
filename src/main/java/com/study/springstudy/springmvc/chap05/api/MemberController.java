package com.study.springstudy.springmvc.chap05.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/members")
@Slf4j
public class MemberController {

//    // 회원가입 양식 열기
//    @GetMapping("/hello")
//    // json을 보내고 싶다면 @ResponseBody 을 사용 단, modelandview말고 string
//    public ModelAndView hello() {
//        return new ModelAndView("board/write");
//    }

    //회원가입 양식 열기
    @GetMapping("/sign-up")
    public void signUp() {
        log.info("/members/sign-up GET : forwarding to sign-up.jsp");
//        return "members/sign-up";
        // url이 JSP 경로와 같으면 리턴 타입을 void로 지정해주면 된다.

    }

}
