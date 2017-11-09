package com.envolope.oss.model.vo;

/**
 * Created by Jan on 2016-07-13
 */
public class ReCustomerListVo implements java.io.Serializable {

    // Fields
    //客户id
    private Integer customerId;

    //客户类型 0-普通商户,1-公众号
    private Integer customerType;
    //客户类型 0-普通商户,1-公众号
    private String customerTypeShow;
    //客户秘钥(随机字符串)
    private String customerSecret;
    // 公众微信号
    private String customerWx;
    // 公众号名称
    private String customerName;
    // 头像
    private String customerImg;
    // appid
    private String customerAppid;
    // appsecret
    private String customerAppsecret;
    // token
    private String customerToken;
    // AESKey
    private String customerAeskey;
    // 消息加密方式 0-明文模式,1-兼容模式,2-安全模式
    private Integer customerSendtype;
    // 创建时间
    private Integer createTime;
    //2016-07-20格式的时间
    private String  showTime;
    //是否是开发者模式 0:普通,1:开发者模式
    private Integer mode;

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getCustomerTypeShow() {
        return customerTypeShow;
    }

    public void setCustomerTypeShow(String customerTypeShow) {
        this.customerTypeShow = customerTypeShow;
    }

    public String getCustomerSecret() {
        return customerSecret;
    }

    public void setCustomerSecret(String customerSecret) {
        this.customerSecret = customerSecret;
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
     * 公众号名称
     */
    public String getCustomerName() {
        return this.customerName;
    }

    /**
     * 公众号名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 头像
     */
    public String getCustomerImg() {
        return this.customerImg;
    }

    /**
     * 头像
     */
    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    /**
     * appid
     */
    public String getCustomerAppid() {
        return this.customerAppid;
    }

    /**
     * appid
     */
    public void setCustomerAppid(String customerAppid) {
        this.customerAppid = customerAppid;
    }

    /**
     * appsecret
     */
    public String getCustomerAppsecret() {
        return this.customerAppsecret;
    }

    /**
     * appsecret
     */
    public void setCustomerAppsecret(String customerAppsecret) {
        this.customerAppsecret = customerAppsecret;
    }

    /**
     * token
     */
    public String getCustomerToken() {
        return this.customerToken;
    }

    /**
     * token
     */
    public void setCustomerToken(String customerToken) {
        this.customerToken = customerToken;
    }

    /**
     * AESKey
     */
    public String getCustomerAeskey() {
        return this.customerAeskey;
    }

    /**
     * AESKey
     */
    public void setCustomerAeskey(String customerAeskey) {
        this.customerAeskey = customerAeskey;
    }

    /**
     * 消息加密方式 0-明文模式,1-兼容模式,2-安全模式
     */
    public Integer getCustomerSendtype() {
        return this.customerSendtype;
    }

    /**
     * 消息加密方式 0-明文模式,1-兼容模式,2-安全模式
     */
    public void setCustomerSendtype(Integer customerSendtype) {
        this.customerSendtype = customerSendtype;
    }

    /**
     * 创建时间
     */
    public Integer getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}