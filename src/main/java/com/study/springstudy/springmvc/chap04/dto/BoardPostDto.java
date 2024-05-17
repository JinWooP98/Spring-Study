package com.study.springstudy.springmvc.chap04.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@EqualsAndHashCode
public class BoardPostDto {
    private String writer;
    private String title;
    private String content;
}
