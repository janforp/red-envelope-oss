package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-31
 */
public class WxShareRedDetail implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long redId;
    // 0:未发送,1:成功,2:发送失败
    private Integer flag;
    // 任务ID
    private Long missionId;
    // 领取人ID
    private Long userId;
    // 金额(分)
    private Integer money;
    // 是否被领取,0:未领取,1:领取,2:领取失败
    private Integer redStatus;
    // 领取时间,如:2016-08-18 12:53:30
    private String receiveTime;

    // Constructors

    /**
     * default constructor
     */
    public WxShareRedDetail() {
    }

    /**
     * full constructor
     */
    public WxShareRedDetail(Integer flag, Long missionId, Long userId, Integer money, Integer redStatus, String receiveTime) {
        this.flag = flag;
        this.missionId = missionId;
        this.userId = userId;
        this.money = money;
        this.redStatus = redStatus;
        this.receiveTime = receiveTime;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getRedId() {
        return this.redId;
    }

    /**
     * id，自增长
     */
    public void setRedId(Long redId) {
        this.redId = redId;
    }

    /**
     * 0:未发送,1:成功,2:发送失败
     */
    public Integer getFlag() {
        return this.flag;
    }

    /**
     * 0:未发送,1:成功,2:发送失败
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 任务ID
     */
    public Long getMissionId() {
        return this.missionId;
    }

    /**
     * 任务ID
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    /**
     * 领取人ID
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * 领取人ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 金额(分)
     */
    public Integer getMoney() {
        return this.money;
    }

    /**
     * 金额(分)
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * 是否被领取,0:未领取,1:领取,2:领取失败
     */
    public Integer getRedStatus() {
        return this.redStatus;
    }

    /**
     * 是否被领取,0:未领取,1:领取,2:领取失败
     */
    public void setRedStatus(Integer redStatus) {
        this.redStatus = redStatus;
    }

    /**
     * 领取时间,如:2016-08-18 12:53:30
     */
    public String getReceiveTime() {
        return this.receiveTime;
    }

    /**
     * 领取时间,如:2016-08-18 12:53:30
     */
    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

}