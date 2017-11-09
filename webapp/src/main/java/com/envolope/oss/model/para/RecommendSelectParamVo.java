package com.envolope.oss.model.para;

import java.io.Serializable;

/**
 * Created by Jan on 16/10/31.
 *  推荐任务
 */
public class RecommendSelectParamVo  implements Serializable{

    //任务分类
    private Integer type;
    //任务名称
    private String  missionTitle;
    //是否限时,0限时,1不限时
    private Integer isLimitTime;
    //是否需要审核, 0:审核, 1:不审核
    private Integer isVerify;
    //页码
    private Integer pageNum;
    //pageSize
    private Integer pageSize;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMissionTitle() {
        return missionTitle;
    }

    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }

    public Integer getIsLimitTime() {
        return isLimitTime;
    }

    public void setIsLimitTime(Integer isLimitTime) {
        this.isLimitTime = isLimitTime;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

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
}
