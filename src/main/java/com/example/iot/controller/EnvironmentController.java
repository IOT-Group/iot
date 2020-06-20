package com.example.iot.controller;

import com.example.iot.service.Environment.ChangeEnv;
import com.example.iot.vo.ChangeEnvVO;
import com.example.iot.vo.Response;
import com.example.iot.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EnvironmentController {
    @Autowired
    ChangeEnv changeEnv;

    @CrossOrigin
    @PostMapping(value = "api/Environment/change")
    @ResponseBody
    public Response changeEnv(@RequestBody ChangeEnvVO changeEnvVO){
        return Response.ResponseSuccess(changeEnv.analyseInpput(changeEnvVO.getUsername(),changeEnvVO.getType(),changeEnvVO.getIns()));
    }

    //提供查询环境的接口
    @CrossOrigin
    @PostMapping(value = "api/Environment/get")
    @ResponseBody
    public Response getEnv(@RequestBody User user){
        return Response.ResponseSuccess(changeEnv.getEnv(user.getUsername()));
    }

}
