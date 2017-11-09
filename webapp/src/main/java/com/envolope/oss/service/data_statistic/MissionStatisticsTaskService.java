package com.envolope.oss.service.data_statistic;

import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.*;
import com.envolope.oss.enums.MissionSubtype;
import com.envolope.oss.enums.MissionType;
import com.envolope.oss.enums.ModuleType;
import com.envolope.oss.model.ReMissionModuleStatistics;
import com.envolope.oss.task.statistics.MissionStatisticsTask;
import com.envolope.oss.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 2016/11/30.
 * 数据统计定时任务业务
 */
@Service
public class MissionStatisticsTaskService {

    @Autowired
    private ReRecommendTaskDAO reRecommendTaskDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReCodeExchangeDetailDAO reCodeExchangeDetailDAO;
    @Autowired
    private ReAppTaskDAO reAppTaskDAO;
    @Autowired
    private ReShareMissionDetailDAO reShareMissionDetailDAO;
    @Autowired
    private RePasswordRedDetailDAO rePasswordRedDetailDAO;
    @Autowired
    private ReReceiveDetailDAO reReceiveDetailDAO;
    @Autowired
    private ReNewcomerMissionDetailDAO reNewcomerMissionDetailDAO;
    @Autowired
    private MissionStatisticsTask missionStatisticsTask;


    /**
     * 定时任务统计各模块的每天的数据，获得数据并存入表中
     * @param oneDate 某一天的日期：2016-11-26
     * @return
     */
    public List<ReMissionModuleStatistics> getStatistics(String oneDate){

        List<ReMissionModuleStatistics> statistics = new ArrayList<>();
        //1.高额任务
        ReMissionModuleStatistics highReward = getHighReward(oneDate);
        statistics.add(highReward);
        //2.关注任务
        ReMissionModuleStatistics attention = getAttention(oneDate);
        statistics.add(attention);
        //3.试玩任务
        ReMissionModuleStatistics demo = getDemo(oneDate);
        statistics.add(demo);
        //4.新手任务
        ReMissionModuleStatistics newComer = getNewComer(oneDate);
        statistics.add(newComer);
        //5.转发任务
        ReMissionModuleStatistics share = getShare(oneDate);
        statistics.add(share);
        //6.口令红包
        ReMissionModuleStatistics password = getPassword(oneDate);
        statistics.add(password);
        //7.定时红包
        ReMissionModuleStatistics fix = getFix(oneDate);
        statistics.add(fix);
        //8.联盟任务
        ReMissionModuleStatistics alliance = getAlliance(oneDate);
        statistics.add(alliance);

        return statistics;
    }


    /**
     * 找到高额任务某一天的数据
     * @param oneDate
     * @return
     */
    public ReMissionModuleStatistics getHighReward(String oneDate){

        ReMissionModuleStatistics highReward = new ReMissionModuleStatistics();

        highReward.setOneDate(oneDate);
        highReward.setModule(ModuleType.great_mission.val);
        highReward.setModuleDesc("高额任务");
        // 参与任务总用户数，每个用户只统计一次
        Integer disPartAmount = reRecommendTaskDAO.getDisPartAmount(oneDate);
        highReward.setDisPartAmount(disPartAmount);
        // 参与任务总次数，单个用户可能统计多次
        Integer totalPartAmount = reRecommendTaskDAO.getTotalPartAmount(oneDate);
        highReward.setTotalPartAmount(totalPartAmount);
        // 完成任务总用户数，每个用户只统计一次
        Integer disCompAmount = reRecommendTaskDAO.getDisCompAmount(oneDate);
        highReward.setDisCompAmount(disCompAmount);
        // 完成任务总次数，单个用户可能统计多次
        Integer totalCompAmount = reRecommendTaskDAO.getTotalCompAmount(oneDate);
        highReward.setTotalCompAmount(totalCompAmount);
        // 应发奖励，元
        BigDecimal shouldPayMoney = reAccountDetailDAO.getShouldPayMoney(MissionType.great_mission.val, MissionSubtype.other.val,oneDate);
        highReward.setShouldPayMoney(shouldPayMoney);
        // 实发奖励，元:高额任务此项与应发相同
        BigDecimal finalPayMoney = reAccountDetailDAO.getFinalPayMoney(MissionType.great_mission.val,MissionSubtype.other.val,oneDate);
        highReward.setFinalPayMoney(finalPayMoney);

        return highReward;
    }

