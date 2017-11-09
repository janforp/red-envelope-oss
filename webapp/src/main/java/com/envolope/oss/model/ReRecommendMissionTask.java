package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-31
 */
public class ReRecommendMissionTask implements java.io.Serializable {

    // Fields

    // id
    private Long taskId;
    // 用户ID
    private Integer userId;
    // 任务id
    private Long missionId;
    // 提交文字
    private String commitText;
    // 提交图片
    private String commitImg;
    // 状态; 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
    private Integer taskStatus;
    // 审核备注
    private String verifyRemarks;
    // 创建时间
    private Long createTime;
    // 更新时间
    private Long updateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReRecommendMissionTask() {
    }

    /**
     * full constructor
     */
    public ReRecommendMissionTask(Integer userId, Long missionId, String commitText, String commitImg, Integer taskStatus, String verifyRemarks, Long createTime, Long updateTime) {
        this.userId = userId;
        this.missionId = missionId;
        this.commitText = commitText;
        this.commitImg = commitImg;
        this.taskStatus = taskStatus;
        this.verifyRemarks = verifyRemarks;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Property accessors

    /**
     * id
     */
    public Long getTaskId() {
        return this.taskId;
    }

    /**
     * id
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * 用户ID
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * 提交文字
     */
    public String getCommitText() {
        return this.commitText;
    }

    /**
     * 提交文字
     */
    public void setCommitText(String commitText) {
        this.commitText = commitText;
    }

    /**
     * 提交图片
     */
    public String getCommitImg() {
        return this.commitImg;
    }

    /**
     * 提交图片
     */
    public void setCommitImg(String commitImg) {
        this.commitImg = commitImg;
    }

    /**
     * 状态; 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
     */
    public Integer getTaskStatus() {
        return this.taskStatus;
    }

    /**
     * 状态; 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
     */
    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 审核备注
     */
    public String getVerifyRemarks() {
        return this.verifyRemarks;
    }

    /**
     * 审核备注
     */
    public void setVerifyRemarks(String verifyRemarks) {
        this.verifyRemarks = verifyRemarks;
    }

    /**
     * 创建时间
     */
    public Long getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Long getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

}