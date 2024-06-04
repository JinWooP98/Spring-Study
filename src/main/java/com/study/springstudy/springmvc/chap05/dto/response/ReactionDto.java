package com.study.springstudy.springmvc.chap05.dto.response;

import com.study.springstudy.springmvc.chap05.entity.ReactionType;
import lombok.*;

@Getter @ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReactionDto {

    // 좋아요 처리를 위해 클라이언트에 보낼 JSON
    private int likeCount; // 갱신된 총 좋아요 수
    private int dislikeCount; // 갱신된 총 싫어요 수
    private String userReaction; // 현재 리액션 상태
}
