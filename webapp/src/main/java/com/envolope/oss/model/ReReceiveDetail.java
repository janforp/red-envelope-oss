package com.envolope.oss.model;

import java.math.*;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-10
 */
public class ReReceiveDetail implements java.io.Serializable {

    // Fields

    // ID
    private Long detailId;
    // 用户ID
    private Integer userId;
    // 红包类型 0-定时红包,1-兑换码红包,2-现金红包
    private Integer redType;
    // 红包ID
    private Integer redId;
    // 红包金额
    private BigDecimal redMoney;
    // 时间,如:2016-08-18 12:53:30
    private String detailTime;

    // Constructors

    /**
     * default constructor
     */
    public ReReceiveDetail() {
    }

    /**
     * full constructor
     */
    public ReReceiveDetail(Integer userId, Integer redType, Integer redId, BigDecimal redMoney, String detailTime) {
        this.userId = userId;
        this.redType = redType;
        this.redId = redId;
        this.redMoney = redMoney;
        this.detailTime = detailTime;
    }

    // Property accessors

    /**
     * ID
     */
    public Long getDetailId() {
        return this.detailId;
    }

    /**
     * ID
     */
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
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
     * 红包类型 0-定时红包,1-兑换码红包,2-现金红包
     */
    public Integer getRedType() {
        return this.redType;
    }

    /**
     * 红包类型 0-定时红包,1-兑换码红包,2-现金红包
     */
    public void setRedType(Integer redType) {
        this.redType = redType;
    }

    /**
     * 红包ID
     */
    public Integer getRedId() {
        return this.redId;
    }

    /**
     * 红包ID
     */
    public void setRedId(Integer redId) {
        this.redId = redId;
    }

    /**
     * 红包金额
     */
    public BigDecimal getRedMoney() {
        return this.redMoney;
    }

    /**
     * 红包金额
     */
    public void setRedMoney(BigDecimal redMoney) {
        this.redMoney = redMoney;
    }

    /**
     * 时间,如:2016-08-18 12:53:30
     */
    public String getDetailTime() {
        return this.detailTime;
    }

    /**
     * 时间,如:2016-08-18 12:53:30
     */
    public void setDetailTime(String detailTime) {
        this.detailTime = detailTime;
    }

}