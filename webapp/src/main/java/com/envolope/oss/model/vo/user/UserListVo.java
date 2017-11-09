package com.envolope.oss.model.vo.user;

import java.math.BigDecimal;

/**
 * Created by Jan on 16/10/13.
 * 用户列表数据
 */
public class UserListVo {

    // user_id，自增长
    private Integer userId;
    // 昵称，非空
    private String nickname;
    // 用户手机
    private String mobile;
    // 账户余额
    private BigDecimal userMoney;
    //今日获得现金
    private BigDecimal userGetMoney;
    // 账户积分
    private Integer userScore;
    //今日获得积分
    private BigDecimal userGetScore;
    // 用户状态，非空，默认1（有效）;0：封号；1：有效
    private Integer userStatus;
    // 注册时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
    private Long createTime;
    //2016-7-15
    private String registerTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public BigDecimal getUserGetMoney() {
        return userGetMoney;
    }

    public void setUserGetMoney(BigDecimal userGetMoney) {
        this.userGetMoney = userGetMoney;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public BigDecimal getUserGetScore() {
        return userGetScore;
    }

    public void setUserGetScore(BigDecimal userGetScore) {
        this.userGetScore = userGetScore;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
}
