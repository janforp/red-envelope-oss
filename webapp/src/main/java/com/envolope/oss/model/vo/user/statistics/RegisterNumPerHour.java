package com.envolope.oss.model.vo.user.statistics;

import java.io.Serializable;

/**
 * Created by Jan on 16/11/11.
 * 每小时的注册数
 */
public class RegisterNumPerHour implements Serializable {

    private String hour;
    private Integer num;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
