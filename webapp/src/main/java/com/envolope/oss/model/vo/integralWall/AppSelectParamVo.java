package com.envolope.oss.model.vo.integralWall;

/**
 * Created by Jan on 16/10/14.
 * 积分墙app查询参数
 */
public class AppSelectParamVo {

    private Integer pageNum;
    private Integer pageSize;
    private String  name;
    private Integer  marketId;
    private String  packageName;
    private String  startReleaseTime;
    private String  endReleaseTime;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMarketId() {
        return marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getStartReleaseTime() {
        return startReleaseTime;
    }

    public void setStartReleaseTime(String startReleaseTime) {
        this.startReleaseTime = startReleaseTime;
    }

    public String getEndReleaseTime() {
        return endReleaseTime;
    }

    public void setEndReleaseTime(String endReleaseTime) {
        this.endReleaseTime = endReleaseTime;
    }
}
