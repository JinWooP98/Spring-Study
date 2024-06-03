package com.study.springstudy.springmvc.interceptor;

import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import com.study.springstudy.springmvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class BoardInterceptor implements HandlerInterceptor {

    private final BoardMapper boardMapper;
    // preHandle을 구현하여
    // 로그인을 안한 회원은 글쓰기, 글수정, 글삭제 요청을 거부할 것!
    // 거부하고 로그인 페이지로 리다이렉션할 것!
    // 클라이언트의 요청이 컨트롤러에 들어가기 전에 해야할 일을 명시
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        log.debug("after login interceptor execute!");
        String requestUri = request.getRequestURI();
        if(requestUri.equals("/board/delete")) {
            if(!LoginUtil.isLoggedIn(request.getSession())) {
                response.sendRedirect("/members/sign-in?message=login-required&redirect=/access-deny");
                return false;
            }

            int bno = Integer.parseInt(request.getParameter("bno"));
            Board board = boardMapper.findOne(bno);
            String boardAccount = board.getAccount();

            if(!(LoginUtil.getLoggedInUserAccount(request.getSession()).equals(boardAccount)
            || LoginUtil.getLoggedInUserAuth(session).equals("ADMIN"))) {
                response.setStatus(403);
                response.sendRedirect("/access-deny?message=authorization");
                return false;
            }
            return true; // 리턴이 true 일경우 컨트롤러 진입 허용, false 진입 차단

        }
        if(!LoginUtil.isLoggedIn(request.getSession())) {
            response.sendRedirect("/members/sign-in?message=login-required&redirect=" + requestUri);
            return false;
        }


        return true; // 리턴이 true 일경우 컨트롤러 진입 허용, false 진입 차단

    }



}
