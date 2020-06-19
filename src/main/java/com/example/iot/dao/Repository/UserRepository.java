package com.example.iot.dao.Repository;


import com.example.iot.po.User.Device;
import com.example.iot.po.User.HomeCondition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    public abstract boolean register(String username,String password);
    public abstract boolean login(String username,String password);
    public abstract List<Device> getUserDevice(String username);

}
