package com.envolope.oss.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jan on 16/6/30.
 *
 * 渠道查询 列表
 */
public class DayStatisticsVo implements Serializable {

    //日期(2016-6-28)
    private String          time;
    //当日新增用户
    private Integer         dayNewUser;
    //当日新增用户总充值
    private BigDecimal      dayNewUserCharge;
    //总充值(所用用户)
    private BigDecimal      dayTotalCharge;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getDayNewUser() {
        return dayNewUser;
    }

    public void setDayNewUser(Integer dayNewUser) {
        this.dayNewUser = dayNewUser;
    }

    public BigDecimal getDayNewUserCharge() {
        return dayNewUserCharge;
    }

    public void setDayNewUserCharge(BigDecimal dayNewUserCharge) {
        this.dayNewUserCharge = dayNewUserCharge;
    }

    public BigDecimal getDayTotalCharge() {
        return dayTotalCharge;
    }

    public void setDayTotalCharge(BigDecimal dayTotalCharge) {
        this.dayTotalCharge = dayTotalCharge;
    }
}
