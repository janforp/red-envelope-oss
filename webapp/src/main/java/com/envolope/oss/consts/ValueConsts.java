package com.envolope.oss.consts;

/**
 * Created by Summer on 16/6/13.
 */
public class ValueConsts {

    private ValueConsts(){
    }

    /**
     * 分页大小
     */
    public static final Integer PAGE_SIZE = 20;

    /**
     * 聚合数据短信模版ID:话费充值短信模版
     */
    public static final String MSG_TPL_PHONE_RECHARGE = "16304";

    /**
     * 聚合数据短信模版ID:微信 支付宝 提现短信模版
     */
    public static final String MSG_TPL_WX_RECHARGE = "16319";

    /**
     * 随机生成 的最小额度 红包 单位:分
     */
    public static final Integer MIN_MONEY = 100;

    /**
     * 随机生成 的最大额度 红包 单位:分
     */
    public static final Integer MAX_MONEY = 110;

    /**
     * 一天时间点毫秒数
     */
    public static final Long  DAY_MILLISECOND = 24*60*60*1000L;
}
