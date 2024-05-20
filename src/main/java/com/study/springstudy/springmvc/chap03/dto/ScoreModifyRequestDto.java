package com.study.springstudy.springmvc.chap03.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ScoreModifyRequestDto {

    private int stuNum;
    private int kor;
    private int eng;
    private int math;

}
