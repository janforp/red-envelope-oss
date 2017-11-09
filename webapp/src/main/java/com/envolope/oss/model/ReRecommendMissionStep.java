package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-07
 */
public class ReRecommendMissionStep implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long stepId;
    // 任务id
    private Long missionId;
    // 任务步骤号
    private Integer stepNum;
    // 步骤内容
    private String stepContent;
    // 步骤图片
    private String stepImgs;
    // 步骤按钮
    private String stepBtn;
    // 显示平台, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
    private Integer stepStatus;

    // Constructors

    /**
     * default constructor
     */
    public ReRecommendMissionStep() {
    }

    /**
     * full constructor
     */
    public ReRecommendMissionStep(Long missionId, Integer stepNum, String stepContent, String stepImgs, String stepBtn, Integer stepStatus) {
        this.missionId = missionId;
        this.stepNum = stepNum;
        this.stepContent = stepContent;
        this.stepImgs = stepImgs;
        this.stepBtn = stepBtn;
        this.stepStatus = stepStatus;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getStepId() {
        return this.stepId;
    }

    /**
     * id，自增长
     */
    public void setStepId(Long stepId) {
        this.stepId = stepId;
    }

    /**
     * 任务id
     */
    public Long getMissionId() {
        return this.missionId;
    }

    /**
     * 任务id
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    /**
     * 任务步骤号
     */
    public Integer getStepNum() {
        return this.stepNum;
    }

    /**
     * 任务步骤号
     */
    public void setStepNum(Integer stepNum) {
        this.stepNum = stepNum;
    }

    /**
     * 步骤内容
     */
    public String getStepContent() {
        return this.stepContent;
    }

    /**
     * 步骤内容
     */
    public void setStepContent(String stepContent) {
        this.stepContent = stepContent;
    }

    /**
     * 步骤图片
     */
    public String getStepImgs() {
        return this.stepImgs;
    }

    /**
     * 步骤图片
     */
    public void setStepImgs(String stepImgs) {
        this.stepImgs = stepImgs;
    }

    /**
     * 步骤按钮
     */
    public String getStepBtn() {
        return this.stepBtn;
    }

    /**
     * 步骤按钮
     */
    public void setStepBtn(String stepBtn) {
        this.stepBtn = stepBtn;
    }

    /**
     * 显示平台, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getStepStatus() {
        return this.stepStatus;
    }

    /**
     * 显示平台, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public void setStepStatus(Integer stepStatus) {
        this.stepStatus = stepStatus;
    }

}