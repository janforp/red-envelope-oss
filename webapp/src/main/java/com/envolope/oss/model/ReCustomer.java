package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-08
 */
public class ReCustomer implements java.io.Serializable {

    // Fields

    // 客户id
    private Integer customerId;
    // 客户类型 0-普通商户,1-公众号
    private Integer customerType;
    // 客户名称
    private String customerName;
    // 客户头像
    private String customerImg;
    // 客户秘钥(随机字符串)
    private String customerSecret;
    // 微信号
    private String customerWx;
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
    // 是否是开发模式，0:普通，1:开发模式，默认为0
    private Integer mode;

    // Constructors

    /**
     * default constructor
     */
    public ReCustomer() {
    }

    /**
     * full constructor
     */
    public ReCustomer(Integer customerType, String customerName, String customerImg, String customerSecret, String customerWx, String customerAppid, String customerAppsecret, String customerToken, String customerAeskey, Integer customerSendtype, Integer createTime, Integer mode) {
        this.customerType = customerType;
        this.customerName = customerName;
        this.customerImg = customerImg;
        this.customerSecret = customerSecret;
        this.customerWx = customerWx;
        this.customerAppid = customerAppid;
        this.customerAppsecret = customerAppsecret;
        this.customerToken = customerToken;
        this.customerAeskey = customerAeskey;
        this.customerSendtype = customerSendtype;
        this.createTime = createTime;
        this.mode = mode;
    }

    // Property accessors

    /**
     * 客户id
     */
    public Integer getCustomerId() {
        return this.customerId;
    }

    /**
     * 客户id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 客户类型 0-普通商户,1-公众号
     */
    public Integer getCustomerType() {
        return this.customerType;
    }

    /**
     * 客户类型 0-普通商户,1-公众号
     */
    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    /**
     * 客户名称
     */
    public String getCustomerName() {
        return this.customerName;
    }

    /**
     * 客户名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 客户头像
     */
    public String getCustomerImg() {
        return this.customerImg;
    }

    /**
     * 客户头像
     */
    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    /**
     * 客户秘钥(随机字符串)
     */
    public String getCustomerSecret() {
        return this.customerSecret;
    }

    /**
     * 客户秘钥(随机字符串)
     */
    public void setCustomerSecret(String customerSecret) {
        this.customerSecret = customerSecret;
    }

    /**
     * 微信号
     */
    public String getCustomerWx() {
        return this.customerWx;
    }

    /**
     * 微信号
     */
    public void setCustomerWx(String customerWx) {
        this.customerWx = customerWx;
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

    /**
     * 是否是开发模式，0:普通，1:开发模式，默认为0
     */
    public Integer getMode() {
        return this.mode;
    }

    /**
     * 是否是开发模式，0:普通，1:开发模式，默认为0
     */
    public void setMode(Integer mode) {
        this.mode = mode;
    }

}