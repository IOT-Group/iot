package com.example.iot.controller;

import com.example.iot.service.Environment.ChangeEnv;
import com.example.iot.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EnvironmentController {
    @Autowired
    ChangeEnv changeEnv;

    @CrossOrigin
    @PostMapping(value = "Environment/change")
    @ResponseBody
    public Response changeEnv(@RequestParam(value = "username",required = true) String username, @RequestParam(value = "type",required = true) String type, @RequestParam(value = "ins",required = true) String ins){
        return Response.ResponseSuccess(changeEnv.analyseInpput(username,type,ins));
    }

    //提供查询环境的接口
    @CrossOrigin
    @PostMapping(value = "Environment/get")
    @ResponseBody
    public Response getEnv(@RequestParam(value = "username",required = true)String username){
        return Response.ResponseSuccess(getEnv(username));
    }

}
