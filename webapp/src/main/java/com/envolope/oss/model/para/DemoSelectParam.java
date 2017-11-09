package com.envolope.oss.model.para;

import java.io.Serializable;

/**
 * Created by Jan on 2016/11/22.
 * 试玩任务查询参数
 */
public class DemoSelectParam implements Serializable{

    //关键词
    private String  keyword;
    //APP name
    private String appName;
    //市场ID
    private Long marketId;
    //状态
    private Integer status;
    //投放时间
    private String releaseTime;
    //
    private Integer pageNum;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
}
