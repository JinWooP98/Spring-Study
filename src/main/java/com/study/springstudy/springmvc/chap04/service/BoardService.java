package com.study.springstudy.springmvc.chap04.service;


import com.study.springstudy.springmvc.chap03.entity.Score;
import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardRequestDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardMapper mapper;

    public List<BoardListResponseDto> getList(Search page) {
        List<Board> boardList = mapper.findAll(page);
        return  boardList.stream()
                .map(s -> new BoardListResponseDto(s))
                .collect(Collectors.toList());
    }

    public void insert(BoardRequestDto boardPostDto) {
        Board board = boardPostDto.toEntity();
        mapper.save(board);
    }

    public void deleteBoard(int bno) {
        mapper.delete(bno);
    }

    public BoardDetailResponseDto retrieve(int bno) {
        Board board = mapper.findOne(bno);
        if ( board != null) mapper.updateViewCount(bno);

        return new BoardDetailResponseDto(board);
    }

    public int getCount(Search search) {
        return mapper.count(search);
    }
}
