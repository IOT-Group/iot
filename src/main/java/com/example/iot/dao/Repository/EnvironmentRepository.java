package com.example.iot.dao.Repository;

import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentRepository {
    public void changeDegree(int id,int degree);
    public void changeHumidity(int id,int humidity);
    public void changeHome(int id,int ownerState);
    public void changeTime(int id,String time);
}
