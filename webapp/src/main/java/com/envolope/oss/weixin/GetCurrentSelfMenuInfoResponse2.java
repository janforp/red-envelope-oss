package com.envolope.oss.weixin;

import com.wujie.common.sdk.weixin.official_account.WeixinResponse;
import com.wujie.common.sdk.weixin.official_account.internal.mapping.ApiField;

/**
 * Created by Summer on 16/8/9.
 */
public class GetCurrentSelfMenuInfoResponse2 extends WeixinResponse {

    @ApiField("is_menu_open")
    private Integer isMenuOpen;
    @ApiField("selfmenu_info")
    private SelfMenuInfoDomain selfMenuInfo;

    public GetCurrentSelfMenuInfoResponse2() {
    }

    public Integer getIsMenuOpen() {
        return this.isMenuOpen;
    }

    public void setIsMenuOpen(Integer isMenuOpen) {
        this.isMenuOpen = isMenuOpen;
    }

    public SelfMenuInfoDomain getSelfMenuInfo() {
        return this.selfMenuInfo;
    }

    public void setSelfMenuInfo(SelfMenuInfoDomain selfMenuInfo) {
        this.selfMenuInfo = selfMenuInfo;
    }

}
