package com.envolope.oss.task.statistics;

import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReMissionModuleStatisticsDAO;
import com.envolope.oss.model.ReMissionModuleStatistics;
import com.envolope.oss.service.data_statistic.MissionStatisticsTaskService;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.craigq.quartz.annotation.TaskCfg;
import org.craigq.quartz.task.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Jan on 2016/11/28.
 * 数据统计定时任务
 */
@Component
@TaskCfg(cron = "0 0 1 * * ?",concurrent = false,runInit = false,threadCount = 1)
@Transactional
public class MissionStatisticsTask extends AbstractTask{

    @Autowired
    private ReMissionModuleStatisticsDAO reMissionModuleStatisticsDAO;
    @Autowired
    private MissionStatisticsTaskService missionStatisticsTaskService;

    @Override
    public void run() {

        List<ReMissionModuleStatistics> statistics = getStatistics();

        insertList(statistics);
    }

    /**
     * 每天的凌晨1点统计前一天的数据
     * @return
     */
    public List<ReMissionModuleStatistics> getStatistics(){

        long now = System.currentTimeMillis();
        long yesterday = now- ValueConsts.DAY_MILLISECOND;
        String yesterdayDate = ElBase.fmtDay(yesterday);

        return missionStatisticsTaskService.getStatistics(yesterdayDate);
    }

    public void insertList(List<ReMissionModuleStatistics> statistics){

        for (ReMissionModuleStatistics statistic : statistics){

            String oneDate = statistic.getOneDate();
            Integer module = statistic.getModule();

            if (!StringUtil.isEmpty(oneDate) && !StringUtil.isEmpty(module)){

                ReMissionModuleStatistics alreadyStatistic = reMissionModuleStatisticsDAO.selectByPrimaryKey(oneDate,module);

                if (alreadyStatistic == null) {

                    reMissionModuleStatisticsDAO.insert(statistic);
                }
            }
        }
    }
}