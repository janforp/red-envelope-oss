package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-25
 */
public class ReDiscoverBanner implements java.io.Serializable {

    // Fields

    // BannerID
    private Integer bannerId;
    // Banner标题
    private String bannerTitle;
    // Banner图片
    private String bannerImg;
    // Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
    private Integer bannerStatus;
    // Banner链接
    private String bannerUrl;
    // Banner排序，值越小，越靠前
    private Integer bannerOrder;
    // 最低显示版本
    private String limitVersion;
    // 最高版本
    private String maxVersion;
    // 最低显示版本的渠道名
    private String limitChannelName;
    // 最低显示版本的包名
    private String limitPackageName;
    // 审核状态是否显示（默认为0） 0-不显示；1-显示
    private Integer isShow;

    // Constructors

    /**
     * default constructor
     */
    public ReDiscoverBanner() {
    }

    /**
     * full constructor
     */
    public ReDiscoverBanner(String bannerTitle, String bannerImg, Integer bannerStatus, String bannerUrl, Integer bannerOrder, String limitVersion, String maxVersion, String limitChannelName, String limitPackageName, Integer isShow) {
        this.bannerTitle = bannerTitle;
        this.bannerImg = bannerImg;
        this.bannerStatus = bannerStatus;
        this.bannerUrl = bannerUrl;
        this.bannerOrder = bannerOrder;
        this.limitVersion = limitVersion;
        this.maxVersion = maxVersion;
        this.limitChannelName = limitChannelName;
        this.limitPackageName = limitPackageName;
        this.isShow = isShow;
    }

    // Property accessors

    /**
     * BannerID
     */
    public Integer getBannerId() {
        return this.bannerId;
    }

    /**
     * BannerID
     */
    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    /**
     * Banner标题
     */
    public String getBannerTitle() {
        return this.bannerTitle;
    }

    /**
     * Banner标题
     */
    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    /**
     * Banner图片
     */
    public String getBannerImg() {
        return this.bannerImg;
    }

    /**
     * Banner图片
     */
    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    /**
     * Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getBannerStatus() {
        return this.bannerStatus;
    }

    /**
     * Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public void setBannerStatus(Integer bannerStatus) {
        this.bannerStatus = bannerStatus;
    }

    /**
     * Banner链接
     */
    public String getBannerUrl() {
        return this.bannerUrl;
    }

    /**
     * Banner链接
     */
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    /**
     * Banner排序，值越小，越靠前
     */
    public Integer getBannerOrder() {
        return this.bannerOrder;
    }

    /**
     * Banner排序，值越小，越靠前
     */
    public void setBannerOrder(Integer bannerOrder) {
        this.bannerOrder = bannerOrder;
    }

    /**
     * 最低显示版本
     */
    public String getLimitVersion() {
        return this.limitVersion;
    }

    /**
     * 最低显示版本
     */
    public void setLimitVersion(String limitVersion) {
        this.limitVersion = limitVersion;
    }

    /**
     * 最高版本
     */
    public String getMaxVersion() {
        return this.maxVersion;
    }

    /**
     * 最高版本
     */
    public void setMaxVersion(String maxVersion) {
        this.maxVersion = maxVersion;
    }

    /**
     * 最低显示版本的渠道名
     */
    public String getLimitChannelName() {
        return this.limitChannelName;
    }

    /**
     * 最低显示版本的渠道名
     */
    public void setLimitChannelName(String limitChannelName) {
        this.limitChannelName = limitChannelName;
    }

    /**
     * 最低显示版本的包名
     */
    public String getLimitPackageName() {
        return this.limitPackageName;
    }

    /**
     * 最低显示版本的包名
     */
    public void setLimitPackageName(String limitPackageName) {
        this.limitPackageName = limitPackageName;
    }

    /**
     * 审核状态是否显示（默认为0） 0-不显示；1-显示
     */
    public Integer getIsShow() {
        return this.isShow;
    }

    /**
     * 审核状态是否显示（默认为0） 0-不显示；1-显示
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

}