package com.example.iot.controller;

import com.example.iot.po.User.Device;
import com.example.iot.service.UserService;
import com.example.iot.vo.LoginResponse;
import com.example.iot.vo.Response;
import com.example.iot.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * Controller for login
 * @date 2020年6月13日
 * @author Debonex
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public LoginResponse login(@RequestBody User requestUser, HttpSession session) {
        // escape html for xss
        String username = requestUser.getUsername();
        String password = requestUser.getPassword();
        username = HtmlUtils.htmlEscape(username);
        password = HtmlUtils.htmlEscape(password);

        if (!userService.login(username,password)) {
            return new LoginResponse(400);
        } else {
            session.setAttribute("user",requestUser);
            return new LoginResponse(200);
        }
    }

    public Response register(@RequestParam(value = "username",required = true)String username,@RequestParam(value="password",required = true)String password){
        return Response.ResponseSuccess(userService.register(username,password));
    }


    @RequestMapping("/getDevices")
    public Response getUserDevice(@RequestParam(value = "username",required = true)String username){
        List<Device> devices=userService.getDevices(username);
        return Response.ResponseSuccess(devices);
    }
}
