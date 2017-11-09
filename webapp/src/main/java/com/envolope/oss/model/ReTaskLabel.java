package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-18
 */
public class ReTaskLabel implements java.io.Serializable {

    // Fields

    // id
    private Long labelId;
    // 关键词
    private String taskLabel;

    // Constructors

    /**
     * default constructor
     */
    public ReTaskLabel() {
    }

    /**
     * full constructor
     */
    public ReTaskLabel(String taskLabel) {
        this.taskLabel = taskLabel;
    }

    // Property accessors

    /**
     * id
     */
    public Long getLabelId() {
        return this.labelId;
    }

    /**
     * id
     */
    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    /**
     * 关键词
     */
    public String getTaskLabel() {
        return this.taskLabel;
    }

    /**
     * 关键词
     */
    public void setTaskLabel(String taskLabel) {
        this.taskLabel = taskLabel;
    }

}