package com.example.iot.dao;

import com.example.iot.dao.Repository.AnalyzeRepository;
import com.example.iot.po.Analyze.Humidifier;
import com.example.iot.po.Analyze.HumidifierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnalyzeDao implements AnalyzeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void analyze(String deviceId,String code){  //用户的设备使用记录分析
        //1. 分析用户在湿度为多少时，喜欢开加湿器

        //若是空调，则operation是PowerOn才进行分析

    }

    public void analyzeAirConditioner(String deviceId,String code){ //析用户在几度以上/以下的时候喜欢开空调，并且是开几度
        String operation=code.split("_")[1];       //只有是PowerOn才执行此分析
        String sql = "select code from operation where deviceid=\"" + deviceId + "\" and code like \"%"+operation+"%\";";  //寻找该设备的所有PowerOn的操作记录
        List<String> codes=jdbcTemplate.queryForList(sql,String.class);

        int ColdCurTmpSum=0;
        int ColdSetTmpSum=0;
        int ColdCount=0;    //用户开冷气的次数
        int HotCurTmpSum=0;
        int HotSetTmpSum=0;
        int HotCount=0;     //用户开暖气的次数
        for(int i=0;i<codes.size();i++){
            String[] splitCodes=codes.get(i).split("_");
            int curTemperature=Integer.parseInt(splitCodes[2]);
            int setTemperature=Integer.parseInt(splitCodes[3]);
            if (curTemperature >= 20) {     //用户开的是冷气
                ColdCurTmpSum=ColdCurTmpSum+curTemperature;
                ColdSetTmpSum=ColdSetTmpSum+setTemperature;
                ColdCount++;
            } else {       //用户开的是暖气
                HotCurTmpSum=HotCurTmpSum+curTemperature;
                HotSetTmpSum=HotSetTmpSum+setTemperature;
                HotCount++;
            }
        }
        int coldCurTemperature=ColdCurTmpSum/ColdCount;
        int coldSetTemperature=ColdSetTmpSum/ColdCount;
        int hotCurTemperature=HotCurTmpSum/HotCount;
        int hotSetTemperature=HotSetTmpSum/HotCount;
        System.out.println("coldCurTemperature is "+coldCurTemperature);
        System.out.println("coldSetTemperature is "+coldSetTemperature);
        System.out.println("hotCurTemperature is "+hotCurTemperature);
        System.out.println("hotSetTemperature is "+hotSetTemperature);

    }

    public void analyzeHumidifier(String deviceId,String code){    //析用户在湿度为多少的时候喜欢开、关加湿器
        System.out.println("Start analyzeHumidifier");
        String state=code.split("_")[1];
        System.out.println("state is "+state);
        String sql=" select code from operation where deviceid=\""+deviceId+"\" and code like \"%"+state+"%\";";       //针对该id设备指定的on或off操作进行数据分析
        List<String> codes=jdbcTemplate.queryForList(sql,String.class);
        System.out.println("size is "+codes.size());
        List<Integer> Humidiness=new ArrayList<>();
        for(int i=0;i<codes.size();i++){
            String[] curHum=codes.get(i).split("_");
            System.out.println(curHum[1]);
            Humidiness.add(Integer.parseInt(curHum[2]));
        }
        int sum=0;
        for(int i=0;i<Humidiness.size();i++){
            sum=sum+Humidiness.get(i);
        }
        int result=0;
        result=sum/Humidiness.size();
        System.out.println("result is "+result);
        //不存在数据则插入，存在则更新
        if(state.equals("PowerOn"))
            sql=" insert into `analyze` (`deviceid`,`condition`,`state`) value (\""+deviceId+"\",\""+result+"\",\"1\") on duplicate key update `condition`=\""+result+"\";";
        else
            sql=" insert into `analyze` (`deviceid`,`condition`,`state`) value (\""+deviceId+"\",\""+result+"\",\"0\") on duplicate key update `condition`=\""+result+"\";";
        jdbcTemplate.update(sql);
    }
}
