package com.envolope.oss.enums;

/**
 * Created by Jan on 2016/11/28.
 * 模块统计用
 */
public enum ModuleType {

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
    deepness_mission(6),
    // 定时红包
    fix_red(7),
    // 签到
    sign(8),
    // 口令红包
    word_red(9),
    // 邀请好友
    invite_friend(10),
    // 新手任务
    newcomer(11),
    // 注册任务
    register(12),
    // 佣金
    commission(13);

    // 值
    public final Integer val;

    private ModuleType(Integer val) {
        this.val = val;
    }

    public static String getMissionName(Integer module){

        if (demo_mission.val.equals(module)){
            return "试玩任务";
        }else if (share_mission.val.equals(module)){
            return "分享任务";
        }else if (demo_mission.val.equals(module)){
            return "试玩任务";
        }else if (attention_mission.val.equals(module)){
            return "关注任务";
        }else if (alliance_mission.val.equals(module)){
            return "联盟任务";
        }else if (great_mission.val.equals(module)){
            return "高额任务";
        }else if (deepness_mission.val.equals(module)){
            return "深度任务";
        }else if (fix_red.val.equals(module)){
            return "定时红包";
        }else if (sign.val.equals(module)){
            return "签到";
        }else if (word_red.val.equals(module)){
            return "口令红包";
        }else if (invite_friend.val.equals(module)){
            return "邀请好友";
        }else if (newcomer.val.equals(module)){
            return "新手任务";
        }else if (register.val.equals(module)){
            return "注册任务";
        }else if (commission.val.equals(module)){
            return "佣金";
        }else {
            return "其他";
        }

    }
}
