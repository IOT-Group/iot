package com.example.iot.controller;

import com.example.iot.vo.Response;
import com.example.iot.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

/**
 * Controller for login
 * @date 2020年6月13日
 * @author Debonex
 */
@Controller
public class LoginController {

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Response login(@RequestBody User requestUser) {
        // escape html for xss
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        if (!Objects.equals("admin", username) || !Objects.equals("123123", requestUser.getPassword())) {
            String message = "账号密码错误";
            System.out.println("test");
            return new Response(400);
        } else {
            return new Response(200);
        }
    }
}
