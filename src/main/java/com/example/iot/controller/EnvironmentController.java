package com.example.iot.controller;

import com.example.iot.service.Environment.ChangeEnv;
import com.example.iot.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Environment")
public class EnvironmentController {
    @Autowired
    ChangeEnv changeEnv;

    @RequestMapping("/change")
    public Response changeEnv(@RequestParam(value = "username",required = true) String username, @RequestParam(value = "type",required = true) String type, @RequestParam(value = "ins",required = true) String ins){
        return Response.ResponseSuccess(changeEnv.analyseInpput(username,type,ins));
    }

    //提供查询环境的接口

}
