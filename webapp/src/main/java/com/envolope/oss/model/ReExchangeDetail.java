package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-12
 */
public class ReExchangeDetail implements java.io.Serializable {

    // Fields

    // id
    private Long id;
    // 用户ID
    private Integer userId;
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
    // 过期时间
    private String invalidTime;

    // Constructors

    /**
     * default constructor
     */
    public ReExchangeDetail() {
    }

    /**
     * full constructor
     */
    public ReExchangeDetail(Integer userId, Long goodsNum, String goodsName, Integer exchangeStatus, String exchangeTime, String sendTime, Integer score, String cardId, String cardPassword, String expressNumber, String invalidTime) {
        this.userId = userId;
        this.goodsNum = goodsNum;
        this.goodsName = goodsName;
        this.exchangeStatus = exchangeStatus;
        this.exchangeTime = exchangeTime;
        this.sendTime = sendTime;
        this.score = score;
        this.cardId = cardId;
        this.cardPassword = cardPassword;
        this.expressNumber = expressNumber;
        this.invalidTime = invalidTime;
    }

    // Property accessors

    /**
     * id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户ID
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 兑换商品编号
     */
    public Long getGoodsNum() {
        return this.goodsNum;
    }

    /**
     * 兑换商品编号
     */
    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }

    /**
     * 兑换商品的名字
     */
    public String getGoodsName() {
        return this.goodsName;
    }

    /**
     * 兑换商品的名字
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 兑换状态 0-未发货,1-已发货
     */
    public Integer getExchangeStatus() {
        return this.exchangeStatus;
    }

    /**
     * 兑换状态 0-未发货,1-已发货
     */
    public void setExchangeStatus(Integer exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    /**
     * 申请兑换时间,如:2016-08-18 12:53:30
     */
    public String getExchangeTime() {
        return this.exchangeTime;
    }

    /**
     * 申请兑换时间,如:2016-08-18 12:53:30
     */
    public void setExchangeTime(String exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    /**
     * 发货时间,如:2016-08-18 12:53:30
     */
    public String getSendTime() {
        return this.sendTime;
    }

    /**
     * 发货时间,如:2016-08-18 12:53:30
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 金币
     */
    public Integer getScore() {
        return this.score;
    }

    /**
     * 金币
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 虚拟商品的卡号
     */
    public String getCardId() {
        return this.cardId;
    }

    /**
     * 虚拟商品的卡号
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * 虚拟商品卡密
     */
    public String getCardPassword() {
        return this.cardPassword;
    }

    /**
     * 虚拟商品卡密
     */
    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword;
    }

    /**
     * 实物快递单号
     */
    public String getExpressNumber() {
        return this.expressNumber;
    }

    /**
     * 实物快递单号
     */
    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    /**
     * 过期时间
     */
    public String getInvalidTime() {
        return this.invalidTime;
    }

    /**
     * 过期时间
     */
    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

}