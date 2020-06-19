package com.example.iot.dao;

import com.example.iot.dao.Repository.CalendarRepository;
import com.example.iot.po.AutoOperate.Calendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalendarDaoTest {
    @Autowired
    CalendarRepository calendarRepository;

    @Test
    public void showCalendarTest(){
        List<Calendar> calendars=calendarRepository.showCalendar("benson");
        System.out.println(calendars.size());
    }

}
