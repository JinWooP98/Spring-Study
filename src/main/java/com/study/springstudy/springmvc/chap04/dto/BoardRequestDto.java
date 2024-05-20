package com.study.springstudy.springmvc.chap04.dto;


import com.study.springstudy.springmvc.chap04.entity.Board;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@EqualsAndHashCode
// dto 의 필드명은 반드시 html form 태그의 입력태그들 name 속성과 일치해야 함
public class BoardRequestDto {
    private String writer;
    private String title;
    private String content;

    public Board toEntity() {
        Board b = new Board();
        b.setContent(this.content);
        b.setWriter(this.writer);
        b.setTitle(this.title);
        return b;
    }

}


