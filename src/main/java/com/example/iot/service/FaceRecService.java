package com.example.iot.service;

import com.example.iot.service.Recognition.FaceRecognition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FaceRecService implements FaceRecognition {
    private int Lowtime=180;
    private int Hightemp=30;
    private int LowHum=30;
    @Autowired
    JdbcTemplate jdbcTemplate;




    @Override
    public void recognition_string(String input,int id) {
        //出家门情况
        if(input=="0"){
            //调用关闭空调，电视机，电灯，音箱，加湿器
        }

        //进家门情况
        else if(input=="1"){
            //开灯

            //根据时间段判断是否打开电灯、窗帘，根据温度判断是否开空调，根据湿度判断是否开加湿器。
            String sql="SELECT time FROM environment where id=\""+id+"\";";
            List<String> time=jdbcTemplate.queryForList(sql,String.class);

            //判断时间段
            if(Integer.parseInt(time.get(0))>Lowtime){
                //拉开窗帘
            }

            sql="SELECT temperature FROM environment where id=\""+id+"\";";
            List<String> temp=jdbcTemplate.queryForList(sql,String.class);
            //判断温度
            if(Integer.parseInt(temp.get(0))>Hightemp){
                //打开空调
            }

            sql="SELECT humidity FROM environment where id=\""+id+"\";";
            List<String> hum=jdbcTemplate.queryForList(sql,String.class);
            //判断湿度
            if(Integer.parseInt(hum.get(0))<LowHum){
                //打开加湿器
            }
        }
    }

    public void adjustTime(int time){
        this.Lowtime=time;
    }

    public void adjustTemp(int temp){
        this.Hightemp=temp;
    }

    public void adjustHum(int humidity){
        this.LowHum=humidity;
    }
}
