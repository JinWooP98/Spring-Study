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
import com.study.springstudy.springmvc.chap05.ReplyMapper;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardMapper boardMapper;
    private final ReplyMapper replyMapper;

    public List<BoardListResponseDto> getList(Search page) {
        List<BoardFindAllDto> boardList = boardMapper.findAll(page);
        return  boardList.stream()
                .map(s -> new BoardListResponseDto(s))
                .collect(Collectors.toList());
    }

    public void insert(BoardRequestDto boardPostDto) {
        Board board = boardPostDto.toEntity();
        boardMapper.save(board);
    }

    public void deleteBoard(int bno) {
        boardMapper.delete(bno);
    }

    public BoardDetailResponseDto retrieve(int bno) {
        Board board = boardMapper.findOne(bno);
        if ( board != null) boardMapper.updateViewCount(bno);

        // 댓글 목록 조회
        List<Reply> replies = replyMapper.findAll(bno);

        BoardDetailResponseDto responseDto = new BoardDetailResponseDto(board);
        responseDto.setReplies(replies);
        return responseDto;
    }

    public int getCount(Search search) {
        return boardMapper.count(search);
    }
}
