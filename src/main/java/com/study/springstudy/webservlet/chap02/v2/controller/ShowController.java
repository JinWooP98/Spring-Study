package com.study.springstudy.webservlet.chap02.v2.controller;

import com.study.springstudy.webservlet.MemberMemoryRepo;
import com.study.springstudy.webservlet.View;
import com.study.springstudy.webservlet.entity.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowController implements ControllerV2 {

    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();

    @Override
    public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 적절한 저장소에서 회원정보들을 가져옴
        List<Member> memberList = repo.findAll();

        //2. 해당 회원정보를 JSP 파일에 전송하기 위한 세팅을 함
        request.setAttribute("mmm", memberList);



        return new View("v2/m-list");
    }
}
