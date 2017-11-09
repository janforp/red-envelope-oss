package com.envolope.oss.model;

import java.math.*;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-27
 */
public class ReUserCommissionWithdraw implements java.io.Serializable {

    // Fields

    // ID
    private Long id;
    // 用户ID
    private Integer userId;
    // 提现状态; 0:未处理 ,1:已处理 
    private Integer withdrawStatus;
    // 申请金额(元)
    private BigDecimal applyMoney;
    // 申请时间,如:2016-08-18 12:53:30
    private String applyTime;
    // 确认时间,如:2016-08-18 12:53:30
    private String withdrawTime;

    // Constructors

    /**
     * default constructor
     */
    public ReUserCommissionWithdraw() {
    }

    /**
     * full constructor
     */
    public ReUserCommissionWithdraw(Integer userId, Integer withdrawStatus, BigDecimal applyMoney, String applyTime, String withdrawTime) {
        this.userId = userId;
        this.withdrawStatus = withdrawStatus;
        this.applyMoney = applyMoney;
        this.applyTime = applyTime;
        this.withdrawTime = withdrawTime;
    }

    // Property accessors

    /**
     * ID
     */
    public Long getId() {
        return this.id;
    }

    /**
     * ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户ID
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 提现状态; 0:未处理 ,1:已处理 
     */
    public Integer getWithdrawStatus() {
        return this.withdrawStatus;
    }

    /**
     * 提现状态; 0:未处理 ,1:已处理 
     */
    public void setWithdrawStatus(Integer withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    /**
     * 申请金额(元)
     */
    public BigDecimal getApplyMoney() {
        return this.applyMoney;
    }

    /**
     * 申请金额(元)
     */
    public void setApplyMoney(BigDecimal applyMoney) {
        this.applyMoney = applyMoney;
    }

    /**
     * 申请时间,如:2016-08-18 12:53:30
     */
    public String getApplyTime() {
        return this.applyTime;
    }

    /**
     * 申请时间,如:2016-08-18 12:53:30
     */
    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 确认时间,如:2016-08-18 12:53:30
     */
    public String getWithdrawTime() {
        return this.withdrawTime;
    }

    /**
     * 确认时间,如:2016-08-18 12:53:30
     */
    public void setWithdrawTime(String withdrawTime) {
        this.withdrawTime = withdrawTime;
    }

}