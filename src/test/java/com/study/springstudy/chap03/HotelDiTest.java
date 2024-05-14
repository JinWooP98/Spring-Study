package com.study.springstudy.chap03;

import com.study.springstudy.core.chap03.Hotel;
import com.study.springstudy.core.chap03.config.HotelManager;
import org.junit.jupiter.api.Test;

class HotelDiTest {

    @Test
    void diTest () {
        HotelManager hotelManager = new HotelManager();

        Hotel hotel = hotelManager.hotel2();
        hotel.inform();

    }

}