    /**
     * 找到关注任务某一天的数据
     * @param oneDate
     * @return
     */
    public ReMissionModuleStatistics getAttention(String oneDate){

        ReMissionModuleStatistics exchange = new ReMissionModuleStatistics();

        exchange.setOneDate(oneDate);
        exchange.setModule(ModuleType.attention_mission.val);
        exchange.setModuleDesc("关注任务");

        // 参与任务总用户数，每个用户只统计一次
        Integer disPartAmount = reCodeExchangeDetailDAO.getDisPartAmount(oneDate);
        exchange.setDisPartAmount(disPartAmount);
        // 参与任务总次数，单个用户可能统计多次
        Integer totalPartAmount = reCodeExchangeDetailDAO.getTotalPartAmount(oneDate);
        exchange.setTotalPartAmount(totalPartAmount);
        // 完成任务总用户数，每个用户只统计一次
        Integer disCompAmount = reCodeExchangeDetailDAO.getDisCompAmount(oneDate);
        exchange.setDisCompAmount(disCompAmount);
        // 完成任务总次数，单个用户可能统计多次
        Integer totalCompAmount = reCodeExchangeDetailDAO.getTotalCompAmount(oneDate);
        exchange.setTotalCompAmount(totalCompAmount);
        // 应发奖励，元
        BigDecimal shouldPayMoney = reAccountDetailDAO.getShouldPayMoney(MissionType.attention_mission.val,MissionSubtype.other.val,oneDate);
        exchange.setShouldPayMoney(shouldPayMoney);
        // 实发奖励，元:高额任务此项与应发相同
        exchange.setFinalPayMoney(shouldPayMoney);

        return exchange;
    }
    /**
     * 找到试玩任务某一天的数据
     * @param oneDate
     * @return
     */
    public ReMissionModuleStatistics getDemo(String oneDate){

        ReMissionModuleStatistics demo = new ReMissionModuleStatistics();

        demo.setOneDate(oneDate);
        demo.setModule(ModuleType.demo_mission.val);
        demo.setModuleDesc("试玩任务");
        // 参与任务总用户数，每个用户只统计一次
        Integer disPartAmount = reAppTaskDAO.getDisPartAmount(oneDate);
        demo.setDisPartAmount(disPartAmount);
        // 参与任务总次数，单个用户可能统计多次
        Integer totalPartAmount = reAppTaskDAO.getTotalPartAmount(oneDate);
        demo.setTotalPartAmount(totalPartAmount);
        // 完成任务总用户数，每个用户只统计一次
        Integer disCompAmount = reAppTaskDAO.getDisCompAmount(oneDate);
        demo.setDisCompAmount(disCompAmount);
        // 完成任务总次数，单个用户可能统计多次
        Integer totalCompAmount = reAppTaskDAO.getTotalCompAmount(oneDate);
        demo.setTotalCompAmount(totalCompAmount);
        // 应发奖励，元
        BigDecimal shouldPayMoney = reAccountDetailDAO.getShouldPayMoney(MissionType.demo_mission.val,MissionSubtype.other.val,oneDate);
        demo.setShouldPayMoney(shouldPayMoney);
        // 实发奖励，元:高额任务此项与应发相同
        demo.setFinalPayMoney(shouldPayMoney);

        return demo;
    }
    /**
     * 找到新手任务某一天的数据
     * @param oneDate
     * @return
     */
    public ReMissionModuleStatistics getNewComer(String oneDate){

        ReMissionModuleStatistics newComer = new ReMissionModuleStatistics();

        newComer.setOneDate(oneDate);
        newComer.setModule(ModuleType.newcomer.val);
        newComer.setModuleDesc("新手任务");

        // 完成任务总用户数，每个用户只统计一次
        Integer disCompAmount = reNewcomerMissionDetailDAO.getDisCompAmount(oneDate);
        newComer.setDisCompAmount(disCompAmount);
        // 完成任务总次数，单个用户可能统计多次
        Integer totalCompAmount = reNewcomerMissionDetailDAO.getTotalCompAmount(oneDate);
        newComer.setTotalCompAmount(totalCompAmount);

        // 实发奖励，元:高额任务此项与应发相同
        BigDecimal finalPayMoney = reNewcomerMissionDetailDAO.getFinalPayMoney(oneDate);
        newComer.setFinalPayMoney(finalPayMoney);

        return newComer;
    }

    /**
     * 找到分享任务某一天的数据
     * @param oneDate
     * @return
     */
    public ReMissionModuleStatistics getShare(String oneDate){

        ReMissionModuleStatistics share = new ReMissionModuleStatistics();

        share.setOneDate(oneDate);
        share.setModule(ModuleType.share_mission.val);
        share.setModuleDesc("分享任务");

        // 参与任务总用户数，不好统计
        // 参与任务总次数，不好统计
        // 完成任务总用户数，每个用户只统计一次
        Integer disCompAmount = reShareMissionDetailDAO.getDisCompAmount(oneDate);
        share.setDisCompAmount(disCompAmount);
        // 完成任务总次数，单个用户可能统计多次
        Integer totalCompAmount = reShareMissionDetailDAO.getTotalCompAmount(oneDate);
        share.setTotalCompAmount(totalCompAmount);
        // 应发奖励，元
        BigDecimal shouldPayMoney = reAccountDetailDAO.getShouldPayMoney(MissionType.share_mission.val,MissionSubtype.other.val,oneDate);
        share.setShouldPayMoney(shouldPayMoney);
        // 实发奖励，元:高额任务此项与应发相同
        share.setFinalPayMoney(shouldPayMoney);

        return share;
    }

