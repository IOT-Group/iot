package com.example.iot.service;

import com.example.iot.dao.Repository.AutoOperateRepository;
import com.example.iot.po.User.HomeCondition;
import com.example.iot.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoOperateService {
    @Autowired
    AutoOperateRepository autoOperateRepository;


    public HomeCondition autoOperate(String username, String time, String temperature, String humidity, String ownerState, String timeInterval){
        try {
            autoOperateRepository.autoOperate(username, time, temperature, humidity, ownerState, timeInterval);
        }catch (Exception e){
            e.printStackTrace();
        }
        return autoOperateRepository.getHomeCondition(username);
    }
}
