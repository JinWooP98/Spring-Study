package com.study.springstudy.database.chap01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback  // DB를 망가뜨리지 않고 테스트 하는 방법// 실행을 시킨뒤 다시 롤백시킨다.
class SpringJdbcTest {

    @Autowired
    SpringJdbc springJdbc;

    // 각 테스트 전에 공통으로 실행할 코드(실행 된 뒤 다시 롤백)
    @BeforeEach
    void bulkInsert() {
        for (int i = 0; i < 100; i++) {
            Person p = new Person(i + 2000, "테스트맨" + i, 10);
            springJdbc.save(p);
        }
    }

    // 단위 테스트 프레임워크 : JUnit5
    // 테스트 == 단언 (Assertion)
    // 한번 성공한 테스트는 몇번을 돌려도 다 성공해야 한다.
    @Test
    @DisplayName("사람의 정보를 입력하면 데이터베이스에 반드시 저장되어야 한다.")
    void saveTest() {
        // gwt 패턴
        // given : 테스트에 주어질 데이터
        Person p = new Person(1000, "천천이", 19);

        // when : 테스트 상황
        int result = springJdbc.save(p);

        // then : 테스트 결과 단언
        assertEquals(1, result);
    }


    @Test
    @DisplayName("아이디가 주어지면 해당 아이디의 사람정보가 데이터베이스로부터 삭제되어야 한다.")
    void deleteTest() {
        //given
        long id = 77;
        //when
        boolean flag = springJdbc.delete(id);
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("새로운 Person 객체와 id가 주어지면 id에 해당하는 사람의 이름과 나이가 업데이트 되어야 한다.")
    void updateTest() {
        //given
        long id = 77;
        Person person = new Person(88, "뽀로로", 12);
        //when
        boolean flag = springJdbc.update(person, id);
        //then
        assertTrue(flag);
    }


    @Test
    @DisplayName("사람 정보를 전체 조회하면 결과 건수는 3건이며, 첫번째 사람의 이름은 이백이")
    void findAllTest() {
        //given

        //when
        List<Person> people = springJdbc.findAll();
        //then
        people.forEach(System.out::println);

        assertEquals(3, people.size());
        assertEquals("뽀로로", people.get(0).getPersonName());
    }
    
    @Test
    @DisplayName("사람 정보를 아이디로 단일조회시 아이디가 200인 사람의 이름은 이백이고, 나이는 19이다.")
    void findeOneTest() {
        //given
        long id = 200;
        //when
        Person p = springJdbc.findOne(id);
        //then
        assertNotNull(p);
        assertEquals("이백이", p.getPersonName());
        assertEquals(19, p.getPersonAge());
    }
}