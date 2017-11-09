package com.envolope.oss.service.data_statistic;

import com.envolope.oss.dao.*;
import com.envolope.oss.enums.ModuleType;
import com.envolope.oss.model.ReMissionModuleStatistics;
import com.envolope.oss.model.vo.data_statistics.ModelData;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jan on 2016/11/25.
 * 模块数据查询
 */
@Service
public class ModuleStatisticsService {

    @Autowired
    private ReMissionModuleStatisticsDAO reMissionModuleStatisticsDAO;
    @Autowired
    private MissionStatisticsTaskService missionStatisticsTaskService;


    /**
     * 获取某个模块再某一个时间范围内的数据
     * @param module
     * @param startDate
     * @param endDate
     * @return
     */
    public ModelData getData(Integer module,String startDate,String endDate) throws Exception {

        long now = System.currentTimeMillis();
        String today = ElBase.fmtDay(now);

        ModelData data = new ModelData();
        data.setStartDate(startDate);
        data.setEndDate(endDate);
        data.setModel(module);
        data.setModelTitle(ModuleType.getMissionName(module));

        //此表中只能获取今天之前的数据，所以不要想太多，先拿出来
        List<ReMissionModuleStatistics> dayBefore = reMissionModuleStatisticsDAO.getDateByTimeScope(module,startDate,endDate);
        ReMissionModuleStatistics nowDay;

        if (today.contains(endDate)){
            //要查询都最后一天包括今天，则需要单独查今天的数据
            nowDay = getDataToday(module,endDate);
            nowDay = handle(nowDay);
            dayBefore.add(nowDay);
        }
        data.setVos(dayBefore);

        return data;
    }

    /**
     * 处理空的属性
     * @param statistics
     * @return
     */
    public ReMissionModuleStatistics handle(ReMissionModuleStatistics statistics){

        //处理查询到的数据
        if (StringUtil.isEmpty(statistics.getDisPartAmount())){
            statistics.setDisPartAmount(0);
        }
        if (StringUtil.isEmpty(statistics.getTotalPartAmount())){
            statistics.setTotalPartAmount(0);
        }
        if (StringUtil.isEmpty(statistics.getDisCompAmount())){
            statistics.setDisCompAmount(0);
        }
        if (StringUtil.isEmpty(statistics.getTotalCompAmount())){
            statistics.setTotalCompAmount(0);
        }
        if (StringUtil.isEmpty(statistics.getShouldPayMoney())){
            statistics.setShouldPayMoney(new BigDecimal("0.00"));
        }
        if (StringUtil.isEmpty(statistics.getFinalPayMoney())){
            statistics.setFinalPayMoney(new BigDecimal("0.00"));
        }

        return statistics;
    }

    /**
     * 根据模块及时间范围查询
     * @param module
     * @param today     年月日
     * @return
     */
    public ReMissionModuleStatistics getDataToday(Integer module,String today) throws Exception {

        ReMissionModuleStatistics data = null ;

        if (ModuleType.great_mission.val.equals(module)){//高额任务

            data = missionStatisticsTaskService.getHighReward(today);

        }else if (ModuleType.attention_mission.val.equals(module)){//关注任务

            data = missionStatisticsTaskService.getAttention(today);

        }else if (ModuleType.demo_mission.val.equals(module)){//试玩任务

            data = missionStatisticsTaskService.getDemo(today);

        }else if (ModuleType.newcomer.val.equals(module)){//新手任务

            data = missionStatisticsTaskService.getNewComer(today);

        }else if (ModuleType.share_mission.val.equals(module)){//分享任务

            data = missionStatisticsTaskService.getShare(today);

        }else if (ModuleType.word_red.val.equals(module)){//口令红包

            data = missionStatisticsTaskService.getPassword(today);

        }else if (ModuleType.fix_red.val.equals(module)){//定时红包

            data = missionStatisticsTaskService.getFix(today);

        }else if (ModuleType.alliance_mission.val.equals(module)){//联盟任务

            data = missionStatisticsTaskService.getAlliance(today);

        }
        return data;
    }
}
