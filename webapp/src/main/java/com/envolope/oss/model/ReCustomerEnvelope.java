package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-07-20
 */
public class ReCustomerEnvelope implements java.io.Serializable {

    // Fields

    // id
    private Integer id;
    // 客户id
    private Integer customerId;
    // 红包金额(单位:分)
    private Integer envelopeMoney;
    // 红包状态 0-未领取,1-已领取
    private Integer envelopeStatus;

    // Constructors

    /**
     * default constructor
     */
    public ReCustomerEnvelope() {
    }

    /**
     * full constructor
     */
    public ReCustomerEnvelope(Integer customerId, Integer envelopeMoney, Integer envelopeStatus) {
        this.customerId = customerId;
        this.envelopeMoney = envelopeMoney;
        this.envelopeStatus = envelopeStatus;
    }

    // Property accessors

    /**
     * id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 客户id
     */
    public Integer getCustomerId() {
        return this.customerId;
    }

    /**
     * 客户id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 红包金额(单位:分)
     */
    public Integer getEnvelopeMoney() {
        return this.envelopeMoney;
    }

    /**
     * 红包金额(单位:分)
     */
    public void setEnvelopeMoney(Integer envelopeMoney) {
        this.envelopeMoney = envelopeMoney;
    }

    /**
     * 红包状态 0-未领取,1-已领取
     */
    public Integer getEnvelopeStatus() {
        return this.envelopeStatus;
    }

    /**
     * 红包状态 0-未领取,1-已领取
     */
    public void setEnvelopeStatus(Integer envelopeStatus) {
        this.envelopeStatus = envelopeStatus;
    }

}