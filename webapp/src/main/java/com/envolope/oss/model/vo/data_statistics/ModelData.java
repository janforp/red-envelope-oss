package com.envolope.oss.model.vo.data_statistics;

import com.envolope.oss.model.ReMissionModuleStatistics;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jan on 2016/11/25.
 * 模块页面数据
 */
public class ModelData implements Serializable{

    private Integer model;
    private String modelTitle;
    private String startDate;
    private String endDate;

    private List<ReMissionModuleStatistics> vos;

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getModelTitle() {
        return modelTitle;
    }

    public void setModelTitle(String modelTitle) {
        this.modelTitle = modelTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<ReMissionModuleStatistics> getVos() {
        return vos;
    }

    public void setVos(List<ReMissionModuleStatistics> vos) {
        this.vos = vos;
    }
}
