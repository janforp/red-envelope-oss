package com.envolope.oss.model.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by wuqiang on 16-3-28.
 *
 * @author wuqiang
 */
public class KeywordAutoReplyVo implements Serializable {
    // 规则ID
    @JSONField(name = "r")
    private Integer ruleId;
    // 客户号
    @JSONField(name = "a")
    private Integer customerId;
    // 匹配模式；0：模糊匹配（所有的关键词中，任何一个包含了用户的关键词就触发）；1：精确匹配（所有关键词中，任何一个和用户输入的相同才触发）
    @JSONField(name = "m")
    private Integer matchMode;
    // 这个字段不缓存到redis，是记录查询时，通过哪个关键词查询到的
    private String keyword;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getMatchMode() {
        return matchMode;
    }

    public void setMatchMode(Integer matchMode) {
        this.matchMode = matchMode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "KeywordAutoReplyVo{" +
                "ruleId=" + ruleId +
                ", customerId=" + customerId +
                ", matchMode=" + matchMode +
                ", keyword=" + keyword +
                '}';
    }
}
