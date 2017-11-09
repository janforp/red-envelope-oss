package com.envolope.oss.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Summer on 16/6/14.
 */
public class UserInfo implements Serializable {

    // 用户id
    private Integer userId;
    // 用户昵称
    private String userName;
    // 用户头像
    private String img;
    //手机号(go_member表)
    private String phone;
    //其它手机号(如收货地址,绑定手机号等):用于话费充值
    private String otherPhone;
    //省份城市
    private String provinceCity;
    //详细地址(为默认地址)
    private String detailAddress;
    //购买人邮箱
    private String email;
    //帐户余额
    private BigDecimal money;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvinceCity() {
        return provinceCity;
    }

    public void setProvinceCity(String provinceCity) {
        this.provinceCity = provinceCity;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }
}
