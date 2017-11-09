package com.envolope.oss.model.vo.data_statistics;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jan on 2016/11/29.
 * 某个模块的每天具体数据详情
 */
public class ModuleDetail implements Serializable {
    //详情记录id
    private Long detailId;
    //模块编号
    private Integer module;
    //日期    2016-11-20 12:00:00
    private String detailDate;
    //用户ID
    private Integer userId;
    //用户昵称
    private String userName;
    //任务的名字
    private String missionName;
    //应领取奖励
    private BigDecimal money;
    //领取状态
    private Integer drawStatus;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Integer getModule() {
        return module;
    }

    public void setModule(Integer module) {
        this.module = module;
    }

    public String getDetailDate() {
        return detailDate;
    }

    public void setDetailDate(String detailDate) {
        this.detailDate = detailDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getDrawStatus() {
        return drawStatus;
    }

    public void setDrawStatus(Integer drawStatus) {
        this.drawStatus = drawStatus;
    }
}
