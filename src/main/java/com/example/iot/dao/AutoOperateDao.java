package com.example.iot.dao;

import com.example.iot.dao.Repository.AutoOperateRepository;
import com.example.iot.po.AutoOperate.Analyze;
import com.example.iot.po.AutoOperate.AnalyzeMapper;
import com.example.iot.po.AutoOperate.Calendar;
import com.example.iot.po.AutoOperate.CalendarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class AutoOperateDao implements AutoOperateRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void autoOperate(String username,String time,String temperature,String humidity,String ownerState,String timeInterval){
        int timeInteger=Integer.parseInt(time);
        int timeIntervalInteger=Integer.parseInt(timeInterval);

        //判断该时间段内是否有日程表，有则进行操控设备
        String sql="select code from calendar where time+0<="+timeInteger+" and time+0>="+(timeInteger-timeIntervalInteger)+";";
        List<Calendar> calendars=new ArrayList<>();
        try {
            calendars= jdbcTemplate.query(sql,new CalendarMapper());
        }catch (EmptyResultDataAccessException e) {
            System.out.println("no device calendar atTime:"+time);
        }
        System.out.println(calendars.size());
        if(calendars.size()!=0){     //若该时间段内有日程表，则调用操控设备的接口
            for(int i=0;i<calendars.size();i++){
                Calendar calendar=calendars.get(i);

            }
        }

        //根据分析的用户数据，判断在该环境下是否需要自动操控设备。
        List<Analyze> analyzes=jdbcTemplate.query("select d.type,T.deviceid,T.condition,T.state from device d ,(select * from `analyze` a where a.deviceid in (select device.id from device,(select id from user where username=?)T1 where userId=T1.id))T where d.id=T.deviceid;",new AnalyzeMapper(),username);
        for(int i=0;i<analyzes.size();i++){
            Analyze analyze=analyzes.get(i);
            String type=analyze.getDeviceType().substring(0,1);
            if(type.equals("A")){   //空调
                if(ownerState.equals("1")) {
                    //若空调分析数据的condition是大于等于16度,且环境温度大于等于condition温度，则开冷气
                    //若空调分析数据的condition是小于16度,且环境温度小于等于condition温度，则开暖气
                    if ((Integer.parseInt(analyze.getCondition()) >= 16 && Integer.parseInt(temperature) >= Integer.parseInt(analyze.getCondition())) || (Integer.parseInt(analyze.getCondition()) < 16 && Integer.parseInt(temperature) <= Integer.parseInt(analyze.getCondition()))) {
                        String code = "A_PowerOn_" + analyze.getCondition() + "_" + analyze.getState();
                        System.out.println("code: "+code);

                    }
                }
            }
            else if(type.equals("H")){  //加湿器
                if(ownerState.equals("1")) {
                    if (Integer.parseInt(analyze.getState()) == 1 && Integer.parseInt(humidity) <= Integer.parseInt(analyze.getCondition())) {   //若环境湿度小于等于开加湿器的condition湿度，则开加湿器
                        String code = "H_PowerOn_" + analyze.getCondition();
                        System.out.println("code: "+code);

                    } else if (Integer.parseInt(analyze.getState()) == 0 && Integer.parseInt(humidity) >= Integer.parseInt(analyze.getCondition())) {    //若环境湿度大于等于关加湿器的condition湿度，则关加湿器
                        String code = "H_PowerOff_" + analyze.getCondition();
                        System.out.println("code: "+code);

                    }
                }
            }
            else if(type.equals("C")){  //窗帘
                if(Integer.parseInt(analyze.getState())==1 && Integer.parseInt(time)>=Integer.parseInt(analyze.getCondition())){       //若是要拉开窗帘，且时间大于等于condition时间，则拉开窗帘
                    String code="C_PowerOn";
                    System.out.println("code: "+code);
                }
                else if(Integer.parseInt(analyze.getState())==0 && Integer.parseInt(time)>=Integer.parseInt(analyze.getCondition())){   //若是要关窗帘，且时间大于等于condition时间，则关窗帘
                    String code="C_PowerOff";
                    System.out.println("code: "+code);
                }
            }

        }





    }
}