package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-04
 */
public class ReAccessToken implements java.io.Serializable {

    // Fields

    // appid
    private String appId;
    // access_token
    private String accessToken;
    // access_token有效时间
    private Long timeMillis;

    // Constructors

    /**
     * default constructor
     */
    public ReAccessToken() {
    }

    /**
     * full constructor
     */
    public ReAccessToken(String appId, String accessToken, Long timeMillis) {
        this.appId = appId;
        this.accessToken = accessToken;
        this.timeMillis = timeMillis;
    }

    // Property accessors

    /**
     * appid
     */
    public String getAppId() {
        return this.appId;
    }

    /**
     * appid
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * access_token
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * access_token
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * access_token有效时间
     */
    public Long getTimeMillis() {
        return this.timeMillis;
    }

    /**
     * access_token有效时间
     */
    public void setTimeMillis(Long timeMillis) {
        this.timeMillis = timeMillis;
    }

}