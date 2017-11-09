package com.envolope.oss.model.vo.user;

import java.io.Serializable;

/**
 * Created by Jan on 16/10/13.
 * 用户金币记录页面上面的数据
 */
public class UserScoreVo implements Serializable {

    private Integer userId;
    private Integer id;
    private Integer score;
    private Integer totalGetScore;
    private Integer totalExchangeScore;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTotalGetScore() {
        return totalGetScore;
    }

    public void setTotalGetScore(Integer totalGetScore) {
        this.totalGetScore = totalGetScore;
    }

    public Integer getTotalExchangeScore() {
        return totalExchangeScore;
    }

    public void setTotalExchangeScore(Integer totalExchangeScore) {
        this.totalExchangeScore = totalExchangeScore;
    }
}
