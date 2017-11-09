package com.envolope.oss.model.vo.user.statistics;

import com.envolope.oss.model.ReUserDataStatistics;

/**
 * Created by Summer on 2016/11/30.
 */
public class UserDataVo extends ReUserDataStatistics {

    // 开始时间
    private String startTime;
    // 结束时间
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
