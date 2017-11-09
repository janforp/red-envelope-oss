package com.envolope.oss.model.para;

import java.io.Serializable;

/**
 * Created by Janita on 2016/12/12.
 * 福利列表查询
 */
public class WelfareSelectParamVo implements Serializable{
    //福利标题
    private String  welfareTitle;
    //福利类型
    private String  welfareType;
    //页数
    private Integer pageNum =1;
    //是否精选， 0-否,1-是
    private Integer isSelection;
    //商家名称
    private String merchantName;

    public Integer getPageNum() {

        if (this.pageNum == 0 || this.pageNum == null){
            return 1;
        }
        return pageNum;
    }

    public String getWelfareTitle() {
        return welfareTitle;
    }

    public void setWelfareTitle(String welfareTitle) {
        this.welfareTitle = welfareTitle;
    }

    public String getWelfareType() {
        return welfareType;
    }

    public void setWelfareType(String welfareType) {
        this.welfareType = welfareType;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getIsSelection() {
        return isSelection;
    }

    public void setIsSelection(Integer isSelection) {
        this.isSelection = isSelection;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
