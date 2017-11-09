package com.envolope.oss.service.data_statistic;

import com.envolope.oss.dao.ReUserDAO;
import com.envolope.oss.model.vo.user.statistics.RegisterNumPerHour;
import com.envolope.oss.model.vo.user.statistics.StatisticVo;
import com.envolope.oss.model.vo.user.statistics.XYVo;
import com.envolope.oss.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/11/11.
 * 用户注册统实时计(折线图)
 */
@Service
public class RegisterNumberService {
    @Autowired
    private ReUserDAO reUserDAO;

    /**
     * 获取某一天的用户注册数据[[1,2],[2,3]]:0-1点之间:2人注册,1-2点之间:3人注册
     * @param request
     * @param type      数据分类
     * @param date      日期,如:2016-11-11
     * @return
     */
    public StatisticVo getDataByDay(HttpServletRequest request,Integer type,String date){

        StatisticVo statisticVo = new StatisticVo();
        //统计指定日期的注册用户详情
        List<XYVo> vos = getVo(date,type);
        if (type == 1){
            statisticVo.setTitle(date+"注册详情");
            statisticVo.setXTitle("时间");
            statisticVo.setYTitle("注册人数");

        }else if (type == 2){
            statisticVo.setTitle(date+"活跃用户详情");
        }
        String jqPlotArray = parseToJqPlotArrayString(vos,type);
        statisticVo.setJqPlotArray(jqPlotArray);
        statisticVo.setVos(vos);

        return statisticVo;
    }

    /**
     * 把数据转换为jqplot格式的数组字符串
     * @param vos
     * @return
     */
    public String parseToJqPlotArrayString(List<XYVo> vos,Integer type){

        String jqPlotString="";

        if (type == 1){ //注册详情
            List<XYVo> register = new ArrayList<>();
            for (XYVo vo:vos){
                XYVo xyVo = new XYVo();
                xyVo.setX(vo.getX().substring(11));//日期只要小时
                xyVo.setY(vo.getY());
                register.add(xyVo);
            }
            for (XYVo vo: register){
                String one = "["+vo.getX()+","+vo.getY()+"]";
                if (jqPlotString == ""){
                    jqPlotString = jqPlotString + one;
                }else {
                    jqPlotString = jqPlotString+","+one;
                }
            }
        }else if (type == 2){//活跃用户详情

        }
        return jqPlotString;
    }

    /**
     * 根据某一日查询当天0点到当前时间的每个小时的注册用户数量
     * @param date
     * @return
     */
    public List<XYVo> getVo(String date,Integer type){

        List<XYVo> vos = new ArrayList<>();
        if (type == 1){//注册用户
            List<RegisterNumPerHour> registerNumPerHours = getRegisterNumPerHour(date);
            for (RegisterNumPerHour hour : registerNumPerHours){
                XYVo vo = new XYVo();
                vo.setX(hour.getHour());
                vo.setY(hour.getNum().toString());
                vos.add(vo);
            }
        }else if (type == 2){//活跃用户详情

        }
        return vos;
    }

    /**
     * 获取注册数量详情 小时-数量
     * @param date
     * @return
     */
    public List<RegisterNumPerHour> getRegisterNumPerHour(String date){

        List<RegisterNumPerHour> numPerHours = new ArrayList<>();

        for (int i=0;i<24;i++){
            String time ;
            if (i<10){
                time = date+" 0"+i;
            }else{
                time = date+" "+i;
            }
            RegisterNumPerHour registerNumPerHour = getNumByHour(time);
            numPerHours.add(registerNumPerHour);
        }
        return numPerHours;
    }

    /**
     * 获取某个小时,及注册用户的数据的对象
     * @param dateHour
     * @return
     */
    public RegisterNumPerHour getNumByHour(String dateHour){

        RegisterNumPerHour hour = new RegisterNumPerHour();
        hour.setHour(dateHour);
        hour.setNum(reUserDAO.getRegisterNumByHour(dateHour));

        return hour;
    }


}
