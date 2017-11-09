package com.envolope.oss.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/7/14.
 * 新增 推广
 * 修改推广
 */
public class NewExtentVo  implements Serializable {


    private Integer extendId;
    private String  customerName;
    // 是否跳转 0-不跳转, 1-跳转
    private Integer isRedirect;
    // 跳转链接
    private String redirectUrl;
    // 等待跳转时间(秒)
    private Integer waitTime;
    // 推广说明
    private String extendDesc;

    private Integer customerId;

    private String bannerImg;
    private String bannerUrl;
    private String advertImg;
    private String adverstUrl;
    private Integer num;
    private String big;
    private Integer status;
    private String startTime;
    private String endTime;
    private Integer isHot;
    private Integer redChance;
    private String stepRule;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStepRule() {
        return stepRule;
    }

    public void setStepRule(String stepRule) {
        this.stepRule = stepRule;
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

    public Integer getExtendId() {
        return extendId;
    }

    public void setExtendId(Integer extendId) {
        this.extendId = extendId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getAdvertImg() {
        return advertImg;
    }

    public void setAdvertImg(String advertImg) {
        this.advertImg = advertImg;
    }

    public String getAdverstUrl() {
        return adverstUrl;
    }

    public void setAdverstUrl(String adverstUrl) {
        this.adverstUrl = adverstUrl;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

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

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getRedChance() {
        return redChance;
    }

    public void setRedChance(Integer redChance) {
        this.redChance = redChance;
    }
}
