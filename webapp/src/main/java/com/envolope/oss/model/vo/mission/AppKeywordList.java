package com.envolope.oss.model.vo.mission;

import com.envolope.oss.model.ReAppKeywords;
import com.envolope.oss.model.ReAppMarket;

import java.util.List;

/**
 * Created by wujie5 on 2016/11/23.
 */
public class AppKeywordList {

    /**
     * <id column="app_id" property="appId" jdbcType="BIGINT"/>
     <result column="app_name" property="appName" jdbcType="VARCHAR"/>
     <result column="market_title_icon" property="marketTitleIcon" jdbcType="VARCHAR"/>
     <collection property="keywordList" column="app_id" javaType="ArrayList" ofType="ReAppKeywords" sel
     */
    private Long appId;
    private String appName;
    private List<ReAppKeywords> keywordList;
    private ReAppMarket market;

    public ReAppMarket getMarket() {
        return market;
    }

    public void setMarket(ReAppMarket market) {
        this.market = market;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<ReAppKeywords> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<ReAppKeywords> keywordList) {
        this.keywordList = keywordList;
    }
}
