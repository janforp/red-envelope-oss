package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-17
 */
public class ReSortRed implements java.io.Serializable {

    // Fields

    // 红包ID
    private Integer redId;
    // 红包名字
    private String redName;
    // 红包图标
    private String redImg;
    // 商家名字
    private String merchantName;
    // 商家详情
    private String merchantDetail;
    // 奖励描述,如:100元红包券,10元观影券,购物券等
    private String redRewardDesc;
    // 奖励金额(单位:分)
    private Integer rewardMoney;
    // 额外奖励描述,如:100元红包券,10元观影券,购物券等
    private String extraRewardDesc;
    // 额外奖励金额(单位:分)
    private Integer extraMoney;
    // 红包分类
    private Integer redSort;
    // 红包说明
    private String redDesc;
    // 详情链接
    private String detailUrl;
    // 详情编辑html
    private String detailDeitor;
    // 按钮编辑html,多个按钮之间用;;(两个分号)分开
    private String buttonDeitor;
    // 排序,值较小者排在较前
    private Integer redOrder;
    // 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
    private Integer redStatus;
    // 任务开关,控制是否需要在页面显示,0:不显示,1:显示
    private Integer showOrNot;
    // 开始时间
    private Long startTime;
    // 结束时间
    private Long endTime;
    // 创建时间
    private Long createTime;

    // Constructors

    /**
     * default constructor
     */
    public ReSortRed() {
    }

    /**
     * full constructor
     */
    public ReSortRed(String redName, String redImg, String merchantName, String merchantDetail, String redRewardDesc, Integer rewardMoney, String extraRewardDesc, Integer extraMoney, Integer redSort, String redDesc, String detailUrl, String detailDeitor, String buttonDeitor, Integer redOrder, Integer redStatus, Integer showOrNot, Long startTime, Long endTime, Long createTime) {
        this.redName = redName;
        this.redImg = redImg;
        this.merchantName = merchantName;
        this.merchantDetail = merchantDetail;
        this.redRewardDesc = redRewardDesc;
        this.rewardMoney = rewardMoney;
        this.extraRewardDesc = extraRewardDesc;
        this.extraMoney = extraMoney;
        this.redSort = redSort;
        this.redDesc = redDesc;
        this.detailUrl = detailUrl;
        this.detailDeitor = detailDeitor;
        this.buttonDeitor = buttonDeitor;
        this.redOrder = redOrder;
        this.redStatus = redStatus;
        this.showOrNot = showOrNot;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
    }

    // Property accessors

    /**
     * 红包ID
     */
    public Integer getRedId() {
        return this.redId;
    }

    /**
     * 红包ID
     */
    public void setRedId(Integer redId) {
        this.redId = redId;
    }

    /**
     * 红包名字
     */
    public String getRedName() {
        return this.redName;
    }

    /**
     * 红包名字
     */
    public void setRedName(String redName) {
        this.redName = redName;
    }

    /**
     * 红包图标
     */
    public String getRedImg() {
        return this.redImg;
    }

    /**
     * 红包图标
     */
    public void setRedImg(String redImg) {
        this.redImg = redImg;
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
     * 商家详情
     */
    public String getMerchantDetail() {
        return this.merchantDetail;
    }

    /**
     * 商家详情
     */
    public void setMerchantDetail(String merchantDetail) {
        this.merchantDetail = merchantDetail;
    }

    /**
     * 奖励描述,如:100元红包券,10元观影券,购物券等
     */
    public String getRedRewardDesc() {
        return this.redRewardDesc;
    }

    /**
     * 奖励描述,如:100元红包券,10元观影券,购物券等
     */
    public void setRedRewardDesc(String redRewardDesc) {
        this.redRewardDesc = redRewardDesc;
    }

    /**
     * 奖励金额(单位:分)
     */
    public Integer getRewardMoney() {
        return this.rewardMoney;
    }

    /**
     * 奖励金额(单位:分)
     */
    public void setRewardMoney(Integer rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

    /**
     * 额外奖励描述,如:100元红包券,10元观影券,购物券等
     */
    public String getExtraRewardDesc() {
        return this.extraRewardDesc;
    }

    /**
     * 额外奖励描述,如:100元红包券,10元观影券,购物券等
     */
    public void setExtraRewardDesc(String extraRewardDesc) {
        this.extraRewardDesc = extraRewardDesc;
    }

    /**
     * 额外奖励金额(单位:分)
     */
    public Integer getExtraMoney() {
        return this.extraMoney;
    }

    /**
     * 额外奖励金额(单位:分)
     */
    public void setExtraMoney(Integer extraMoney) {
        this.extraMoney = extraMoney;
    }

    /**
     * 红包分类
     */
    public Integer getRedSort() {
        return this.redSort;
    }

    /**
     * 红包分类
     */
    public void setRedSort(Integer redSort) {
        this.redSort = redSort;
    }

    /**
     * 红包说明
     */
    public String getRedDesc() {
        return this.redDesc;
    }

    /**
     * 红包说明
     */
    public void setRedDesc(String redDesc) {
        this.redDesc = redDesc;
    }

    /**
     * 详情链接
     */
    public String getDetailUrl() {
        return this.detailUrl;
    }

    /**
     * 详情链接
     */
    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    /**
     * 详情编辑html
     */
    public String getDetailDeitor() {
        return this.detailDeitor;
    }

    /**
     * 详情编辑html
     */
    public void setDetailDeitor(String detailDeitor) {
        this.detailDeitor = detailDeitor;
    }

    /**
     * 按钮编辑html,多个按钮之间用;;(两个分号)分开
     */
    public String getButtonDeitor() {
        return this.buttonDeitor;
    }

    /**
     * 按钮编辑html,多个按钮之间用;;(两个分号)分开
     */
    public void setButtonDeitor(String buttonDeitor) {
        this.buttonDeitor = buttonDeitor;
    }

    /**
     * 排序,值较小者排在较前
     */
    public Integer getRedOrder() {
        return this.redOrder;
    }

    /**
     * 排序,值较小者排在较前
     */
    public void setRedOrder(Integer redOrder) {
        this.redOrder = redOrder;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getRedStatus() {
        return this.redStatus;
    }

    /**
     * 状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭
     */
    public void setRedStatus(Integer redStatus) {
        this.redStatus = redStatus;
    }

    /**
     * 任务开关,控制是否需要在页面显示,0:不显示,1:显示
     */
    public Integer getShowOrNot() {
        return this.showOrNot;
    }

    /**
     * 任务开关,控制是否需要在页面显示,0:不显示,1:显示
     */
    public void setShowOrNot(Integer showOrNot) {
        this.showOrNot = showOrNot;
    }

    /**
     * 开始时间
     */
    public Long getStartTime() {
        return this.startTime;
    }

    /**
     * 开始时间
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     */
    public Long getEndTime() {
        return this.endTime;
    }

    /**
     * 结束时间
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * 创建时间
     */
    public Long getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}