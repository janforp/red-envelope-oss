package com.envolope.oss.model;

import java.math.*;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-28
 */
public class ReUserDataStatistics implements java.io.Serializable {

    // Fields

    // 包名
    private String packageName;
    // 渠道
    private String channelName;
    // 日期
    private String dataTime;
    // 今日新增用户
    private Integer todayUser;
    // 总用户数
    private Integer totalUser;
    // 新增用户发放金币
    private Integer todayUserGiveCoin;
    // 今日总发放金币
    private Integer todayTotalGiveCoin;
    // 新增用户消耗金币
    private Integer todayUserExpendCoin;
    // 今日总消耗金币
    private Integer todayTotalExpendCoin;
    // 新增用户发放金额(元)
    private BigDecimal todayUserGiveMoney;
    // 今日总发放金额(元)
    private BigDecimal todayTotalGiveMoney;
    // 新增用户消耗金额(元)
    private BigDecimal todayUserExpendMoney;
    // 今日总消耗金额(元)
    private BigDecimal todayTotalExpendMoney;

    // Constructors

    /**
     * default constructor
     */
    public ReUserDataStatistics() {
    }

    /**
     * full constructor
     */
    public ReUserDataStatistics(String packageName, String channelName, String dataTime, Integer todayUser, Integer totalUser, Integer todayUserGiveCoin, Integer todayTotalGiveCoin, Integer todayUserExpendCoin, Integer todayTotalExpendCoin, BigDecimal todayUserGiveMoney, BigDecimal todayTotalGiveMoney, BigDecimal todayUserExpendMoney, BigDecimal todayTotalExpendMoney) {
        this.packageName = packageName;
        this.channelName = channelName;
        this.dataTime = dataTime;
        this.todayUser = todayUser;
        this.totalUser = totalUser;
        this.todayUserGiveCoin = todayUserGiveCoin;
        this.todayTotalGiveCoin = todayTotalGiveCoin;
        this.todayUserExpendCoin = todayUserExpendCoin;
        this.todayTotalExpendCoin = todayTotalExpendCoin;
        this.todayUserGiveMoney = todayUserGiveMoney;
        this.todayTotalGiveMoney = todayTotalGiveMoney;
        this.todayUserExpendMoney = todayUserExpendMoney;
        this.todayTotalExpendMoney = todayTotalExpendMoney;
    }

    // Property accessors

    /**
     * 包名
     */
    public String getPackageName() {
        return this.packageName;
    }

    /**
     * 包名
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * 渠道
     */
    public String getChannelName() {
        return this.channelName;
    }

    /**
     * 渠道
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 日期
     */
    public String getDataTime() {
        return this.dataTime;
    }

    /**
     * 日期
     */
    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    /**
     * 今日新增用户
     */
    public Integer getTodayUser() {
        return this.todayUser;
    }

    /**
     * 今日新增用户
     */
    public void setTodayUser(Integer todayUser) {
        this.todayUser = todayUser;
    }

    /**
     * 总用户数
     */
    public Integer getTotalUser() {
        return this.totalUser;
    }

    /**
     * 总用户数
     */
    public void setTotalUser(Integer totalUser) {
        this.totalUser = totalUser;
    }

    /**
     * 新增用户发放金币
     */
    public Integer getTodayUserGiveCoin() {
        return this.todayUserGiveCoin;
    }

    /**
     * 新增用户发放金币
     */
    public void setTodayUserGiveCoin(Integer todayUserGiveCoin) {
        this.todayUserGiveCoin = todayUserGiveCoin;
    }

    /**
     * 今日总发放金币
     */
    public Integer getTodayTotalGiveCoin() {
        return this.todayTotalGiveCoin;
    }

    /**
     * 今日总发放金币
     */
    public void setTodayTotalGiveCoin(Integer todayTotalGiveCoin) {
        this.todayTotalGiveCoin = todayTotalGiveCoin;
    }

    /**
     * 新增用户消耗金币
     */
    public Integer getTodayUserExpendCoin() {
        return this.todayUserExpendCoin;
    }

    /**
     * 新增用户消耗金币
     */
    public void setTodayUserExpendCoin(Integer todayUserExpendCoin) {
        this.todayUserExpendCoin = todayUserExpendCoin;
    }

    /**
     * 今日总消耗金币
     */
    public Integer getTodayTotalExpendCoin() {
        return this.todayTotalExpendCoin;
    }

    /**
     * 今日总消耗金币
     */
    public void setTodayTotalExpendCoin(Integer todayTotalExpendCoin) {
        this.todayTotalExpendCoin = todayTotalExpendCoin;
    }

    /**
     * 新增用户发放金额(元)
     */
    public BigDecimal getTodayUserGiveMoney() {
        return this.todayUserGiveMoney;
    }

    /**
     * 新增用户发放金额(元)
     */
    public void setTodayUserGiveMoney(BigDecimal todayUserGiveMoney) {
        this.todayUserGiveMoney = todayUserGiveMoney;
    }

    /**
     * 今日总发放金额(元)
     */
    public BigDecimal getTodayTotalGiveMoney() {
        return this.todayTotalGiveMoney;
    }

    /**
     * 今日总发放金额(元)
     */
    public void setTodayTotalGiveMoney(BigDecimal todayTotalGiveMoney) {
        this.todayTotalGiveMoney = todayTotalGiveMoney;
    }

    /**
     * 新增用户消耗金额(元)
     */
    public BigDecimal getTodayUserExpendMoney() {
        return this.todayUserExpendMoney;
    }

    /**
     * 新增用户消耗金额(元)
     */
    public void setTodayUserExpendMoney(BigDecimal todayUserExpendMoney) {
        this.todayUserExpendMoney = todayUserExpendMoney;
    }

    /**
     * 今日总消耗金额(元)
     */
    public BigDecimal getTodayTotalExpendMoney() {
        return this.todayTotalExpendMoney;
    }

    /**
     * 今日总消耗金额(元)
     */
    public void setTodayTotalExpendMoney(BigDecimal todayTotalExpendMoney) {
        this.todayTotalExpendMoney = todayTotalExpendMoney;
    }

}