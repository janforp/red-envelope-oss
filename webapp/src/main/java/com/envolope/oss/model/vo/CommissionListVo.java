package com.envolope.oss.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jan on 16/9/27.
 * 佣金提现列表
 */
public class CommissionListVo implements Serializable {

    private Long id;
    // 用户ID
    private Integer userId;
    private String name;
    private String phone;
    // 提现状态; 0:未处理 ,1:已处理
    private Integer withdrawStatus;
    // 申请金额(元)
    private BigDecimal applyMoney;
    // 申请时间,如:2016-08-18 12:53:30
    private String applyTime;
    // 确认时间,如:2016-08-18 12:53:30
    private String withdrawTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(Integer withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    public BigDecimal getApplyMoney() {
        return applyMoney;
    }

    public void setApplyMoney(BigDecimal applyMoney) {
        this.applyMoney = applyMoney;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getWithdrawTime() {
        return withdrawTime;
    }

    public void setWithdrawTime(String withdrawTime) {
        this.withdrawTime = withdrawTime;
    }
}
