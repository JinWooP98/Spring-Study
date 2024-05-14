package com.study.springstudy.webservlet.chap02.v2.controller;

import com.study.springstudy.webservlet.MemberMemoryRepo;
import com.study.springstudy.webservlet.View;
import com.study.springstudy.webservlet.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveController implements ControllerV2 {
    MemberMemoryRepo repo = MemberMemoryRepo.getInstance();
    @Override
    public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("userName");
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        //2. 회원 정보를 객체로 포장하여 적절한 저장소에 저장
        Member member = new Member(name, account, password);
        repo.save(member);

        //3. 적절한 페이지로 이동 - 조회화면으로 리다이렉트
//        response.sendRedirect("/chap02/v1/show");
        return new View("redirect:/chap02/v2/show");
    }
}
