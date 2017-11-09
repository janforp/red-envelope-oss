package com.envolope.oss.model;

import java.math.*;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-24
 */
public class ReCodeRedDetail implements java.io.Serializable {

    // Fields

    // id
    private Long id;
    // 红包id
    private Integer codeId;
    // 红包金额(元)
    private BigDecimal codeMoney;
    // 红包状态 0-未领取,1-已领取
    private Integer codeStatus;

    // Constructors

    /**
     * default constructor
     */
    public ReCodeRedDetail() {
    }

    /**
     * full constructor
     */
    public ReCodeRedDetail(Integer codeId, BigDecimal codeMoney, Integer codeStatus) {
        this.codeId = codeId;
        this.codeMoney = codeMoney;
        this.codeStatus = codeStatus;
    }

    // Property accessors

    /**
     * id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 红包id
     */
    public Integer getCodeId() {
        return this.codeId;
    }

    /**
     * 红包id
     */
    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    /**
     * 红包金额(元)
     */
    public BigDecimal getCodeMoney() {
        return this.codeMoney;
    }

    /**
     * 红包金额(元)
     */
    public void setCodeMoney(BigDecimal codeMoney) {
        this.codeMoney = codeMoney;
    }

    /**
     * 红包状态 0-未领取,1-已领取
     */
    public Integer getCodeStatus() {
        return this.codeStatus;
    }

    /**
     * 红包状态 0-未领取,1-已领取
     */
    public void setCodeStatus(Integer codeStatus) {
        this.codeStatus = codeStatus;
    }

}