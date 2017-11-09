package com.envolope.oss.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/7/15.
 *  解锁红包 页面 显示数据
 */
public class UnlockRedPageShowVo implements Serializable {

    //推广id
    private Integer id;
    //微信名称
    private String  wxName;
    //微信号
    private String  wxId;
    //客户id
    private Integer customerId;
    //剩余红包数量
    private Integer left;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }
}
