package com.study.springstudy.springmvc.chap05.entity;

import lombok.*;

@Getter @ToString
@AllArgsConstructor
public enum Auth {

    COMMON("일반회원", 1),
    ADMIN("관리자회원", 2);

    private String desc;
    private int authNumber;
}
