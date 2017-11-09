package com.envolope.oss.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/9/9.
 * 兑换列表数据
 */
public class ExchangeListVo  implements Serializable {

    private Long id;
    // 用户ID
    private Integer userId;

    private String userName;
    private String address;
    private String phone;
    // 兑换状态 0-未发货,1-已发货
    // 兑换商品编号
    private Long goodsNum;
    // 兑换商品的名字
    private String goodsName;
    // 兑换状态 0-未发货,1-已发货
    private Integer exchangeStatus;
    // 申请兑换时间,如:2016-08-18 12:53:30
    private String exchangeTime;
    // 发货时间,如:2016-08-18 12:53:30
    private String sendTime;
    // 金币
    private Integer score;
    // 虚拟商品的卡号
    private String cardId;
    // 虚拟商品卡密
    private String cardPassword;
    // 实物快递单号
    private String expressNumber;
    //商品类型0:实物，1:虚拟商品，将来会扩展
    private Integer type;
    private String invalidTime;

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(Integer exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    public String getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(String exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
