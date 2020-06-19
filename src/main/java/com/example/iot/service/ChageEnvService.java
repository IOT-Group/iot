package com.example.iot.service;

import com.example.iot.dao.Repository.EnvironmentRepository;
import com.example.iot.po.User.Environment;
import com.example.iot.po.User.HomeCondition;
import com.example.iot.service.Environment.ChangeEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChageEnvService implements ChangeEnv {
    @Autowired
    EnvironmentRepository environmentRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;


    private Environment analyseIns(int id, int type , int instruction) {
        //type 0=温度，1=湿度,2=主人在家与否
        //instruction
        // type0时，int值为温度
        // type1时，int值为湿度
        // type2时，0在家，1不在家
        Environment environment=new Environment();
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

    @Override
    public HomeCondition analyseInpput(String username, String type, String ins) {
        String sql="SELECT id from user where username='"+username+"'";
        List<String> user=jdbcTemplate.queryForList(sql,String.class);

        int id=Integer.valueOf(user.get(0));
        return analyseIns(id,Integer.valueOf(type),Integer.valueOf(ins));


    }
}
