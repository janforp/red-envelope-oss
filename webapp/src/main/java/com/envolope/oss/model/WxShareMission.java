package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-31
 */
public class WxShareMission implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long missionId;
    // 任务名字
    private String missionTitle;
    // 任务图标
    private String missionIcon;
    // 任务描述
    private String missionDesc;
    // 中奖概率 0-100
    private Integer prizeRate;
    // 最小金额(分)
    private Integer minMoney;
    // 最大金额(分)
    private Integer maxMoney;
    // 允许中奖次数
    private Integer prizeTimes;
    // 每个用户允许抽奖次数
    private Integer lotteryTimes;
    // 红包总数量
    private Integer totalRedNum;
    // 剩余红包数量
    private Integer leftRedNum;
    // 是否验证IP地址,0:不需要,1:需要
    private Integer verifyIp;
    // 是否加密，0:不需要加密，1:需要加密
    private Integer isEncrypted;
    // 商户加密密钥
    private String merchantSecret;
    // 省,如:浙江。则只有IP是浙江的用户才能参加此活动
    private String missionProvince;
    // 市/县,如:杭州。则只有IP是杭州的用户才能参加此活动
    private String missionCity;
    // 商家名字
    private String merchantName;
    // 分享链接(按客户要求)
    private String shareUrl;
    // 分享标题(按客户要求)
    private String shareTitle;
    // 分享图片(按客户要求)
    private String shareImg;
    // 分享描述(按客户要求)
    private String shareDesc;
    // 分享类型:music、video或link，不填默认为link
    private String shareType;
    // 分享数据链接,如果type是music或video，则要提供数据链接，默认为空
    private String shareDataurl;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 领取红包成功页面图片
    private String successImg;
    // 没有获得红包页面图片
    private String failImg;
    // 点击打开红包页面图片
    private String openImg;
    // 活动是否已经结束，0:已经结束，1:没有结束
    private Integer isEnd;

    // Constructors

    /**
     * default constructor
     */
    public WxShareMission() {
    }

    /**
     * full constructor
     */
    public WxShareMission(String missionTitle, String missionIcon, String missionDesc, Integer prizeRate, Integer minMoney, Integer maxMoney, Integer prizeTimes, Integer lotteryTimes, Integer totalRedNum, Integer leftRedNum, Integer verifyIp, Integer isEncrypted, String merchantSecret, String missionProvince, String missionCity, String merchantName, String shareUrl, String shareTitle, String shareImg, String shareDesc, String shareType, String shareDataurl, String createTime, String successImg, String failImg, String openImg, Integer isEnd) {
        this.missionTitle = missionTitle;
        this.missionIcon = missionIcon;
        this.missionDesc = missionDesc;
        this.prizeRate = prizeRate;
        this.minMoney = minMoney;
        this.maxMoney = maxMoney;
        this.prizeTimes = prizeTimes;
        this.lotteryTimes = lotteryTimes;
        this.totalRedNum = totalRedNum;
        this.leftRedNum = leftRedNum;
        this.verifyIp = verifyIp;
        this.isEncrypted = isEncrypted;
        this.merchantSecret = merchantSecret;
        this.missionProvince = missionProvince;
        this.missionCity = missionCity;
        this.merchantName = merchantName;
        this.shareUrl = shareUrl;
        this.shareTitle = shareTitle;
        this.shareImg = shareImg;
        this.shareDesc = shareDesc;
        this.shareType = shareType;
        this.shareDataurl = shareDataurl;
        this.createTime = createTime;
        this.successImg = successImg;
        this.failImg = failImg;
        this.openImg = openImg;
        this.isEnd = isEnd;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getMissionId() {
        return this.missionId;
    }

    /**
     * id，自增长
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    /**
     * 任务名字
     */
    public String getMissionTitle() {
        return this.missionTitle;
    }

    /**
     * 任务名字
     */
    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }

    /**
     * 任务图标
     */
    public String getMissionIcon() {
        return this.missionIcon;
    }

    /**
     * 任务图标
     */
    public void setMissionIcon(String missionIcon) {
        this.missionIcon = missionIcon;
    }

    /**
     * 任务描述
     */
    public String getMissionDesc() {
        return this.missionDesc;
    }

    /**
     * 任务描述
     */
    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }

    /**
     * 中奖概率 0-100
     */
    public Integer getPrizeRate() {
        return this.prizeRate;
    }

    /**
     * 中奖概率 0-100
     */
    public void setPrizeRate(Integer prizeRate) {
        this.prizeRate = prizeRate;
    }

    /**
     * 最小金额(分)
     */
    public Integer getMinMoney() {
        return this.minMoney;
    }

    /**
     * 最小金额(分)
     */
    public void setMinMoney(Integer minMoney) {
        this.minMoney = minMoney;
    }

    /**
     * 最大金额(分)
     */
    public Integer getMaxMoney() {
        return this.maxMoney;
    }

    /**
     * 最大金额(分)
     */
    public void setMaxMoney(Integer maxMoney) {
        this.maxMoney = maxMoney;
    }

    /**
     * 允许中奖次数
     */
    public Integer getPrizeTimes() {
        return this.prizeTimes;
    }

    /**
     * 允许中奖次数
     */
    public void setPrizeTimes(Integer prizeTimes) {
        this.prizeTimes = prizeTimes;
    }

    /**
     * 每个用户允许抽奖次数
     */
    public Integer getLotteryTimes() {
        return this.lotteryTimes;
    }

    /**
     * 每个用户允许抽奖次数
     */
    public void setLotteryTimes(Integer lotteryTimes) {
        this.lotteryTimes = lotteryTimes;
    }

    /**
     * 红包总数量
     */
    public Integer getTotalRedNum() {
        return this.totalRedNum;
    }

    /**
     * 红包总数量
     */
    public void setTotalRedNum(Integer totalRedNum) {
        this.totalRedNum = totalRedNum;
    }

    /**
     * 剩余红包数量
     */
    public Integer getLeftRedNum() {
        return this.leftRedNum;
    }

    /**
     * 剩余红包数量
     */
    public void setLeftRedNum(Integer leftRedNum) {
        this.leftRedNum = leftRedNum;
    }

    /**
     * 是否验证IP地址,0:不需要,1:需要
     */
    public Integer getVerifyIp() {
        return this.verifyIp;
    }

    /**
     * 是否验证IP地址,0:不需要,1:需要
     */
    public void setVerifyIp(Integer verifyIp) {
        this.verifyIp = verifyIp;
    }

    /**
     * 是否加密，0:不需要加密，1:需要加密
     */
    public Integer getIsEncrypted() {
        return this.isEncrypted;
    }

    /**
     * 是否加密，0:不需要加密，1:需要加密
     */
    public void setIsEncrypted(Integer isEncrypted) {
        this.isEncrypted = isEncrypted;
    }

    /**
     * 商户加密密钥
     */
    public String getMerchantSecret() {
        return this.merchantSecret;
    }

    /**
     * 商户加密密钥
     */
    public void setMerchantSecret(String merchantSecret) {
        this.merchantSecret = merchantSecret;
    }

    /**
     * 省,如:浙江。则只有IP是浙江的用户才能参加此活动
     */
    public String getMissionProvince() {
        return this.missionProvince;
    }

    /**
     * 省,如:浙江。则只有IP是浙江的用户才能参加此活动
     */
    public void setMissionProvince(String missionProvince) {
        this.missionProvince = missionProvince;
    }

    /**
     * 市/县,如:杭州。则只有IP是杭州的用户才能参加此活动
     */
    public String getMissionCity() {
        return this.missionCity;
    }

    /**
     * 市/县,如:杭州。则只有IP是杭州的用户才能参加此活动
     */
    public void setMissionCity(String missionCity) {
        this.missionCity = missionCity;
    }

    /**
     * 商家名字
     */
    public String getMerchantName() {
        return this.merchantName;
    }

    /**
     * 商家名字
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * 分享链接(按客户要求)
     */
    public String getShareUrl() {
        return this.shareUrl;
    }

    /**
     * 分享链接(按客户要求)
     */
    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    /**
     * 分享标题(按客户要求)
     */
    public String getShareTitle() {
        return this.shareTitle;
    }

    /**
     * 分享标题(按客户要求)
     */
    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    /**
     * 分享图片(按客户要求)
     */
    public String getShareImg() {
        return this.shareImg;
    }

    /**
     * 分享图片(按客户要求)
     */
    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }

    /**
     * 分享描述(按客户要求)
     */
    public String getShareDesc() {
        return this.shareDesc;
    }

    /**
     * 分享描述(按客户要求)
     */
    public void setShareDesc(String shareDesc) {
        this.shareDesc = shareDesc;
    }

    /**
     * 分享类型:music、video或link，不填默认为link
     */
    public String getShareType() {
        return this.shareType;
    }

    /**
     * 分享类型:music、video或link，不填默认为link
     */
    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    /**
     * 分享数据链接,如果type是music或video，则要提供数据链接，默认为空
     */
    public String getShareDataurl() {
        return this.shareDataurl;
    }

    /**
     * 分享数据链接,如果type是music或video，则要提供数据链接，默认为空
     */
    public void setShareDataurl(String shareDataurl) {
        this.shareDataurl = shareDataurl;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 领取红包成功页面图片
     */
    public String getSuccessImg() {
        return this.successImg;
    }

    /**
     * 领取红包成功页面图片
     */
    public void setSuccessImg(String successImg) {
        this.successImg = successImg;
    }

    /**
     * 没有获得红包页面图片
     */
    public String getFailImg() {
        return this.failImg;
    }

    /**
     * 没有获得红包页面图片
     */
    public void setFailImg(String failImg) {
        this.failImg = failImg;
    }

    /**
     * 点击打开红包页面图片
     */
    public String getOpenImg() {
        return this.openImg;
    }

    /**
     * 点击打开红包页面图片
     */
    public void setOpenImg(String openImg) {
        this.openImg = openImg;
    }

    /**
     * 活动是否已经结束，0:已经结束，1:没有结束
     */
    public Integer getIsEnd() {
        return this.isEnd;
    }

    /**
     * 活动是否已经结束，0:已经结束，1:没有结束
     */
    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

}