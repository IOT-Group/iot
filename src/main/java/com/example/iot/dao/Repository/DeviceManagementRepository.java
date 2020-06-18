package com.example.iot.dao.Repository;

import org.springframework.stereotype.Repository;

@Repository
public interface DeviceManagementRepository {
    public abstract int addDevice(String type,String owner);
    public abstract int deleteDevice(String deviceId);
    public abstract int operateDevice(String time,String code,String deviceId);
}
