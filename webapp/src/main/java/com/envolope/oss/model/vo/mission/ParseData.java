package com.envolope.oss.model.vo.mission;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jan on 2016/11/24.
 * 从个应用市场解析下来的数据
 */
public class ParseData implements Serializable {

    private Long keywordId;
    // 应用市场
    private Integer marketId;
    // app名称
    private String appName;
    // 包名
    private String appPackage;
    // 版本
    private String appDesc;
    //图片
    private String appIcon;
    //大小
    private String size;
    // 关键字
    private String keyword;
    //金额
    private BigDecimal money;
    //总次数
    private Integer totalNum;
    //剩余次数
    private Integer leftNum;
    //标签
    private String label;
    //权重
    private Integer keywordWeight;
    //释放任务时间
    private String releaseTime;
    //创建时间
    private String createTime;
    //结束时间
    private String endTime;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getKeywordWeight() {
        return keywordWeight;
    }

    public void setKeywordWeight(Integer keywordWeight) {
        this.keywordWeight = keywordWeight;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public Long getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }

    public Integer getMarketId() {
        return marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public void setKeywordId(long keywordId) {
        this.keywordId = keywordId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
