package com.envolope.oss.model.vo.mission;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jan on 2016/11/22.
 * 关键词任务列表／试玩任务
 */
public class KeywordListVo implements Serializable{

    private Long keywordId;
    private String appName;
    private String appIcon;
    private String marketTitleIcon;
    private String keyword;
    private BigDecimal money;
    private Integer totalNum;
    private Integer leftNum;
    private String releaseTime;
    private String endTime;
    private String status;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getMarketTitleIcon() {
        return marketTitleIcon;
    }

    public void setMarketTitleIcon(String marketTitleIcon) {
        this.marketTitleIcon = marketTitleIcon;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
}
