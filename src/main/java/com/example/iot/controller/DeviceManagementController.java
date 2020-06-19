package com.example.iot.controller;

import com.example.iot.service.DeviceManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/device")
public class DeviceManagementController {
    @Autowired
    DeviceManagementService deviceManagementService;

    @RequestMapping("/addDevice")
    public int addDevice(@RequestParam(value = "type",required = true) String type, @RequestParam(value = "owner",required = true) String owner){
        int id=deviceManagementService.addDevice(type, owner);
        return id;
    }
    @RequestMapping("/deleteDevice")
    public ModelAndView deleteDevice(@RequestParam(value ="deviceId",required = true)String deviceId){
        boolean result=deviceManagementService.deleteDevice(deviceId);
        ModelAndView mav=new ModelAndView();
        mav.addObject("result",result);
        return mav;
    }
    @RequestMapping("operateDevice")
    public ModelAndView operateDevice(@RequestParam(value = "time",required = true)String time,@RequestParam(value = "code",required = true)String code,@RequestParam(value = "deviceId",required = true)String deviceId){
        boolean result=deviceManagementService.operateDevice(time, code, deviceId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("result",result);
        return mav;
    }
}
