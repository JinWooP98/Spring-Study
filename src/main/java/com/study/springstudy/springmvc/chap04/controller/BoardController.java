package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.PageMaker;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardRequestDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import com.study.springstudy.springmvc.chap04.repository.BoardRepository;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {


    private final BoardService service;
    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String list(@ModelAttribute("s") Search page, Model model) {

        // 1. 데이터베이스로부터 게시글 목록 조회
//        List<Board> boardList = repository.findAll();

        // 2. 클라이언트에 데이터를 보내기전에 랜더링에 필요한 데이터만 추출하기

//        List<BoardListResponseDto> bList = boardList.stream()
//                .map(BoardListResponseDto::new).collect(Collectors.toList());
//        List<BoardListResponseDto> bList = new ArrayList<>();
//
//        for (Board b : boardList) {
//            BoardListResponseDto dto = new BoardListResponseDto(b);
//            bList.add(dto);
//        }
        List<BoardListResponseDto> bList = service.getList(page);

        // 페이지 정보를 생성하여 JSP 에게 전송
        PageMaker maker = new PageMaker(page, service.getCount(page));
        // 3. JSP파일에 해당 목록데이터를 보냄
        model.addAttribute("bList", bList);
        model.addAttribute("maker", maker);

        return "/board/list";
    }

    // 2. 글 쓰기 양식 화면 열기 요청 (/board/write : GET)
    @GetMapping("/write")
    public String write() {
        return "/board/write";
    }
    // 3. 게시글 등록 요청 (/board/write : POST)
    // -> 목록조회 요청 리다이렉션
    @PostMapping("/write")
    public String register(BoardRequestDto boardPostDto, HttpSession session) {

//        Board board = boardPostDto.toEntity();
//
//        repository.save(board);

        service.insert(boardPostDto, session);

        return "redirect:/board/list";
    }
    // 4. 게시글 삭제 요청 (/board/delete : GET)
    // -> 목록조회 요청 리다이렉션
    @GetMapping("/delete")
    public String delete(int bno) {

        service.deleteBoard(bno);

        return "redirect:/board/list";
    }
    // 5. 게시글 상세 조회 요청 (/board/detail : GET)
    @GetMapping("/detail")
    public String detail(int bno, HttpServletRequest request, Model model) {
//        Board board = repository.findOne(bno);
//        if ( board != null) repository.updateViewCount(bno);

        BoardDetailResponseDto board = service.retrieve(bno);

        model.addAttribute("b", board);

        // 4. 요청 헤더를 파싱하여 이전 페이지의 주소를 얻어냄
        String ref = request.getHeader("Referer");
        model.addAttribute("ref", ref);

        return "/board/detail";
    }
}
