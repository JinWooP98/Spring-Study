package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap04.common.PageMaker;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardRequestDto;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import com.study.springstudy.springmvc.chap05.dto.response.ReactionDto;
import com.study.springstudy.springmvc.chap05.entity.ReactionType;
import com.study.springstudy.springmvc.chap05.service.ReactionService;
import com.study.springstudy.springmvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {


    private static final Logger log = LoggerFactory.getLogger(BoardController.class);
    private final BoardService boardService;
    private final ReactionService reactionService;
    private final HttpSession httpSession;

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
        List<BoardListResponseDto> bList = boardService.getList(page);

        // 페이지 정보를 생성하여 JSP 에게 전송
        PageMaker maker = new PageMaker(page, boardService.getCount(page));
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

        boardService.insert(boardPostDto, session);

        return "redirect:/board/list";
    }
    // 4. 게시글 삭제 요청 (/board/delete : GET)
    // -> 목록조회 요청 리다이렉션
    @GetMapping("/delete")
    public String delete(int bno) {

        boardService.deleteBoard(bno);

        return "redirect:/board/list";
    }
    // 5. 게시글 상세 조회 요청 (/board/detail : GET)
    @GetMapping("/detail")
    public String detail(int bno, HttpServletRequest request, Model model, HttpServletResponse response) {
//        Board board = repository.findOne(bno);
//        if ( board != null) repository.updateViewCount(bno);

        BoardDetailResponseDto board = boardService.retrieve(bno, request, response);

        model.addAttribute("b", board);

        // 4. 요청 헤더를 파싱하여 이전 페이지의 주소를 얻어냄
        String ref = request.getHeader("Referer");
        model.addAttribute("ref", ref);

        return "/board/detail";
    }

    // 좋아요 요청 비동기 처리
    @GetMapping("/like")
    @ResponseBody
    public ResponseEntity<?> like(long bno, HttpSession session) {

        log.info("like async request!");

        String account = LoginUtil.getLoggedInUserAccount(session);
        ReactionDto dto = reactionService.like(bno, account);
        return ResponseEntity.ok().body(dto);
    }

    // 싫어요 요청 비동기 처리
    @GetMapping("/dislike")
    @ResponseBody
    public ResponseEntity<?> dislike(long bno, HttpSession session) {

        log.info("dislike async request!");

        String account = LoginUtil.getLoggedInUserAccount(session);
        ReactionDto dto = reactionService.dislike(bno, account);
        return ResponseEntity.ok().body(dto);
    }
}
