package com.envolope.oss.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/8/17.
 * 修改/添加 红包时传的数据
 */
public class RedVo  implements Serializable {

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
    private String showStartTime;
    // 结束时间
    private Long endTime;
    private String showEndTime;
    // 创建时间
    private Long createTime;
    private String showCreateTime;


    public Integer getRedId() {
        return redId;
    }

    public void setRedId(Integer redId) {
        this.redId = redId;
    }

    public String getRedName() {
        return redName;
    }

    public void setRedName(String redName) {
        this.redName = redName;
    }

    public String getRedImg() {
        return redImg;
    }

    public void setRedImg(String redImg) {
        this.redImg = redImg;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantDetail() {
        return merchantDetail;
    }

    public void setMerchantDetail(String merchantDetail) {
        this.merchantDetail = merchantDetail;
    }

    public String getRedRewardDesc() {
        return redRewardDesc;
    }

    public void setRedRewardDesc(String redRewardDesc) {
        this.redRewardDesc = redRewardDesc;
    }

    public Integer getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(Integer rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

    public String getExtraRewardDesc() {
        return extraRewardDesc;
    }

    public void setExtraRewardDesc(String extraRewardDesc) {
        this.extraRewardDesc = extraRewardDesc;
    }

    public Integer getExtraMoney() {
        return extraMoney;
    }

    public void setExtraMoney(Integer extraMoney) {
        this.extraMoney = extraMoney;
    }

    public Integer getRedSort() {
        return redSort;
    }

    public void setRedSort(Integer redSort) {
        this.redSort = redSort;
    }

    public String getRedDesc() {
        return redDesc;
    }

    public void setRedDesc(String redDesc) {
        this.redDesc = redDesc;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getDetailDeitor() {
        return detailDeitor;
    }

    public void setDetailDeitor(String detailDeitor) {
        this.detailDeitor = detailDeitor;
    }

    public String getButtonDeitor() {
        return buttonDeitor;
    }

    public void setButtonDeitor(String buttonDeitor) {
        this.buttonDeitor = buttonDeitor;
    }

    public Integer getRedOrder() {
        return redOrder;
    }

    public void setRedOrder(Integer redOrder) {
        this.redOrder = redOrder;
    }

    public Integer getRedStatus() {
        return redStatus;
    }

    public void setRedStatus(Integer redStatus) {
        this.redStatus = redStatus;
    }

    public Integer getShowOrNot() {
        return showOrNot;
    }

    public void setShowOrNot(Integer showOrNot) {
        this.showOrNot = showOrNot;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(String showStartTime) {
        this.showStartTime = showStartTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(String showEndTime) {
        this.showEndTime = showEndTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getShowCreateTime() {
        return showCreateTime;
    }

    public void setShowCreateTime(String showCreateTime) {
        this.showCreateTime = showCreateTime;
    }
}
