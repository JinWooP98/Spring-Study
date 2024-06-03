package com.study.springstudy.springmvc.chap04.service;


import com.study.springstudy.springmvc.chap03.entity.Score;
import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardFindAllDto;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardRequestDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import com.study.springstudy.springmvc.chap05.mapper.ReplyMapper;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import com.study.springstudy.springmvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.study.springstudy.springmvc.util.LoginUtil.*;

@RequiredArgsConstructor
@Service
public class BoardService {

    private static final Logger log = LoggerFactory.getLogger(BoardService.class);
    private final BoardMapper boardMapper;
    private final ReplyMapper replyMapper;

    public List<BoardListResponseDto> getList(Search page) {
        List<BoardFindAllDto> boardList = boardMapper.findAll(page);
        return  boardList.stream()
                .map(s -> new BoardListResponseDto(s))
                .collect(Collectors.toList());
    }

    public void insert(BoardRequestDto boardPostDto, HttpSession session) {
        Board board = boardPostDto.toEntity();
        // 계정명을 엔터티에 추가 - 세션에서 계정명 가져오기
        board.setAccount(LoginUtil.getLoggedInUserAccount(session));
        boardMapper.save(board);
    }

    public void deleteBoard(int bno) {
        boardMapper.delete(bno);
    }

    public BoardDetailResponseDto retrieve(int bno, HttpServletRequest request, HttpServletResponse response) {
        // 게시물 정보 조회
        Board board = boardMapper.findOne(bno);

        HttpSession session = request.getSession();
        // 비회원이거나 본인 글이면 조회수 증가 방지
        if ( board != null && LoginUtil.isLoggedIn(session)) {
            if(!LoginUtil.getLoggedInUserAccount(session).equals(board.getAccount())) {
                // 조회수 증가 여부를 판단
                /*
                    - 만약 내가 처음 조회한 상대방의 게시물이면
                      해당 게시물 번호로 쿠키를 생성 쿠키 안에는 생성 시간을 저장
                      수명은 1시간으로 설정
                    - 이후 게시물 조회시 반복해서 쿠키를 조회한 후 해당 쿠키가 존재할 시 false 를 리턴하여 조회수증가를 방지
                    - 쿠키 생성 예시
                    Cookie(name= view_101, 2024-06-03 14:11:30)
                 */
                String cookieName = String.valueOf(bno) + LoginUtil.getLoggedInUserAccount(session);
                Cookie viewLogCookie = WebUtils.getCookie(request, cookieName);

                // 이 게시물에 대한 쿠키가 존재하지 않는다면 or 쿠키가 존재하지만 cookieName이 일치하지 않는다면
                if(viewLogCookie == null || !Objects.equals(viewLogCookie.getName(), cookieName)) {

                    // 쿠키를 생성하고 클라이언트에 전송
                    Cookie viewCookie = new Cookie(cookieName, LocalDateTime.now().toString());
                    // 쿠키 설정
                    viewCookie.setPath("/board/detail"); // 쿠키를 사용할 범위 결정
                    viewCookie.setMaxAge(60 * 60); // view log 유지 시간

                    // 2. 쿠키를 클라이언트에 전송 - 응답바디에 실어보내야 함
                    response.addCookie(viewCookie);

                    boardMapper.updateViewCount(bno);
                }

            }
        }

//        // 댓글 목록 조회
//        List<Reply> replies = replyMapper.findAll(bno);

        BoardDetailResponseDto responseDto = new BoardDetailResponseDto(board);
//        responseDto.setReplies(replies);
        return responseDto;
    }

    public int getCount(Search search) {
        return boardMapper.count(search);
    }
}
