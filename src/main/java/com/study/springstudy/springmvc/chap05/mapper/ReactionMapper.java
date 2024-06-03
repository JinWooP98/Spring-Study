package com.study.springstudy.springmvc.chap05.mapper;

import com.study.springstudy.springmvc.chap05.entity.Reaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReactionMapper {

    // 좋아요 로그 가져오기
    Reaction findOne(@Param("account") String account, @Param("bno") long bno);

    // 좋아요&싫어요 저장
    void insertReaction(Reaction reaction);

    // 좋아요&싫어요 삭제
    void deleteReaction(Reaction reaction);

    // 좋아요&싫어요 업데이트
    void upDataReaction(Reaction reaction);
}
