package com.envolope.oss.model.para;

import java.io.Serializable;

/**
 * Created by Jan on 2016/12/7.
 * 转发文章列表查询参数
 */
public class ArticleSelectParamVo implements Serializable {

    private String  articleTitle;

    private String  articleType;

    //0：未开始，1：进行中，2：已结束
    private String  status;

    //任务开始的时间
    private String startMissionTime;

    private Integer pageNum =1;

    public Integer getPageNum() {

        if (this.pageNum == 0 || this.pageNum == null){
            return 1;
        }
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartMissionTime() {
        return startMissionTime;
    }

    public void setStartMissionTime(String startMissionTime) {
        this.startMissionTime = startMissionTime;
    }
}
