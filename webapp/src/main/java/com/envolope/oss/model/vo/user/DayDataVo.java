package com.envolope.oss.model.vo.user;

import java.math.BigDecimal;

/**
 * Created by Jan on 16/9/29.
 * 每日数据
 */
public class DayDataVo {

    //2016-7-28
    private String startTime;
    private String endTime;
    //当日注册用户
    private Integer registerUsers;
    //目前为止的总用户
    private Integer totalUsers;
    //包名
    private String  packageName;
    //渠道
    private String channelName;
    //当日注册用户获得的钱
    private BigDecimal newUserGetMoney;
    //当日注册用户提现的钱
    private BigDecimal newUserWithdrawMoney;
    //当日注册用户获得的金币
    private Integer newUserGetScore;
    //当日注册用户消耗的金币
    private Integer newUserConsumeScore;
    //当日总用户获得的钱
    private BigDecimal totalUserGetMoney;
    //当日总用户提现的钱
    private BigDecimal totalUserWithdrawMoney;
    //当日总用户获得的金币
    private Integer totalUserGetScore;
    //当日总用户消耗的金币
    private Integer totalUserConsumeScore;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
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

    public Integer getRegisterUsers() {
        return registerUsers;
    }

    public void setRegisterUsers(Integer registerUsers) {
        this.registerUsers = registerUsers;
    }

    public Integer getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(Integer totalUsers) {
        this.totalUsers = totalUsers;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public BigDecimal getNewUserGetMoney() {
        return newUserGetMoney;
    }

    public void setNewUserGetMoney(BigDecimal newUserGetMoney) {
        this.newUserGetMoney = newUserGetMoney;
    }

    public BigDecimal getNewUserWithdrawMoney() {
        return newUserWithdrawMoney;
    }

    public void setNewUserWithdrawMoney(BigDecimal newUserWithdrawMoney) {
        this.newUserWithdrawMoney = newUserWithdrawMoney;
    }

    public Integer getNewUserGetScore() {
        return newUserGetScore;
    }

    public void setNewUserGetScore(Integer newUserGetScore) {
        this.newUserGetScore = newUserGetScore;
    }

    public Integer getNewUserConsumeScore() {
        return newUserConsumeScore;
    }

    public void setNewUserConsumeScore(Integer newUserConsumeScore) {
        this.newUserConsumeScore = newUserConsumeScore;
    }

    public BigDecimal getTotalUserGetMoney() {
        return totalUserGetMoney;
    }

    public void setTotalUserGetMoney(BigDecimal totalUserGetMoney) {
        this.totalUserGetMoney = totalUserGetMoney;
    }

    public BigDecimal getTotalUserWithdrawMoney() {
        return totalUserWithdrawMoney;
    }

    public void setTotalUserWithdrawMoney(BigDecimal totalUserWithdrawMoney) {
        this.totalUserWithdrawMoney = totalUserWithdrawMoney;
    }

    public Integer getTotalUserGetScore() {
        return totalUserGetScore;
    }

    public void setTotalUserGetScore(Integer totalUserGetScore) {
        this.totalUserGetScore = totalUserGetScore;
    }

    public Integer getTotalUserConsumeScore() {
        return totalUserConsumeScore;
    }

    public void setTotalUserConsumeScore(Integer totalUserConsumeScore) {
        this.totalUserConsumeScore = totalUserConsumeScore;
    }
}
