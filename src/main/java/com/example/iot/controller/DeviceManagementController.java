package com.example.iot.controller;

import com.example.iot.service.DeviceManagementService;
import com.example.iot.vo.AddDeviceResponse;
import com.example.iot.vo.DeviceVO;
import com.example.iot.vo.Operation;
import com.example.iot.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("api/device")
public class DeviceManagementController {
    @Autowired
    DeviceManagementService deviceManagementService;

    @CrossOrigin
    @PostMapping(value = "api/addDevice")
    @ResponseBody
    public AddDeviceResponse addDevice(@RequestBody DeviceVO deviceVO){
        return deviceManagementService.addDevice(deviceVO.getType(), deviceVO.getUsername());
    }

//    public int addDevice(@RequestParam(value = "type",required = true) String type, @RequestParam(value = "owner",required = true) String owner){
//        return deviceManagementService.addDevice(type, owner);
//    }
    @CrossOrigin
    @PostMapping(value = "api/deleteDevice")
    @ResponseBody
    public Response deleteDevice(@RequestParam(value ="deviceId",required = true)String deviceId){
        boolean result=deviceManagementService.deleteDevice(deviceId);
        return Response.ResponseSuccess(result);
    }

//    @RequestMapping("/deleteDevice")
//  public Response deleteDevice(@RequestParam(value ="deviceId",required = true)String deviceId){
//        boolean result=deviceManagementService.deleteDevice(deviceId);
//        return Response.ResponseSuccess(result);
//    }

    @CrossOrigin
    @PostMapping(value = "api/operateDevice")
    @ResponseBody
    public Response operateDevice(@RequestBody Operation operation){
        boolean result=deviceManagementService.operateDevice(operation.getTime(),operation.getCode(),operation.getDeviceId());
        return Response.ResponseSuccess(result);
    }
}

//    @RequestMapping("/operateDevice")
//    public Response operateDevice(@RequestParam(value = "time",required = true)String time,@RequestParam(value = "code",required = true)String code,@RequestParam(value = "deviceId",required = true)String deviceId){
//        boolean result=deviceManagementService.operateDevice(time, code, deviceId);
//        return Response.ResponseSuccess(result);
//    }
//}
