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

    }

    public void analyzeHumidifier(String deviceId,String code){    //分析加湿器的数据
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
