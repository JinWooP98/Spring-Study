package com.study.springstudy.springmvc;

import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void test() {
        Student s = new Student();
        s.setName("말똥이");
        s.setAge(12);
        s.setGrade(5);
    }

}