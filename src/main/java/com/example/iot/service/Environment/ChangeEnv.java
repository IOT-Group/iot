package com.example.iot.service.Environment;

import org.springframework.stereotype.Repository;

@Repository
public interface ChangeEnv {
    public void analyseIns(int id,int type,int instruction);
}
