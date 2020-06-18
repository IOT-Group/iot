package com.example.iot.service.Environment;

import org.springframework.stereotype.Repository;

@Repository
public interface ChangeEnv {
    public void analyseInpput(String username,String type,String ins);
}
