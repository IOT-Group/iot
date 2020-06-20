package com.example.iot.service;

import com.example.iot.dao.DeviceManagementDao;
import com.example.iot.dao.Repository.AnalyzeRepository;
import com.example.iot.dao.Repository.DeviceManagementRepository;
import com.example.iot.vo.AddDeviceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceManagementService {
    @Autowired
    DeviceManagementRepository deviceManagementRepository;

    @Autowired
    AnalyzeRepository analyzeRepository;

    public boolean initialize(){
        return deviceManagementRepository.initialize();
    }

    public AddDeviceResponse addDevice(String type, String owner){
        return deviceManagementRepository.addDevice(type,owner);
    }
    public boolean deleteDevice(String deviceId){
        return deviceManagementRepository.deleteDevice(deviceId);
    }
    public boolean operateDevice(String time,String code,String deviceId){

        boolean result= deviceManagementRepository.operateDevice(time, code, deviceId);
        analyzeRepository.analyze(deviceId,code);
        return result;
    }
}
