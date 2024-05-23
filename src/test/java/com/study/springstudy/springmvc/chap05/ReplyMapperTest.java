package com.study.springstudy.springmvc.chap05;

import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyMapperTest {

    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private BoardMapper boardMapper;

//    @Test
//    @DisplayName("")
//    void bulkInsert() {
//        // 게시물 100개와 댓글 5000개를 랜덤으로 등록
//        for (int i = 0; i < 100; i++) {
//            Board b = Board.builder()
//                    .title("재밌는 글" + i)
//                    .writer("아무나" + i)
//                    .content("aasdasd" + i)
//                    .build();
//
//            boardMapper.save(b);
//        }
//
//        for (int i = 0; i <= 5000; i++) {
//            Reply reply = Reply.builder()
//                    .replyText("하하호호댓글" + i)
//                    .replyWriter("아무개" + i)
//                    .boardNo((long) (Math.random() * 100 + 1))
//                    .build();
//
//            replyMapper.save(reply);
//        }
//    }

    @Test
    @DisplayName("전체조회 ")
    void findAllTest() {
        long boardNo = 1;

        List<Reply> replies = replyMapper.findAll(boardNo);

        replies.forEach(System.out::println);
    }
    
    @Test
    @DisplayName("댓글삭제")
    void deleteTest() {
        //given
        long replyNo = 1;
        //when
        replyMapper.delete(replyNo);
        //then
    }

    @Test
    @DisplayName("수정")
    void modifyTest() {
        //given
        long replyNo = 2;
        Reply reply = Reply.builder()
                .replyNo(replyNo)
                .replyText("수정수정")
                .build();
        //when
        replyMapper.modify(reply);
        //then
    }


}