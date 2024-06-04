package com.study.springstudy.springmvc.chap04.common;

import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
public class Search extends Page {

    // 검색어, 검색조건
    private String keyword, type;

    // type은 검색조건을 안고를수가 없어 null이 될리 없지만
    // keyword는 아무것도 입력을 안 할 경우 null이 뜰 수도 있어 생성자로 빈 문자열을 넣어줌.
    public Search() {
        this.keyword = "";
    }
}
