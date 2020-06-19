package com.example.iot.dao.Repository;

import org.springframework.stereotype.Repository;

@Repository
public interface DeviceManagementRepository {
    public abstract int addDevice(String type,String owner);
    public abstract boolean deleteDevice(String deviceId);
    public abstract boolean operateDevice(String time,String code,String deviceId);
}
