package com.study.springstudy.springmvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

/*
create table tbl_reaction
(
    reaction_id   int(8) auto_increment
        primary key,
    board_no      int(8)                               not null,
    account       varchar(50)                          not null,
    reaction_type enum ('LIKE', 'DISLIKE')             not null,
    reaction_date datetime default current_timestamp() null,
    constraint fk_reaction_board
        foreign key (board_no) references tbl_board (board_no),
    constraint fk_reaction_member
        foreign key (account) references tbl_member (account)
);
 */

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reaction {

    private long reactionId;
    private long boardNo;
    private String account;
    private ReactionType reactionType;
    private LocalDateTime reactionDate;
}
