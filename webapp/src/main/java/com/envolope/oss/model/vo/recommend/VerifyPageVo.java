package com.envolope.oss.model.vo.recommend;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jan on 16/11/8.
 * 审核详情页面数据
 */
public class VerifyPageVo implements Serializable {

    //ID
    private Long    taskId;
    //备注要求
    private String  remarks;
    //截图要求
    private String  imgText;
    //要求图片
    private List<String> exampleImgs;
    //必须要填的标签:如:手机号-13854545454,用户名-得到的
    private List<UserCommitLabelVo> requires;
    //用户提交的图片
    private List<String> userImgs;
    //审核未通过的原因
    private String reason;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getImgText() {
        return imgText;
    }

    public void setImgText(String imgText) {
        this.imgText = imgText;
    }

    public List<String> getExampleImgs() {
        return exampleImgs;
    }

    public void setExampleImgs(List<String> exampleImgs) {
        this.exampleImgs = exampleImgs;
    }

    public List<UserCommitLabelVo> getRequires() {
        return requires;
    }

    public void setRequires(List<UserCommitLabelVo> requires) {
        this.requires = requires;
    }

    public List<String> getUserImgs() {
        return userImgs;
    }

    public void setUserImgs(List<String> userImgs) {
        this.userImgs = userImgs;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
