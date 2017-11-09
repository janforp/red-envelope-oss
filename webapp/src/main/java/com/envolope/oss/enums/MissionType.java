package com.envolope.oss.enums;

/**
 * Created by Summer on 16/9/19.
 */
public enum MissionType {

    // 其他
    other_mission(0),
    // 试玩任务
    demo_mission(1),
    // 分享任务
    share_mission(2),
    // 关注任务
    attention_mission(3),
    // 联盟任务
    alliance_mission(4),
    // 高额任务
    great_mission(5),
    // 深度任务
    deepness_mission(6);

    // 值
    public final Integer val;

    private MissionType(Integer val) {
        this.val = val;
    }


}
