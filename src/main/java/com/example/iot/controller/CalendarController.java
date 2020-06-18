package com.example.iot.controller;

import com.example.iot.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
    @Autowired
    CalendarService calendarService;

    @RequestMapping("/addCalendar")
    public ModelAndView addCalendar(@RequestParam(value = "deviceId",required = true) String deviceId, @RequestParam(value = "time",required = true) String time, @RequestParam(value = "code",required = true) String code){
        boolean result= calendarService.addCalendar(deviceId,time,code);
        ModelAndView mav = new ModelAndView();
        //mav.setViewName("authorDetail");
        mav.addObject("result",result);
        return mav;
    }

    @RequestMapping("/deleteCalendar")
    public void deleteCalendar(@RequestParam(value = "deviceId",required = true) String deviceId, @RequestParam(value = "time",required = true) String time, @RequestParam(value = "code",required = true) String code) {
        calendarService.deleteCalendar(deviceId, time, code);
    }
}
