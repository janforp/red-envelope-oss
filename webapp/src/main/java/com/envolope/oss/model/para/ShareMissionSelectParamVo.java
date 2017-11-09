package com.envolope.oss.model.para;

import java.io.Serializable;

/**
 * Created by Jan on 16/10/31.
 * 分享任务查询条件
 */
public class ShareMissionSelectParamVo implements Serializable{

    //任务名称
    private String missionTitle;
    //商家名称
    private String  merchantName;
    //是否结束:0:结束,1:没有结束
    private Integer isEnd;


    private Integer pageSize;

    private Integer pageNum;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getMissionTitle() {
        return missionTitle;
    }

    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }
}
