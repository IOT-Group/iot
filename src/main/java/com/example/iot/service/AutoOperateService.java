package com.example.iot.service;

import com.example.iot.dao.Repository.AutoOperateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoOperateService {
    @Autowired
    AutoOperateRepository autoOperateRepository;

    public void autoOperate(String username,String time,String temperature,String humidity,String ownerState,String timeInterval){
        autoOperateRepository.autoOperate(username,time,temperature,humidity,ownerState,timeInterval);
    }
}
