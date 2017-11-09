package com.envolope.oss.model.vo.user;

import java.math.BigDecimal;

/**
 * Created by Jan on 16/9/30.
 * 渠道详情
 */
public class ChannelDataVo {

    private String  startDate;
    private String  endDate;
    private String  packageName;
    private String  channelName;
    //在此时间段内新增的用户
    private Integer newUserNum;
    //此包名及渠道 的总用户数
    private Integer allUserNum;
    //此时间段内发放的金币
    private Integer allUserGetScore;
    //此时间段内消耗的金币
    private Integer allUserConsumeScore;
    //此时间段内发放的金额
    private BigDecimal allUserGetMoney;
    //此时间段内消耗的金额
    private BigDecimal allUserConsumeMoney;

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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getNewUserNum() {
        return newUserNum;
    }

    public void setNewUserNum(Integer newUserNum) {
        this.newUserNum = newUserNum;
    }

    public Integer getAllUserNum() {
        return allUserNum;
    }

    public void setAllUserNum(Integer allUserNum) {
        this.allUserNum = allUserNum;
    }

    public Integer getAllUserGetScore() {
        return allUserGetScore;
    }

    public void setAllUserGetScore(Integer allUserGetScore) {
        this.allUserGetScore = allUserGetScore;
    }

    public Integer getAllUserConsumeScore() {
        return allUserConsumeScore;
    }

    public void setAllUserConsumeScore(Integer allUserConsumeScore) {
        this.allUserConsumeScore = allUserConsumeScore;
    }

    public BigDecimal getAllUserGetMoney() {
        return allUserGetMoney;
    }

    public void setAllUserGetMoney(BigDecimal allUserGetMoney) {
        this.allUserGetMoney = allUserGetMoney;
    }

    public BigDecimal getAllUserConsumeMoney() {
        return allUserConsumeMoney;
    }

    public void setAllUserConsumeMoney(BigDecimal allUserConsumeMoney) {
        this.allUserConsumeMoney = allUserConsumeMoney;
    }
}
