package com.envolope.oss.weixin;

import com.wujie.common.sdk.weixin.official_account.domain.menu.SelfMenuInfoButtonNewsInfoDomain;
import com.wujie.common.sdk.weixin.official_account.internal.mapping.ApiField;

import java.util.List;

/**
 * Created by Summer on 16/8/9.
 */
public class SelfMenuInfoButtonDomain {
    private String name;
    private String type;
    private String url;
    private String value;
    private String key;
    private String media_id;
    @ApiField(
            owner = "news_info",
            value = "list"
    )
    private List<SelfMenuInfoButtonNewsInfoDomain> newsInfo;
    @ApiField(
            owner = "sub_button",
            value = "list"
    )
    private List<SelfMenuInfoButtonDomain> subButton;

    public SelfMenuInfoButtonDomain() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<SelfMenuInfoButtonNewsInfoDomain> getNewsInfo() {
        return this.newsInfo;
    }

    public void setNewsInfo(List<SelfMenuInfoButtonNewsInfoDomain> newsInfo) {
        this.newsInfo = newsInfo;
    }

    public List<SelfMenuInfoButtonDomain> getSubButton() {
        return this.subButton;
    }

    public void setSubButton(List<SelfMenuInfoButtonDomain> subButton) {
        this.subButton = subButton;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
}
