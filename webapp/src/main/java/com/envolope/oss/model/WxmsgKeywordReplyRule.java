package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-04
 */
public class WxmsgKeywordReplyRule implements java.io.Serializable {

    // Fields

    // 规则ID
    private Integer ruleId;
    // 规则名称，非空，长度64
    private String ruleName;
    // 客户id
    private Integer customerId;
    // 昵多个关键词，一个关键词使用“#关键词#”包装，多个关键词之间使用“_”分割；格式：#关键词1#_#关键词2#_#关键词3#，非空，长度512
    private String keywords;
    // 匹配模式；0：模糊匹配（所有的关键词中，任何一个包含了用户的关键词就触发）；1：精确匹配（所有关键词中，任何一个和用户输入的相同才触发）
    private Integer matchMode;
    // 回复的消息类型：目前支持文字/图文消息；text:文本消息;news:图文消息;
    private String msgType;
    // 回复的消息的JSON数据，非空，长度1024
    private String msgBody;
    // 创建时间
    private Long createTime;

    // Constructors

    /**
     * default constructor
     */
    public WxmsgKeywordReplyRule() {
    }

    /**
     * full constructor
     */
    public WxmsgKeywordReplyRule(String ruleName, Integer customerId, String keywords, Integer matchMode, String msgType, String msgBody, Long createTime) {
        this.ruleName = ruleName;
        this.customerId = customerId;
        this.keywords = keywords;
        this.matchMode = matchMode;
        this.msgType = msgType;
        this.msgBody = msgBody;
        this.createTime = createTime;
    }

    // Property accessors

    /**
     * 规则ID
     */
    public Integer getRuleId() {
        return this.ruleId;
    }

    /**
     * 规则ID
     */
    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * 规则名称，非空，长度64
     */
    public String getRuleName() {
        return this.ruleName;
    }

    /**
     * 规则名称，非空，长度64
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    /**
     * 客户id
     */
    public Integer getCustomerId() {
        return this.customerId;
    }

    /**
     * 客户id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 昵多个关键词，一个关键词使用“#关键词#”包装，多个关键词之间使用“_”分割；格式：#关键词1#_#关键词2#_#关键词3#，非空，长度512
     */
    public String getKeywords() {
        return this.keywords;
    }

    /**
     * 昵多个关键词，一个关键词使用“#关键词#”包装，多个关键词之间使用“_”分割；格式：#关键词1#_#关键词2#_#关键词3#，非空，长度512
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 匹配模式；0：模糊匹配（所有的关键词中，任何一个包含了用户的关键词就触发）；1：精确匹配（所有关键词中，任何一个和用户输入的相同才触发）
     */
    public Integer getMatchMode() {
        return this.matchMode;
    }

    /**
     * 匹配模式；0：模糊匹配（所有的关键词中，任何一个包含了用户的关键词就触发）；1：精确匹配（所有关键词中，任何一个和用户输入的相同才触发）
     */
    public void setMatchMode(Integer matchMode) {
        this.matchMode = matchMode;
    }

    /**
     * 回复的消息类型：目前支持文字/图文消息；text:文本消息;news:图文消息;
     */
    public String getMsgType() {
        return this.msgType;
    }

    /**
     * 回复的消息类型：目前支持文字/图文消息；text:文本消息;news:图文消息;
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * 回复的消息的JSON数据，非空，长度1024
     */
    public String getMsgBody() {
        return this.msgBody;
    }

    /**
     * 回复的消息的JSON数据，非空，长度1024
     */
    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
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

}