    /**
     * 找到口令红包某一天的数据
     * @param oneDate
     * @return
     */
    public ReMissionModuleStatistics getPassword(String oneDate){

        ReMissionModuleStatistics password = new ReMissionModuleStatistics();

        password.setOneDate(oneDate);
        password.setModule(ModuleType.word_red.val);
        password.setModuleDesc("口令红包");

        //参与单个用户总数
        Integer disPartAmount = rePasswordRedDetailDAO.getDisPartAmount(oneDate);
        password.setDisPartAmount(disPartAmount);
        //参与总次数
        Integer totalPartAmount = rePasswordRedDetailDAO.getTotalPartAmount(oneDate);
        password.setTotalPartAmount(totalPartAmount);
        // 完成任务总用户数，每个用户只统计一次
        Integer disCompAmount = rePasswordRedDetailDAO.getDisCompAmount(oneDate);
        password.setDisCompAmount(disCompAmount);
        //完成任务总次数，单个用户可能统计多次
        Integer totalCompAmount = rePasswordRedDetailDAO.getTotalCompAmount(oneDate);
        password.setTotalCompAmount(totalCompAmount);
        // 应发奖励，元
        BigDecimal shouldPayMoney = reAccountDetailDAO.getShouldPayMoney(MissionType.other_mission.val,MissionSubtype.word_red.val,oneDate);
        password.setShouldPayMoney(shouldPayMoney);
        //实发奖励，元:
        BigDecimal finalPayMoney = reAccountDetailDAO.getShouldPayMoney(MissionType.other_mission.val,MissionSubtype.word_red.val,oneDate);
        password.setFinalPayMoney(finalPayMoney);

        return password;
    }

    /**
     * 定时红包
     * @param oneDate
     * @return
     */
    public ReMissionModuleStatistics getFix(String  oneDate){

        ReMissionModuleStatistics fix = new ReMissionModuleStatistics();
        fix.setOneDate(oneDate);
        fix.setModule(ModuleType.fix_red.val);
        fix.setModuleDesc("定时红包");

        //参与单个用户总数
        Integer disPartAmount = reReceiveDetailDAO.getDisPartAmount(oneDate);
        fix.setDisPartAmount(disPartAmount);
        //参与总次数
        Integer totalPartAmount = reReceiveDetailDAO.getTotalPartAmount(oneDate);
        fix.setTotalPartAmount(totalPartAmount);
        // 完成任务总用户数，每个用户只统计一次
        Integer disCompAmount = reReceiveDetailDAO.getDisCompAmount(oneDate);
        fix.setDisCompAmount(disCompAmount);
        //完成任务总次数，单个用户可能统计多次
        Integer totalCompAmount = reReceiveDetailDAO.getTotalCompAmount(oneDate);
        fix.setTotalCompAmount(totalCompAmount);
        // 应发奖励，元
        BigDecimal shouldPayMoney = reAccountDetailDAO.getShouldPayMoney(MissionType.other_mission.val,MissionSubtype.fix_red.val,oneDate);
        fix.setShouldPayMoney(shouldPayMoney);
        //实发奖励，元:
        BigDecimal finalPayMoney = reAccountDetailDAO.getFinalPayMoney(MissionType.other_mission.val,MissionSubtype.fix_red.val,oneDate);
        fix.setFinalPayMoney(finalPayMoney);

        return fix;
    }
    /**
     * 联盟任务
     * @param oneDate
     * @return
     */
    public ReMissionModuleStatistics getAlliance(String oneDate){

        ReMissionModuleStatistics alliance = new ReMissionModuleStatistics();
        alliance.setOneDate(oneDate);
        alliance.setModule(ModuleType.alliance_mission.val);
        alliance.setModuleDesc("联盟任务");

        //参与单个用户总数，不好统计
        //参与总次数，不好统计
        // 完成任务总用户数，每个用户只统计一次，不好统计
        //完成任务总次数，单个用户可能统计多次，不好统计
        //应发奖励，元
        BigDecimal shouldPayMoney = reAccountDetailDAO.getShouldPayMoney(MissionType.alliance_mission.val,MissionSubtype.other.val,oneDate);
        alliance.setShouldPayMoney(shouldPayMoney);
        //实发奖励，元:
        alliance.setFinalPayMoney(shouldPayMoney);

        return alliance;
    }

    /**
     * 手动把之前定时任务没有正确统计的数据统计一下
     * @param today
     */
    public void executeYesterday(HttpServletRequest request,String today){

        List<ReMissionModuleStatistics> yesterdayStat = getStatistics(today);

        missionStatisticsTask.insertList(yesterdayStat);

    }
}
