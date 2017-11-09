package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-22
 */
public class ReAddress implements java.io.Serializable {

    // Fields

    // 用户ID,主键
    private Integer userId;
    // 收货人姓名
    private String realName;
    // 收货手机
    private String mobile;
    // 省
    private String province;
    // 市
    private String city;
    // 详细地址
    private String detailAddress;

    // Constructors

    /**
     * default constructor
     */
    public ReAddress() {
    }

    /**
     * full constructor
     */
    public ReAddress(Integer userId, String realName, String mobile, String province, String city, String detailAddress) {
        this.userId = userId;
        this.realName = realName;
        this.mobile = mobile;
        this.province = province;
        this.city = city;
        this.detailAddress = detailAddress;
    }

    // Property accessors

    /**
     * 用户ID,主键
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户ID,主键
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 收货人姓名
     */
    public String getRealName() {
        return this.realName;
    }

    /**
     * 收货人姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 收货手机
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * 收货手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 省
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 市
     */
    public String getCity() {
        return this.city;
    }

    /**
     * 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 详细地址
     */
    public String getDetailAddress() {
        return this.detailAddress;
    }

    /**
     * 详细地址
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

}