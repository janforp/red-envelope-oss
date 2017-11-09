package com.envolope.oss.model.para;

import java.io.Serializable;

/**
 * Created by Jan on 16/11/7.
 * 用户推荐任务列表查询条件
 */
public class RecommendTaskSelectParamVo implements Serializable {

    private Long        missionId;
    private String      missionTitle;
    private String      userPhone;
    private Integer     type;
    private Integer     isVerify;
    private Integer     status;
    private Integer     isLimitTime;
    private Integer     pageNum;
    private Integer     pageSize;
    private String      startDate;//2016-11-18 11:20:06
    private String      endDate;//2016-11-18 11:20:06

    public String getStartDate() {
        return startDate;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getMissionTitle() {
        return missionTitle;
    }

    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    public Integer getIsLimitTime() {
        return isLimitTime;
    }

    public void setIsLimitTime(Integer isLimitTime) {
        this.isLimitTime = isLimitTime;
    }
}
