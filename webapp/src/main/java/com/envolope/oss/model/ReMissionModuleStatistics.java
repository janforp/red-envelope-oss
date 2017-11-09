package com.envolope.oss.model;

import java.math.*;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-12-01
 */
public class ReMissionModuleStatistics implements java.io.Serializable {

    // Fields

    // 日期，格式：2016-11-26
    private String oneDate;
    // 模块编号，如：1表示高额任务模块
    private Integer module;
    // 参与任务总用户数，每个用户只统计一次
    private Integer disPartAmount;
    // 参与任务总次数，单个用户可能统计多次
    private Integer totalPartAmount;
    // 完成任务总用户数，每个用户只统计一次
    private Integer disCompAmount;
    // 完成任务总次数，单个用户可能统计多次
    private Integer totalCompAmount;
    // 应发奖励，元
    private BigDecimal shouldPayMoney;
    // 实发奖励，元
    private BigDecimal finalPayMoney;
    // 备注
    private String moduleDesc;

    // Constructors

    /**
     * default constructor
     */
    public ReMissionModuleStatistics() {
    }

    /**
     * full constructor
     */
    public ReMissionModuleStatistics(String oneDate, Integer module, Integer disPartAmount, Integer totalPartAmount, Integer disCompAmount, Integer totalCompAmount, BigDecimal shouldPayMoney, BigDecimal finalPayMoney, String moduleDesc) {
        this.oneDate = oneDate;
        this.module = module;
        this.disPartAmount = disPartAmount;
        this.totalPartAmount = totalPartAmount;
        this.disCompAmount = disCompAmount;
        this.totalCompAmount = totalCompAmount;
        this.shouldPayMoney = shouldPayMoney;
        this.finalPayMoney = finalPayMoney;
        this.moduleDesc = moduleDesc;
    }

    // Property accessors

    /**
     * 日期，格式：2016-11-26
     */
    public String getOneDate() {
        return this.oneDate;
    }

    /**
     * 日期，格式：2016-11-26
     */
    public void setOneDate(String oneDate) {
        this.oneDate = oneDate;
    }

    /**
     * 模块编号，如：1表示高额任务模块
     */
    public Integer getModule() {
        return this.module;
    }

    /**
     * 模块编号，如：1表示高额任务模块
     */
    public void setModule(Integer module) {
        this.module = module;
    }

    /**
     * 参与任务总用户数，每个用户只统计一次
     */
    public Integer getDisPartAmount() {
        return this.disPartAmount;
    }

    /**
     * 参与任务总用户数，每个用户只统计一次
     */
    public void setDisPartAmount(Integer disPartAmount) {
        this.disPartAmount = disPartAmount;
    }

    /**
     * 参与任务总次数，单个用户可能统计多次
     */
    public Integer getTotalPartAmount() {
        return this.totalPartAmount;
    }

    /**
     * 参与任务总次数，单个用户可能统计多次
     */
    public void setTotalPartAmount(Integer totalPartAmount) {
        this.totalPartAmount = totalPartAmount;
    }

    /**
     * 完成任务总用户数，每个用户只统计一次
     */
    public Integer getDisCompAmount() {
        return this.disCompAmount;
    }

    /**
     * 完成任务总用户数，每个用户只统计一次
     */
    public void setDisCompAmount(Integer disCompAmount) {
        this.disCompAmount = disCompAmount;
    }

    /**
     * 完成任务总次数，单个用户可能统计多次
     */
    public Integer getTotalCompAmount() {
        return this.totalCompAmount;
    }

    /**
     * 完成任务总次数，单个用户可能统计多次
     */
    public void setTotalCompAmount(Integer totalCompAmount) {
        this.totalCompAmount = totalCompAmount;
    }

    /**
     * 应发奖励，元
     */
    public BigDecimal getShouldPayMoney() {
        return this.shouldPayMoney;
    }

    /**
     * 应发奖励，元
     */
    public void setShouldPayMoney(BigDecimal shouldPayMoney) {
        this.shouldPayMoney = shouldPayMoney;
    }

    /**
     * 实发奖励，元
     */
    public BigDecimal getFinalPayMoney() {
        return this.finalPayMoney;
    }

    /**
     * 实发奖励，元
     */
    public void setFinalPayMoney(BigDecimal finalPayMoney) {
        this.finalPayMoney = finalPayMoney;
    }

    /**
     * 备注
     */
    public String getModuleDesc() {
        return this.moduleDesc;
    }

    /**
     * 备注
     */
    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

}