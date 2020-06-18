package com.example.iot.dao.Repository;

public interface AutoOperateRepository {
    public abstract void autoOperate(String time,String temperature,String humidity,String ownerState);
}
