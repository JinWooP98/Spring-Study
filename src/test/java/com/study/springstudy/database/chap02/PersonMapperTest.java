package com.study.springstudy.database.chap02;

import com.study.springstudy.database.chap01.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonMapperTest {

    @Autowired
    PersonMapper personMapper;

    @Test
    @DisplayName("mybatis mapper로 사람 정보를 등록한다.")
    void saveTest() {
        //given
        Person p = new Person(9999, "마바맨", 59);
        //when
        boolean result = personMapper.save(p);
        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("아이디로 사람 정보 삭제한다.")
    void deleteTest() {
        //given
        long id = 9999;
        //when
        boolean result = personMapper.delete(id);
        //then
        assertTrue(result);
    }
    
    @Test
    @DisplayName("아이디가 200인 사람의 정보를 수정한다.")
    void updateTest() {
        //given
        Person p = new Person(200, "루피", 25);
        //when
        boolean update = personMapper.update(p);
        //then
        assertTrue(update);
    }
    
    @Test
    @DisplayName("전체조회하면 결과 건수가 3건이다")
    void findAllTest() {
        //given
        List<Person> people = personMapper.findAll();
        //when
        people.forEach(System.out::println);
        //then
        assertEquals(3, people.size());
    }

    @Test
    @DisplayName("id로 사람 정보를 개별 조회한다.")
    void findOneTest() {
        //given
        long id = 200;
        //when
        Person p = personMapper.findOne(id);
        //then

        System.out.println("p = " + p);

        assertEquals("루피", p.getPersonName());
    }

    @Test
    @DisplayName("사람수와 이름리스트를 조회한다.")
    void findNamesTest() {
        //given

        //when
        List<String> names = personMapper.findNames();
        int count = personMapper.count();
        //then
        names.forEach(System.out::println);
        System.out.println();
        System.out.println("count = " + count);
    }



}