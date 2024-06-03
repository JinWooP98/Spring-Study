package com.study.springstudy.springmvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reaction {
    private long id;
    private String account;
    private long boardNo;
    private ReactionType reactionType;
    private LocalDateTime reactionDate;
}
