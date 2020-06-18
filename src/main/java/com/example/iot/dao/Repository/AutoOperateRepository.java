package com.example.iot.dao.Repository;

import java.util.Date;

public interface AutoOperateRepository {
    public abstract void autoOperate(String userId,String time, String temperature, String humidity, String ownerState,String timeInterval);
}
