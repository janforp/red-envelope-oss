package com.envolope.oss.enums;

/**
 * Created by Summer on 16/9/19.
 */
public enum MissionSubtype {

    // 其他
    other(0),
    // 定时红包
    fix_red(1),
    // 签到
    sign(2),
    // 口令红包
    word_red(3),
    // 邀请好友
    invite_friend(4),
    // 新手任务
    newcomer(5),
    // 注册任务
    register(6),
    // 提现到支付宝
    zhifubao(7),
    // 提现到微信
    weixin(8),
    // 话费充值
    huafei(9),
    // 佣金
    commission(10);

    // 值
    public final Integer val;

    private MissionSubtype(Integer val) {
        this.val = val;
    }


}
