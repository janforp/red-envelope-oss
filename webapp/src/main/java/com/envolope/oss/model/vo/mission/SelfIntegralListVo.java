package com.envolope.oss.model.vo.mission;

import java.math.BigDecimal;

/**
 * Created by Jan on 2016/12/3.
 * 列表数据
 */
public class SelfIntegralListVo {

    private Long wallId;
    // 应用名称
    private String appName;
    // 应用图标
    private String appIcon;
    // 应用包名
    private String appPackage;
    // 应用标签
    private String appLabel;
    // 开始时间,如:2016-08-18 12:53:30
    private String startTime;
    // 结束时间,如:2016-08-18 12:53:30
    private String endTime;
    // 任务总数量
    private Integer totalNum;
    // 完成第一步的数量
    private Integer completeNum;
    // 任务总奖励
    private BigDecimal totalMoney;
    // 第一步总奖励
    private BigDecimal stepOneMoney;
    // 第二步每天奖励
    private BigDecimal stepTwoMoney;
    // 第二步天数
    private Integer stepTwoDay;
    // 权重大的靠前
    private Integer wallWeight;
    //状态
    private String status;
    //是否限制数量
    private Integer isLimitNum;

    public Integer getIsLimitNum() {
        return isLimitNum;
    }

    public void setIsLimitNum(Integer isLimitNum) {
        this.isLimitNum = isLimitNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getWallId() {
        return wallId;
    }

    public void setWallId(Long wallId) {
        this.wallId = wallId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
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

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getStepOneMoney() {
        return stepOneMoney;
    }

    public void setStepOneMoney(BigDecimal stepOneMoney) {
        this.stepOneMoney = stepOneMoney;
    }

    public BigDecimal getStepTwoMoney() {
        return stepTwoMoney;
    }

    public void setStepTwoMoney(BigDecimal stepTwoMoney) {
        this.stepTwoMoney = stepTwoMoney;
    }

    public Integer getStepTwoDay() {
        return stepTwoDay;
    }

    public void setStepTwoDay(Integer stepTwoDay) {
        this.stepTwoDay = stepTwoDay;
    }

    public Integer getWallWeight() {
        return wallWeight;
    }

    public void setWallWeight(Integer wallWeight) {
        this.wallWeight = wallWeight;
    }
}
