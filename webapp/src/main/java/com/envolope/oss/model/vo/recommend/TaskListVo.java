package com.envolope.oss.model.vo.recommend;

import java.io.Serializable;

/**
 * Created by Jan on 16/11/8.
 * 推荐任务审核任务列表
 */
public class TaskListVo implements Serializable {

    private Long    taskId;
    private Integer status;
    private String  title;
    private String  money;
    private String  phone;
    private String  time;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
