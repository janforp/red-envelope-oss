package com.envolope.oss.model.para;

import java.io.Serializable;

/**
 * Created by Jan on 2016/12/3.
 * 自己的积分墙列表查询参数
 */
public class SelfIntegralSelectParam implements Serializable{

    // 应用名称
    private String appName;
    // 安装包链接
    private String appUrl;
    // 应用图标
    private String appIcon;
    // 应用包名
    private String appPackage;
    // 开始时间,如:2016-08-18 12:53:30
    private String startTime;
    // 结束时间,如:2016-08-18 12:53:30
    private String endTime;
    //页数
    private Integer pageNum;
    //状态
    private Integer status;

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

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
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
}
