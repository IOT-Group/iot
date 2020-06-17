package com.example.iot.service;

import com.example.iot.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * provide service about users
 * @author Debonex
 * @date 2020年6月17日
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public boolean login(String username,String password){
        return userDao.login(username,password);
    }
}
