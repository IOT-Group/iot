package com.example.iot.dao.Repository;

import com.example.iot.vo.AddDeviceResponse;
import com.example.iot.vo.DeviceVO;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceManagementRepository {
    public abstract AddDeviceResponse addDevice(String type, String owner);
    public abstract boolean deleteDevice(String deviceId);
    public abstract boolean operateDevice(String time,String code,String deviceId);
}
