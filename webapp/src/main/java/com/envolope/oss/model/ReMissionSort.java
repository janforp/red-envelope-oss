package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-11
 */
public class ReMissionSort implements java.io.Serializable {

    // Fields

    // 任务分类ID
    private Integer sortId;
    // 分类名字
    private String sortName;
    // 分类图标
    private String sortImg;
    // 分类排序,值较小者排在较前
    private Integer sortOrder;

    // Constructors

    /**
     * default constructor
     */
    public ReMissionSort() {
    }

    /**
     * full constructor
     */
    public ReMissionSort(String sortName, String sortImg, Integer sortOrder) {
        this.sortName = sortName;
        this.sortImg = sortImg;
        this.sortOrder = sortOrder;
    }

    // Property accessors

    /**
     * 任务分类ID
     */
    public Integer getSortId() {
        return this.sortId;
    }

    /**
     * 任务分类ID
     */
    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    /**
     * 分类名字
     */
    public String getSortName() {
        return this.sortName;
    }

    /**
     * 分类名字
     */
    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    /**
     * 分类图标
     */
    public String getSortImg() {
        return this.sortImg;
    }

    /**
     * 分类图标
     */
    public void setSortImg(String sortImg) {
        this.sortImg = sortImg;
    }

    /**
     * 分类排序,值较小者排在较前
     */
    public Integer getSortOrder() {
        return this.sortOrder;
    }

    /**
     * 分类排序,值较小者排在较前
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

}