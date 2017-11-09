package com.envolope.oss.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/9/27.
 * 佣金提现 查询参数
 */
public class CommissionSelectParamVo implements Serializable{

    private Integer status;

    private String  startTime;

    private String  endTime;

    private String  cellphone;

    private Integer pageSize;

    private Integer pageNum;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
