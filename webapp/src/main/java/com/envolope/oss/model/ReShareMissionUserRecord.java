package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-28
 */
public class ReShareMissionUserRecord implements java.io.Serializable {

    // Fields

    // 任务ID
    private Long missionId;
    // 用户ID
    private Integer userId;
    // 生成的短链接
    private String shortUrl;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;
    // 生成时间,如:2016-08-18 12:53:30
    private String createTime;

    // Constructors

    /**
     * default constructor
     */
    public ReShareMissionUserRecord() {
    }

    /**
     * full constructor
     */
    public ReShareMissionUserRecord(Long missionId, Integer userId, String shortUrl, String updateTime, String createTime) {
        this.missionId = missionId;
        this.userId = userId;
        this.shortUrl = shortUrl;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    // Property accessors

    /**
     * 任务ID
     */
    public Long getMissionId() {
        return this.missionId;
    }

    /**
     * 任务ID
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
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
     * 生成的短链接
     */
    public String getShortUrl() {
        return this.shortUrl;
    }

    /**
     * 生成的短链接
     */
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 生成时间,如:2016-08-18 12:53:30
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 生成时间,如:2016-08-18 12:53:30
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}