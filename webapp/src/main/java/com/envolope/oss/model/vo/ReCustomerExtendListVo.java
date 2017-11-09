package com.envolope.oss.model.vo;

/**
 * Created by Jan on 2016-07-14
 * 推广列表
 */
public class ReCustomerExtendListVo implements java.io.Serializable {



    // 推广id
    private Integer id;
    // 客户id
    private Integer customerId;
    //客户名
    private String customerName;
    // 是否跳转 0-不跳转, 1-跳转
    private Integer isRedirect;
    //是否跳转 0-不跳转, 1-跳转 中文
    private String  isRedirectShow;
    // 跳转链接
    private String redirectUrl;
    // 等待跳转时间(秒)
    private Integer waitTime;
    // 推广说明
    private String extendDesc;
    // banner图
    private String customerBanner;
    // banner链接
    private String customerBannerUrl;
    // 广告图
    private String customerAdvert;
    // 广告链接
    private String customerAdvertUrl;
    //大金额红包总数 (大于110的红包)
    // 红包总个数
    private Integer redNumTotal;
    // 红包剩余个数
    private Integer redNumLeft;
    // 当天红包总个数
    private Integer redNumDayTotal;
    // 当天红包剩余个数
    private Integer redNumDayLeft;
    // 状态 0-结束, 1-进行中
    private Integer customerStatus;
    //状态中文
    private String  customStatusDesc;
    // 开始时间
    private Integer startTime;
    //显示的开始时间 2016-7-20....
    private String showStartTime;
    // 结束时间
    private Integer endTime;
    //显示的结束时间 2016-7-20....
    private String showEndTime;
    // 是热门 0-否, 1-是
    private Integer isHot;
    //是否热门中文
    private String isHotDesc;
    //中奖概率
    private Integer redChance;
    //stepRule
    private String stepRule ;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIsRedirectShow() {
        return isRedirectShow;
    }

    public void setIsRedirectShow(String isRedirectShow) {
        this.isRedirectShow = isRedirectShow;
    }

    public Integer getRedChance() {
        return redChance;
    }

    public void setRedChance(Integer redChance) {
        this.redChance = redChance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getIsRedirect() {
        return isRedirect;
    }

    public void setIsRedirect(Integer isRedirect) {
        this.isRedirect = isRedirect;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Integer getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Integer waitTime) {
        this.waitTime = waitTime;
    }

    public String getExtendDesc() {
        return extendDesc;
    }

    public void setExtendDesc(String extendDesc) {
        this.extendDesc = extendDesc;
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

    public String getCustomerAdvert() {
        return customerAdvert;
    }

    public void setCustomerAdvert(String customerAdvert) {
        this.customerAdvert = customerAdvert;
    }

    public String getCustomerAdvertUrl() {
        return customerAdvertUrl;
    }

    public void setCustomerAdvertUrl(String customerAdvertUrl) {
        this.customerAdvertUrl = customerAdvertUrl;
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

    public Integer getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(Integer customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getCustomStatusDesc() {
        return customStatusDesc;
    }

    public void setCustomStatusDesc(String customStatusDesc) {
        this.customStatusDesc = customStatusDesc;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public String getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(String showStartTime) {
        this.showStartTime = showStartTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(String showEndTime) {
        this.showEndTime = showEndTime;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getIsHotDesc() {
        return isHotDesc;
    }

    public void setIsHotDesc(String isHotDesc) {
        this.isHotDesc = isHotDesc;
    }

    public String getStepRule() {
        return stepRule;
    }

    public void setStepRule(String stepRule) {
        this.stepRule = stepRule;
    }
}