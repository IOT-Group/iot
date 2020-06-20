package com.example.iot.service;

import com.example.iot.dao.Repository.DeviceManagementRepository;
import com.example.iot.dao.Repository.UserRepository;
import com.example.iot.dao.UserDao;
import com.example.iot.po.User.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * provide service about users
 * @author Debonex
 * @date 2020年6月17日
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DeviceManagementRepository deviceManagementRepository;

    public boolean register(String username,String password){
        return userRepository.register(username,password);
    }

    public boolean login(String username,String password){
        boolean result= userRepository.login(username,password);
        if(result)
            deviceManagementRepository.initialize();
        return result;
    }

    public List<Device> getDevices(String username){
        return userRepository.getUserDevice(username);
    }
}
