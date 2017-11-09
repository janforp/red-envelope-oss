package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-29
 */
public class ReChannel implements java.io.Serializable {

    // Fields

    // ID
    private Long id;
    // 平台 0-iOS, 1-Android
    private Integer platform;
    // 渠道名
    private String channelName;

    // Constructors

    /**
     * default constructor
     */
    public ReChannel() {
    }

    /**
     * full constructor
     */
    public ReChannel(Integer platform, String channelName) {
        this.platform = platform;
        this.channelName = channelName;
    }

    // Property accessors

    /**
     * ID
     */
    public Long getId() {
        return this.id;
    }

    /**
     * ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 平台 0-iOS, 1-Android
     */
    public Integer getPlatform() {
        return this.platform;
    }

    /**
     * 平台 0-iOS, 1-Android
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * 渠道名
     */
    public String getChannelName() {
        return this.channelName;
    }

    /**
     * 渠道名
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

}