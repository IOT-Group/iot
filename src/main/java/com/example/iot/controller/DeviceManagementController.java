package com.example.iot.controller;

import com.example.iot.service.DeviceManagementService;
import com.example.iot.vo.Response;
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
        return deviceManagementService.addDevice(type, owner);
    }
    @RequestMapping("/deleteDevice")
    public Response deleteDevice(@RequestParam(value ="deviceId",required = true)String deviceId){
        boolean result=deviceManagementService.deleteDevice(deviceId);
        return Response.ResponseSuccess(result);
    }
    @RequestMapping("/operateDevice")
    public Response operateDevice(@RequestParam(value = "time",required = true)String time,@RequestParam(value = "code",required = true)String code,@RequestParam(value = "deviceId",required = true)String deviceId){
        boolean result=deviceManagementService.operateDevice(time, code, deviceId);
        return Response.ResponseSuccess(result);
    }
}