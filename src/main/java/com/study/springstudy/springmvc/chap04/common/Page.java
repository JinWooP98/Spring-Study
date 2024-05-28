package com.study.springstudy.springmvc.chap04.common;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Page {

    private int pageNo; // 클라이언트가 요청한 페이지 번호
    private int amount; // 클라이언트가 요청한 한페이지당 게시물 목록 수

    public Page() {
        this.pageNo = 1;
        this.amount = 5;
    }
    /*
        만약에 한 페이지에 게시물을 10개씩 렌더링한다면

        1페이지 -> LIMIT 0, 10
        2페이지 -> LIMIT 10, 10
        3페이지 -> LIMIT 20, 10

        만약에 한 페이지에 게시물을 n개씩 렌더링한다면

        1페이지 -> LIMIT 0, n
        2페이지 -> LIMIT n, n
        3페이지 -> LIMIT 2n, n
        M페이지 -> LIMIT (M -1) * N, N
     */
    private int getPageStart() {
        return (this.pageNo - 1) * this.amount;
    }

    public void setPageNo(int pageNo) {
        if(pageNo < 1 || pageNo > Integer.MAX_VALUE) {
            this.pageNo = 1;
            return;
        }
        this.pageNo = pageNo;
    }

    public void setAmount(int amount) {
        if(amount < 6 || amount > 60) {
            this.amount = 5;
            return;
        }
        this.amount = amount;
    }

}
