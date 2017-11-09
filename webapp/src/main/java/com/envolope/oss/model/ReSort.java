package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-16
 */
public class ReSort implements java.io.Serializable {

    // Fields

    // 红包分类ID
    private Integer sortId;
    // 红包分类名字
    private String sortTitle;
    // 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
    private Integer sortStatus;
    // 分类排序,值较小者排在较前
    private Integer sortOrder;

    // Constructors

    /**
     * default constructor
     */
    public ReSort() {
    }

    /**
     * full constructor
     */
    public ReSort(String sortTitle, Integer sortStatus, Integer sortOrder) {
        this.sortTitle = sortTitle;
        this.sortStatus = sortStatus;
        this.sortOrder = sortOrder;
    }

    // Property accessors

    /**
     * 红包分类ID
     */
    public Integer getSortId() {
        return this.sortId;
    }

    /**
     * 红包分类ID
     */
    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    /**
     * 红包分类名字
     */
    public String getSortTitle() {
        return this.sortTitle;
    }

    /**
     * 红包分类名字
     */
    public void setSortTitle(String sortTitle) {
        this.sortTitle = sortTitle;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getSortStatus() {
        return this.sortStatus;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public void setSortStatus(Integer sortStatus) {
        this.sortStatus = sortStatus;
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