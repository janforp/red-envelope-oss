package com.envolope.oss.model.vo.user.statistics;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jan on 16/11/11.
 * 用户数量统计,数据实体类
 */
public class StatisticVo implements Serializable {

    //集合形式的数据
    List<XYVo> vos;
    //满足jqplot格式的数据格式
    String jqPlotArray;
    //折线名称
    private String title;
    //类型
    private Integer type;
    //x坐标名字
    private String XTitle;
    //y坐标名字
    private String YTitle;

    public List<XYVo> getVos() {
        return vos;
    }

    public void setVos(List<XYVo> vos) {
        this.vos = vos;
    }

    public String getTitle() {
        return title;
    }

    public String getJqPlotArray() {
        return jqPlotArray;
    }

    public void setJqPlotArray(String jqPlotArray) {
        this.jqPlotArray = jqPlotArray;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getXTitle() {
        return XTitle;
    }

    public void setXTitle(String XTitle) {
        this.XTitle = XTitle;
    }

    public String getYTitle() {
        return YTitle;
    }

    public void setYTitle(String YTitle) {
        this.YTitle = YTitle;
    }
}
