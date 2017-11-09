package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-07-21
 */
public class ReUserRecord implements java.io.Serializable {

    // Fields

    // id
    private Integer id;
    // 推广id
    private Integer extendId;
    // openid
    private String userOpenid;
    // 昵称
    private String userNickname;
    // 头像
    private String userImg;
    // 红包金额(单位:分)
    private Integer envelopeMoney;
    // 领取时间
    private Integer createTime;
    // ip地址
    private String userIp;

    // Constructors

    /**
     * default constructor
     */
    public ReUserRecord() {
    }

    /**
     * full constructor
     */
    public ReUserRecord(Integer extendId, String userOpenid, String userNickname, String userImg, Integer envelopeMoney, Integer createTime, String userIp) {
        this.extendId = extendId;
        this.userOpenid = userOpenid;
        this.userNickname = userNickname;
        this.userImg = userImg;
        this.envelopeMoney = envelopeMoney;
        this.createTime = createTime;
        this.userIp = userIp;
    }

    // Property accessors

    /**
     * id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 推广id
     */
    public Integer getExtendId() {
        return this.extendId;
    }

    /**
     * 推广id
     */
    public void setExtendId(Integer extendId) {
        this.extendId = extendId;
    }

    /**
     * openid
     */
    public String getUserOpenid() {
        return this.userOpenid;
    }

    /**
     * openid
     */
    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid;
    }

    /**
     * 昵称
     */
    public String getUserNickname() {
        return this.userNickname;
    }

    /**
     * 昵称
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    /**
     * 头像
     */
    public String getUserImg() {
        return this.userImg;
    }

    /**
     * 头像
     */
    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    /**
     * 红包金额(单位:分)
     */
    public Integer getEnvelopeMoney() {
        return this.envelopeMoney;
    }

    /**
     * 红包金额(单位:分)
     */
    public void setEnvelopeMoney(Integer envelopeMoney) {
        this.envelopeMoney = envelopeMoney;
    }

    /**
     * 领取时间
     */
    public Integer getCreateTime() {
        return this.createTime;
    }

    /**
     * 领取时间
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     * ip地址
     */
    public String getUserIp() {
        return this.userIp;
    }

    /**
     * ip地址
     */
    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

}