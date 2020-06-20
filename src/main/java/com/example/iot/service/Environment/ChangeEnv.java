package com.example.iot.service.Environment;

import com.example.iot.po.User.Environment;
import com.example.iot.po.User.HomeCondition;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeEnv {
    public Environment analyseInpput(String username, String type, String ins);
    public Environment getEnv(String username);
}
