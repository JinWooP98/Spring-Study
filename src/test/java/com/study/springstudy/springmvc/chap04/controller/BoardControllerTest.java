package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class BoardControllerTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("입력받은 값에 따라 board데이터 베이스에 저장이 되는지 확인한다.")
    void registerTest() {
        //given
        Board board = new Board(5, "원피스", "루피의 여행", "오다", 0, LocalDateTime.now());
        //when
        boolean result = boardRepository.save(board);
        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("게시판 번호와 일치하는 게시판을 데이터베이스에서 삭제되는지 확인한다.")
    void deleteTest() {
        //given
        int bno = 1;
        //when
        boolean result = boardRepository.delete(bno);
        //then
        assertTrue(result);
    }

}