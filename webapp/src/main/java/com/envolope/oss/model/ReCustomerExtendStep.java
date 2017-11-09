package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-07-15
 */
public class ReCustomerExtendStep implements java.io.Serializable {

    // Fields

    // id
    private Integer id;
    // 推广id
    private Integer extendId;
    // 序号
    private Integer stepNum;
    // 步骤内容
    private String stepContent;

    // Constructors

    /**
     * default constructor
     */
    public ReCustomerExtendStep() {
    }

    /**
     * full constructor
     */
    public ReCustomerExtendStep(Integer extendId, Integer stepNum, String stepContent) {
        this.extendId = extendId;
        this.stepNum = stepNum;
        this.stepContent = stepContent;
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
     * 推广id
     */
    public Integer getExtendId() {
        return this.extendId;
    }

    /**
     * 推广id
     */
    public void setExtendId(Integer extendId) {
        this.extendId = extendId;
    }

    /**
     * 序号
     */
    public Integer getStepNum() {
        return this.stepNum;
    }

    /**
     * 序号
     */
    public void setStepNum(Integer stepNum) {
        this.stepNum = stepNum;
    }

    /**
     * 步骤内容
     */
    public String getStepContent() {
        return this.stepContent;
    }

    /**
     * 步骤内容
     */
    public void setStepContent(String stepContent) {
        this.stepContent = stepContent;
    }

}