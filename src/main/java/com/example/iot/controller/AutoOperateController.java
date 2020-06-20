package com.example.iot.controller;

import com.example.iot.po.User.HomeCondition;
import com.example.iot.service.AutoOperateService;
import com.example.iot.vo.Environment;
import com.example.iot.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AutoOperateController {
    @Autowired
    AutoOperateService autoOperateService;

    @CrossOrigin
    @PostMapping(value = "api/sendEnvironment")
    @ResponseBody
    public Response autoOperate(@RequestBody Environment environment){
        try {

            HomeCondition homeCondition=autoOperateService.autoOperate(environment.getUsername(), environment.getTime(), environment.getTemperature(), environment.getHumidity(), environment.getOwnerState(), environment.getTimeInterval());
            com.example.iot.po.User.Environment e=homeCondition.getEnvironment();
            System.out.println("time is "+e.getTime());
            return Response.ResponseSuccess(homeCondition);
        }catch (Exception e){
            return Response.ResponseFail("自动操作失败");
        }

    }
}
