package com.study.springstudy.springmvc.chap05.mapper;

import com.study.springstudy.springmvc.chap05.entity.Auth;
import com.study.springstudy.springmvc.chap05.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    PasswordEncoder encoder;

    @Test
    @DisplayName("회원가입에 성공해야 한다.")
    void saveTest() {
        //given
        Member member = Member.builder()
                .account("kuromi")
                .password("abc1234!")
                .name("쿠로미")
                .email("kuromi@gmail.com")
                .build();
        //when
        boolean flag = memberMapper.save(member);
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("멤버 조회에 성공해야 한다.")
    void findOneTest() {
        //given

        //when
        Member kuromi = memberMapper.findOne("kuromi");
        //then
        assertEquals("쿠로미", kuromi.getName());
    }

    @Test
    @DisplayName("계정 중복 조회에 성공ㅇ해야 한다.")
    void existsTest() {
        //given

        //when
        boolean b = memberMapper.existsById("account", "kuromi");
        //then
        assertTrue(b);
    }

    @Test
    @DisplayName("평문의 암호를 인코딩하여야 한다.")
    void encodingTest() {
        //given
        String rawPassword = "abc1234";
        //when
        String encodedPassword = encoder.encode(rawPassword);
        //then
        System.out.println(encodedPassword);
    }
}