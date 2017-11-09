package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-24
 */
public class ReApp implements java.io.Serializable {

    // Fields

    // id
    private Long appId;
    // app名称
    private String appName;
    // app大小
    private String appSize;
    // 市场id
    private Integer marketId;
    // 包名
    private String appPackage;
    // app图片
    private String appIcon;
    // app描述
    private String appDesc;
    // 创建时间,如:2016-08-18
    private String createTime;
    // 修改时间，如2016-11-28 12:00:00
    private String updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReApp() {
    }

    /**
     * full constructor
     */
    public ReApp(String appName, String appSize, Integer marketId, String appPackage, String appIcon, String appDesc, String createTime, String updateTime) {
        this.appName = appName;
        this.appSize = appSize;
        this.marketId = marketId;
        this.appPackage = appPackage;
        this.appIcon = appIcon;
        this.appDesc = appDesc;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * id
     */
    public Long getAppId() {
        return this.appId;
    }

    /**
     * id
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * app名称
     */
    public String getAppName() {
        return this.appName;
    }

    /**
     * app名称
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * app大小
     */
    public String getAppSize() {
        return this.appSize;
    }

    /**
     * app大小
     */
    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    /**
     * 市场id
     */
    public Integer getMarketId() {
        return this.marketId;
    }

    /**
     * 市场id
     */
    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    /**
     * 包名
     */
    public String getAppPackage() {
        return this.appPackage;
    }

    /**
     * 包名
     */
    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    /**
     * app图片
     */
    public String getAppIcon() {
        return this.appIcon;
    }

    /**
     * app图片
     */
    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    /**
     * app描述
     */
    public String getAppDesc() {
        return this.appDesc;
    }

    /**
     * app描述
     */
    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    /**
     * 创建时间,如:2016-08-18
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间,如:2016-08-18
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间，如2016-11-28 12:00:00
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 修改时间，如2016-11-28 12:00:00
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}