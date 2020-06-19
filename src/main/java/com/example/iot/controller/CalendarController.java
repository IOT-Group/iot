package com.example.iot.controller;

import com.example.iot.service.CalendarService;
import com.example.iot.vo.Operation;
import com.example.iot.vo.Response;
import com.example.iot.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalendarController {
    @Autowired
    CalendarService calendarService;

    @CrossOrigin
    @PostMapping(value = "api/addCalendar")
    @ResponseBody
    public Response addCalendar(@RequestBody Operation operation){
        boolean result= calendarService.addCalendar(operation.getDeviceId(),operation.getTime(),operation.getCode());
        return Response.ResponseSuccess(result);
    }

    @CrossOrigin
    @PostMapping(value = "api/deleteCalendar")
    @ResponseBody
    public Response deleteCalendar(@RequestBody Operation operation) {
        return Response.ResponseSuccess(
                calendarService.deleteCalendar(operation.getDeviceId(),operation.getTime(),operation.getCode()));
    }

    @CrossOrigin
    @PostMapping(value = "api/showCalendar")
    @ResponseBody
    public Response showCalendar(@RequestBody User requestUser){
        return Response.ResponseSuccess(calendarService.showCalendar(requestUser.getUsername()));
    }
}
