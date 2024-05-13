package com.study.springstudy.chap03;

import com.study.springstudy.chap03.config.HotelManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelDiTest {

    @Test
    void diTest () {
        HotelManager hotelManager = new HotelManager();

        Hotel hotel = hotelManager.hotel2();
        hotel.inform();

    }

}