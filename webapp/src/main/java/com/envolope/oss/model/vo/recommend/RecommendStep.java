package com.envolope.oss.model.vo.recommend;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jan on 16/11/1.
 *  图片分开的步骤
 */
public class RecommendStep implements Serializable{

    //步骤ID
    private Long stepId;
    // 任务步骤号
    private Integer stepNum;
    // 任务id
    private Long missionId;
    // 步骤内容
    private String stepContent;
    // 对于的按钮html
    private String btnHtml;
    // 步骤图片
    private List<RandomImgVo> randomImgVos;
    // 状态
    private Integer stepStatus;


    public Long getStepId() {
        return stepId;
    }

    public void setStepId(Long stepId) {
        this.stepId = stepId;
    }

    public Integer getStepStatus() {
        return stepStatus;
    }

    public void setStepStatus(Integer stepStatus) {
        this.stepStatus = stepStatus;
    }

    public String getBtnHtml() {
        return btnHtml;
    }

    public void setBtnHtml(String btnHtml) {
        this.btnHtml = btnHtml;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Integer getStepNum() {
        return stepNum;
    }

    public void setStepNum(Integer stepNum) {
        this.stepNum = stepNum;
    }

    public String getStepContent() {
        return stepContent;
    }

    public void setStepContent(String stepContent) {
        this.stepContent = stepContent;
    }

    public List<RandomImgVo> getRandomImgVos() {
        return randomImgVos;
    }

    public void setRandomImgVos(List<RandomImgVo> randomImgVos) {
        this.randomImgVos = randomImgVos;
    }
}
