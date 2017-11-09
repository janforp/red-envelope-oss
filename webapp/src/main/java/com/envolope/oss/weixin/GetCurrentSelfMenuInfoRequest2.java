package com.envolope.oss.weixin;

import com.wujie.common.sdk.support.enums.HttpMethod;
import com.wujie.common.sdk.weixin.official_account.WeixinRequest;

/**
 * Created by Summer on 16/8/9.
 */
public class GetCurrentSelfMenuInfoRequest2 extends WeixinRequest<GetCurrentSelfMenuInfoResponse2> {
    public GetCurrentSelfMenuInfoRequest2() {
    }

    public String getApiSubPath() {
        return "/cgi-bin/get_current_selfmenu_info";
    }

    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }
}
