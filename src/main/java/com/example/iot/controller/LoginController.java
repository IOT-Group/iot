package com.example.iot.controller;

import com.example.iot.service.UserService;
import com.example.iot.vo.Response;
import com.example.iot.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Controller for login
 * @date 2020年6月13日
 * @author Debonex
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Response login(@RequestBody User requestUser, HttpSession session) {
        // escape html for xss
        String username = requestUser.getUsername();
        String password = requestUser.getPassword();
        username = HtmlUtils.htmlEscape(username);
        password = HtmlUtils.htmlEscape(password);

        if (!userService.login(username,password)) {
            return new Response(400);
        } else {
            session.setAttribute("user",requestUser);
            return new Response(200);
        }
    }
}
