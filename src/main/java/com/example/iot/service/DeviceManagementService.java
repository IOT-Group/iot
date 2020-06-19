package com.example.iot.service;

import com.example.iot.dao.DeviceManagementDao;
import com.example.iot.dao.Repository.DeviceManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceManagementService {
    @Autowired
    DeviceManagementRepository deviceManagementRepository;

    public int addDevice(String type,String owner){
        return deviceManagementRepository.addDevice(type,owner);
    }
    public boolean deleteDevice(String deviceId){
        return deviceManagementRepository.deleteDevice(deviceId);
    }
    public boolean operateDevice(String time,String code,String deviceId){
        return deviceManagementRepository.operateDevice(time, code, deviceId);
    }
}
