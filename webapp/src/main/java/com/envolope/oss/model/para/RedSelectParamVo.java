package com.envolope.oss.model.para;

/**
 * Created by Jan on 16/8/17.
 * 红包查询条件参数
 */
public class RedSelectParamVo {

    private String      redName;
    private String      merchantName;
    private Integer     redSort;
    private Integer     redStatus;
    private Integer     showOrNot;
    private String      startTime;
    private Long        startTimeStamp;
    private String      endTime;
    private Long        endTimeStamp;
    private Integer     pageSize;
    private Integer     pageNum;

    public String getRedName() {
        return redName;
    }

    public void setRedName(String redName) {
        this.redName = redName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getRedSort() {
        return redSort;
    }

    public void setRedSort(Integer redSort) {
        this.redSort = redSort;
    }

    public Integer getRedStatus() {
        return redStatus;
    }

    public void setRedStatus(Integer redStatus) {
        this.redStatus = redStatus;
    }

    public Integer getShowOrNot() {
        return showOrNot;
    }

    public void setShowOrNot(Integer showOrNot) {
        this.showOrNot = showOrNot;
    }

    public Long getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(Long startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public Long getEndTimeStamp() {
        return endTimeStamp;
    }

    public void setEndTimeStamp(Long endTimeStamp) {
        this.endTimeStamp = endTimeStamp;
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
