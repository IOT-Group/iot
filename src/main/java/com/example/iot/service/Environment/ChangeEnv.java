package com.example.iot.service.Environment;

import com.example.iot.po.User.HomeCondition;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeEnv {
    public HomeCondition analyseInpput(String username, String type, String ins);
}
