package com.envolope.oss.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/8/24.
 * 兑换码红包 修改或者添加 页面数据
 */
public class CodeRedVo implements Serializable{

    // id
    private Integer codeId;
    // 红包口令
    private String redCode;
    // 客户名称
    private String customerName;
    // 客户头像
    private String customerImg;
    // 客户描述
    private String customerDesc;
    // 奖励说明
    private String awardDesc;
    // banner图
    private String customerBanner;
    // banner链接
    private String customerBannerUrl;
    // 最大红包(展示用)
    private String redMax;
    // 红包领取规则
    private String redDesc;
    // 红包总个数
    private Integer redNumTotal;
    // 红包剩余个数
    private Integer redNumLeft;
    // 当天红包总个数
    private Integer redNumDayTotal;
    // 当天红包剩余个数
    private Integer redNumDayLeft;
    // 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
    private Integer codeStatus;
    // 注册时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
    private Long createTime;

    private String showCreateTime ;
    // 更新时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
    private Long updateTime;

    private String showUpdateTime ;

    private String max;

    private String min ;

    private String bigRed ;

    public String getBigRed() {
        return bigRed;
    }

    public void setBigRed(String bigRed) {
        this.bigRed = bigRed;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    public String getRedCode() {
        return redCode;
    }

    public void setRedCode(String redCode) {
        this.redCode = redCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public String getCustomerDesc() {
        return customerDesc;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }

    public String getAwardDesc() {
        return awardDesc;
    }

    public void setAwardDesc(String awardDesc) {
        this.awardDesc = awardDesc;
    }

    public String getCustomerBanner() {
        return customerBanner;
    }

    public void setCustomerBanner(String customerBanner) {
        this.customerBanner = customerBanner;
    }

    public String getCustomerBannerUrl() {
        return customerBannerUrl;
    }

    public void setCustomerBannerUrl(String customerBannerUrl) {
        this.customerBannerUrl = customerBannerUrl;
    }

    public String getRedMax() {
        return redMax;
    }

    public void setRedMax(String redMax) {
        this.redMax = redMax;
    }

    public String getRedDesc() {
        return redDesc;
    }

    public void setRedDesc(String redDesc) {
        this.redDesc = redDesc;
    }

    public Integer getRedNumTotal() {
        return redNumTotal;
    }

    public void setRedNumTotal(Integer redNumTotal) {
        this.redNumTotal = redNumTotal;
    }

    public Integer getRedNumLeft() {
        return redNumLeft;
    }

    public void setRedNumLeft(Integer redNumLeft) {
        this.redNumLeft = redNumLeft;
    }

    public Integer getRedNumDayTotal() {
        return redNumDayTotal;
    }

    public void setRedNumDayTotal(Integer redNumDayTotal) {
        this.redNumDayTotal = redNumDayTotal;
    }

    public Integer getRedNumDayLeft() {
        return redNumDayLeft;
    }

    public void setRedNumDayLeft(Integer redNumDayLeft) {
        this.redNumDayLeft = redNumDayLeft;
    }

    public Integer getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(Integer codeStatus) {
        this.codeStatus = codeStatus;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getShowCreateTime() {
        return showCreateTime;
    }

    public void setShowCreateTime(String showCreateTime) {
        this.showCreateTime = showCreateTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getShowUpdateTime() {
        return showUpdateTime;
    }

    public void setShowUpdateTime(String showUpdateTime) {
        this.showUpdateTime = showUpdateTime;
    }
}
