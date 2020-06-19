package com.example.iot.dao.Repository;

import com.example.iot.po.User.HomeCondition;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AutoOperateRepository {
    public abstract void autoOperate(String username,String time, String temperature, String humidity, String ownerState,String timeInterval);

}
