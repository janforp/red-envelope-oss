package com.envolope.oss.task.statistics;

import com.envolope.oss.service.userManager.UserDataStatisticsService;
import com.envolope.oss.util.el.ElBase;
import org.craigq.quartz.annotation.TaskCfg;
import org.craigq.quartz.task.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Summer on 16/29/29.
 * 统计昨日用户数据
 */
@Component
@TaskCfg(cron = "0 0 1 * * ?", concurrent = false, runInit = false, threadCount = 1)
@Transactional
public class UserDataStatisticsTask extends AbstractTask {

    @Autowired
    private UserDataStatisticsService userDataStatisticsService;

    @Override
    public void run() {
        String yesterday = ElBase.fmtDay(System.currentTimeMillis() - 24 * 3600 * 1000);
        userDataStatisticsService.statistics(yesterday);
    }


}
