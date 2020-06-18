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
    public void analyze(String deviceId,String time,String code){  //用户的设备使用记录分析
        String deviceType=code.split("_")[0];
        if(deviceType.equals("C"))
            analyzeCurtain(deviceId,code);
        else if(deviceType.equals("A"))
            analyzeAirConditioner(deviceId,code);
        else if(deviceType.equals("H"))
            analyzeHumidifier(deviceId,code);

    }

    public void analyzeCurtain(String deviceId,String code){    //分析窗帘
        String operation=code.split("_")[1];        //可能是PowerOn或PowerOff
        String sql="select time from operation where deviceid=\""+deviceId+"\" and code like \"%"+operation+"%\";";
        List<Integer> times=jdbcTemplate.queryForList(sql, Integer.class);
        int sum=0;
        for(int i=0;i<times.size();i++){
            sum=sum+times.get(i);
        }
        int resultTime=0;
        if(times.size()!=0)
            resultTime=sum/times.size();
        if(operation.equals("PowerOn")){    //开窗帘
            int count=jdbcTemplate.queryForObject("select count(*) from `analyze` where `deviceid`=? and `state`=?",Integer.class,deviceId,"1");
            if(count==0){   //没有数据则插入数据
                jdbcTemplate.update("insert into `analyze` (`deviceid`,`condition`,`state`) values (?,?,?)",deviceId,resultTime,"1");
            }
            else{   //有则更新
                jdbcTemplate.update("update `analyze` set `deviceid`=?,`condition`=?,`state`=? where `deviceid`=? and `state`=?;",deviceId,resultTime,"1",deviceId,"1");
            }
        }
        else{   //关窗帘
            int count=jdbcTemplate.queryForObject("select count(*) from `analyze` where `deviceid`=? and `state`=?",Integer.class,deviceId,"0");
            if(count==0){   //没有数据则插入数据
                jdbcTemplate.update("insert into `analyze` (`deviceid`,`condition`,`state`) values (?,?,?)",deviceId,resultTime,"0");
            }
            else{   //有则更新
                jdbcTemplate.update("update `analyze` set `deviceid`=?,`condition`=?,`state`=? where `deviceid`=? and `state`=?;",deviceId,resultTime,"0",deviceId,"0");
            }
        }
    }

    public void analyzeAirConditioner(String deviceId,String code){ //析用户在几度以上/以下的时候喜欢开空调，并且是开几度
        String operation=code.split("_")[1];
        if(operation.equals("PowerOn")) {       //只有是PowerOn才执行此分析
            String sql = "select code from operation where deviceid=\"" + deviceId + "\" and code like \"%" + operation + "%\";";  //寻找该设备的所有PowerOn的操作记录
            List<String> codes = jdbcTemplate.queryForList(sql, String.class);

            int ColdCurTmpSum = 0;
            int ColdSetTmpSum = 0;
            int ColdCount = 0;    //用户开冷气的次数
            int HotCurTmpSum = 0;
            int HotSetTmpSum = 0;
            int HotCount = 0;     //用户开暖气的次数
            for (int i = 0; i < codes.size(); i++) {
                String[] splitCodes = codes.get(i).split("_");
                int curTemperature = Integer.parseInt(splitCodes[2]);
                int setTemperature = Integer.parseInt(splitCodes[3]);
                if (curTemperature >= 16) {     //用户开的是冷气 (16度以上是开冷气，以下则开暖气)
                    ColdCurTmpSum = ColdCurTmpSum + curTemperature;
                    ColdSetTmpSum = ColdSetTmpSum + setTemperature;
                    ColdCount++;
                } else {       //用户开的是暖气
                    HotCurTmpSum = HotCurTmpSum + curTemperature;
                    HotSetTmpSum = HotSetTmpSum + setTemperature;
                    HotCount++;
                }
            }
            if (ColdCount != 0) {
                int coldCurTemperature = ColdCurTmpSum / ColdCount;
                int coldSetTemperature = ColdSetTmpSum / ColdCount;
                int count = jdbcTemplate.queryForObject("select count(*) from `analyze` where `deviceid`=? and `condition`>=16", Integer.class, deviceId);
                if (count == 0) {   //插入新数据
                    sql = "insert into `analyze` (`deviceid`,`condition`,`state`) values (\"" + deviceId + "\",\"" + coldCurTemperature + "\",\"" + coldSetTemperature + "\");";
                    jdbcTemplate.update(sql);
                } else {
                    jdbcTemplate.update("update `analyze` set `deviceid`=?,`condition`=?,`state`=? where  `deviceid`=? and `condition`>=16;", deviceId, coldCurTemperature, coldSetTemperature, deviceId);
                }
            }
            if (HotCount != 0) {
                int hotCurTemperature = HotCurTmpSum / HotCount;
                int hotSetTemperature = HotSetTmpSum / HotCount;
                int count = jdbcTemplate.queryForObject("select count(*) from `analyze` where `deviceid`=? and `condition`<16", Integer.class, deviceId);
                if (count == 0) {   //插入新数据
                    sql = "insert into `analyze` (`deviceid`,`condition`,`state`) values (\"" + deviceId + "\",\"" + hotCurTemperature + "\",\"" + hotSetTemperature + "\");";
                    jdbcTemplate.update(sql);
                } else {
                    jdbcTemplate.update("update `analyze` set `deviceid`=?,`condition`=?,`state`=? where `deviceid`=? and `condition`<16;", deviceId, hotCurTemperature, hotSetTemperature, deviceId);
                }
            }
        }
//        System.out.println("coldCurTemperature is "+coldCurTemperature);
//        System.out.println("coldSetTemperature is "+coldSetTemperature);
//        System.out.println("hotCurTemperature is "+hotCurTemperature);
//        System.out.println("hotSetTemperature is "+hotSetTemperature);

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
        if(Humidiness.size()!=0)
            result=sum/Humidiness.size();
        System.out.println("result is "+result);
        if(state.equals("PowerOn")){
            int count=jdbcTemplate.queryForObject("select count(*) from `analyze` where `deviceid`=? and `state`=?",Integer.class,deviceId,"1");
            if(count==0){   //插入数据
                sql="insert into `analyze` (`deviceid`,`condition`,`state`) values (\""+deviceId+"\",\""+result+"\",\"1\");";
                jdbcTemplate.update(sql);
            }
            else{   //更新数据
                jdbcTemplate.update("update `analyze` set `deviceid`=?,`condition`=?,`state`=? where `deviceid`=? and `state`=?;",deviceId,result,"1",deviceId,"1");
            }
            //sql=" insert into `analyze` (`deviceid`,`condition`,`state`) value (\""+deviceId+"\",\""+result+"\",\"1\") on duplicate key update `condition`=\""+result+"\";";
        }
        else{
            int count=jdbcTemplate.queryForObject("select count(*) from `analyze` where `deviceid`=? and `state`=?",Integer.class,deviceId,"0");
            if(count==0){   //插入数据
                sql="insert into `analyze` (`deviceid`,`condition`,`state`) values (\""+deviceId+"\",\""+result+"\",\"0\");";
                jdbcTemplate.update(sql);
            }
            else{   //更新数据
                jdbcTemplate.update("update `analyze` set `deviceid`=?,`condition`=?,`state`=? where `deviceid`=? and `state`=?;",deviceId,result,"0",deviceId,"0");
            }
        }
           // sql=" insert into `analyze` (`deviceid`,`condition`,`state`) value (\""+deviceId+"\",\""+result+"\",\"0\") on duplicate key update `condition`=\""+result+"\";";
        //jdbcTemplate.update(sql);
    }
}
