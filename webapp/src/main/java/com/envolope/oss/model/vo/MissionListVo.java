package com.envolope.oss.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/8/12.
 */
public class MissionListVo  implements Serializable {

    private Integer missionId;
    // 任务名字
    private String missionName;
    // 商家名字
    private String merchantDetail;
    // 商家名字
    private String merchantName;
    // 任务图标
    private String missionImg;
    // 奖励,如:100元红包券,10元观影券,购物券等
    private String missionReward;
    // 目前获得奖励的人数
    private Integer gainRewardNum;
    // 任务广告图片
    private String missionAdImg;
    // 广告图片的链接,可以是网址,也可以是app下载地址
    private String missionUrl;
    // 是否为热门任务,0:否,1:是
    private Integer missionHot;
    //是否热门
    private String  showHot;
    // 任务分类
    private Integer missionSort;
    //分类名称
    private String sortName;
    // 热舞排序,值较小者排在较前
    private Integer missionOrder;
    // 参加人数
    private Integer participantsNum;
    // 开始时间
    private Integer startTime;
    // 结束时间
    private Integer endTime;
    // 任务状态,0:已结束,1:进行中
    private Integer missionStatus;
    //
    private String showStatus;
    // 任务步骤
    private String missionStep;
    // 任务规则
    private String missionRule;
    // 奖励,如:100元红包券,10元观影券,购物券等
    private String missionExtraReward;
    // 任务开关,控制是否需要在页面显示,0:不显示,1:显示
    private Integer showOrNot;
    //是否显示
    private String showNot;

    public String getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getMerchantDetail() {
        return merchantDetail;
    }

    public void setMerchantDetail(String merchantDetail) {
        this.merchantDetail = merchantDetail;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMissionImg() {
        return missionImg;
    }

    public void setMissionImg(String missionImg) {
        this.missionImg = missionImg;
    }

    public String getMissionReward() {
        return missionReward;
    }

    public void setMissionReward(String missionReward) {
        this.missionReward = missionReward;
    }

    public Integer getGainRewardNum() {
        return gainRewardNum;
    }

    public void setGainRewardNum(Integer gainRewardNum) {
        this.gainRewardNum = gainRewardNum;
    }

    public String getMissionAdImg() {
        return missionAdImg;
    }

    public void setMissionAdImg(String missionAdImg) {
        this.missionAdImg = missionAdImg;
    }

    public String getMissionUrl() {
        return missionUrl;
    }

    public void setMissionUrl(String missionUrl) {
        this.missionUrl = missionUrl;
    }

    public Integer getMissionHot() {
        return missionHot;
    }

    public void setMissionHot(Integer missionHot) {
        this.missionHot = missionHot;
    }

    public String getShowHot() {
        return showHot;
    }

    public void setShowHot(String showHot) {
        this.showHot = showHot;
    }

    public Integer getMissionSort() {
        return missionSort;
    }

    public void setMissionSort(Integer missionSort) {
        this.missionSort = missionSort;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public Integer getMissionOrder() {
        return missionOrder;
    }

    public void setMissionOrder(Integer missionOrder) {
        this.missionOrder = missionOrder;
    }

    public Integer getParticipantsNum() {
        return participantsNum;
    }

    public void setParticipantsNum(Integer participantsNum) {
        this.participantsNum = participantsNum;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(Integer missionStatus) {
        this.missionStatus = missionStatus;
    }

    public String getMissionStep() {
        return missionStep;
    }

    public void setMissionStep(String missionStep) {
        this.missionStep = missionStep;
    }

    public String getMissionRule() {
        return missionRule;
    }

    public void setMissionRule(String missionRule) {
        this.missionRule = missionRule;
    }

    public String getMissionExtraReward() {
        return missionExtraReward;
    }

    public void setMissionExtraReward(String missionExtraReward) {
        this.missionExtraReward = missionExtraReward;
    }

    public Integer getShowOrNot() {
        return showOrNot;
    }

    public void setShowOrNot(Integer showOrNot) {
        this.showOrNot = showOrNot;
    }

    public String getShowNot() {
        return showNot;
    }

    public void setShowNot(String showNot) {
        this.showNot = showNot;
    }
}
