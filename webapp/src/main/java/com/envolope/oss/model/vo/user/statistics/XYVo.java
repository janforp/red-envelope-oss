package com.envolope.oss.model.vo.user.statistics;

import java.io.Serializable;

/**
 * Created by Jan on 16/11/11.
 * x,y坐标的数据
 */
public class XYVo implements Serializable {
    //x坐标
    private String x;
    //y坐标
    private String y;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
