package com.envolope.oss.model.vo.recommend;

import java.io.Serializable;

/**
 * Created by Jan on 16/11/8.
 * 用户提交标签
 */
public class UserCommitLabelVo implements Serializable{

    // id，自增长
    private Long requireId;
    // 要求名字
    private String requireName;

    private String requireContent;

    private String submitTime;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public Long getRequireId() {
        return requireId;
    }

    public void setRequireId(Long requireId) {
        this.requireId = requireId;
    }

    public String getRequireName() {
        return requireName;
    }

    public void setRequireName(String requireName) {
        this.requireName = requireName;
    }

    public String getRequireContent() {
        return requireContent;
    }

    public void setRequireContent(String requireContent) {
        this.requireContent = requireContent;
    }
}
