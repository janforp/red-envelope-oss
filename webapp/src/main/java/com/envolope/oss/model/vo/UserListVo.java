package com.envolope.oss.model.vo;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-07-15
 */
public class UserListVo implements java.io.Serializable {

    // Fields

    // id
    private Integer id;
    // 公众微信号
    private String customerWx;
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
    //2016-7-25 22:5:45
    private String  showTime;
    // ip地址
    private String userIp;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

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
     * 公众微信号
     */
    public String getCustomerWx() {
        return this.customerWx;
    }

    /**
     * 公众微信号
     */
    public void setCustomerWx(String customerWx) {
        this.customerWx = customerWx;
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