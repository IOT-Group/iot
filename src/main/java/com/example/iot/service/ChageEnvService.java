package com.example.iot.service;

import com.example.iot.dao.Repository.EnvironmentRepository;
import com.example.iot.service.Environment.ChangeEnv;
import org.springframework.beans.factory.annotation.Autowired;

public class ChageEnvService implements ChangeEnv {
    @Autowired
    EnvironmentRepository environmentRepository;

    @Override
    public void analyseIns(int id,int type ,int instruction) {
        //type 0=温度，1=湿度,2=主人在家与否
        //instruction
        // type0时，int值为温度
        // type1时，int值为湿度
        // type2时，0在家，1不在家
        if(type==0){
            environmentRepository.changeDegree(id,instruction);
        }
        else if(type==1){
            environmentRepository.changeHumidity(id,instruction);
        }
        else if(type==2){
            environmentRepository.changeHome(id,instruction);
        }

    }
}
