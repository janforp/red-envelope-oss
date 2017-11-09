package com.envolope.oss.model.vo.user;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jan on 16/10/13.
 * 个人现金记录 页面顶部的数据
 */
public class UserMoneyVo implements Serializable {

    private Integer userId;
    //账户余额
    private BigDecimal money;
    //累积获得现金
    private BigDecimal totalGetMoney;
    //累积提现(提现成功)
    private BigDecimal withdrawMoney;
    //提现审核的金额
    private BigDecimal withdrawVerifyMoney;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getTotalGetMoney() {
        return totalGetMoney;
    }

    public void setTotalGetMoney(BigDecimal totalGetMoney) {
        this.totalGetMoney = totalGetMoney;
    }

    public BigDecimal getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(BigDecimal withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    public BigDecimal getWithdrawVerifyMoney() {
        return withdrawVerifyMoney;
    }

    public void setWithdrawVerifyMoney(BigDecimal withdrawVerifyMoney) {
        this.withdrawVerifyMoney = withdrawVerifyMoney;
    }
}
