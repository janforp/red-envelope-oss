package com.envolope.oss.web.controller.page.console.auth.user_manager;

import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.vo.user.ChannelDataVo;
import com.envolope.oss.model.vo.user.DayDataVo;
import com.envolope.oss.model.vo.user.statistics.UserDataVo;
import com.envolope.oss.service.userManager.UserDataStatisticsService;
import com.envolope.oss.service.userManager.UserService;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.date.DateUtil;
import com.envolope.oss.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/page/console/auth/user", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    @Autowired
    private UserDataStatisticsService userDataStatisticsService;

    /**
     * 用户数据
     * @return
     */
    @RequestMapping(value = "/userData")
    public String userData(HttpServletRequest request){

        UserDataVo todayVo = userDataStatisticsService.selectTodayData(ElBase.fmtDay(System.currentTimeMillis()));
        UserDataVo yesterdayVo = userDataStatisticsService.selectDataByTime(DateUtil.getDayOfNDaysAgoFromNow(1), DateUtil.getDayOfNDaysAgoFromNow(1));
        UserDataVo weekVo = userDataStatisticsService.selectDataByTime(DateUtil.getDayOfNDaysAgoFromNow(7), DateUtil.getDayOfNDaysAgoFromNow(1));
        UserDataVo monthVo = userDataStatisticsService.selectDataByTime(DateUtil.getDayOfNDaysAgoFromNow(30), DateUtil.getDayOfNDaysAgoFromNow(1));

        //今日数据
        request.setAttribute("today", todayVo);
        request.setAttribute("yesterday", yesterdayVo);
        request.setAttribute("week", weekVo);
        request.setAttribute("month", monthVo);

        return "/console/user-management/user-data";
    }

    /**
     * 查询时间段数据
     * @param request
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "/dataByDateScope")
    @ResponseBody
    public String dataByDateScope(HttpServletRequest request,
                                  @RequestParam(value = "startDate")String startDate,
                                  @RequestParam(value = "endDate")String endDate){
        UserDataVo scopeVo = userDataStatisticsService.selectDataByTime(startDate, endDate);
        Map<String,Object> map = new HashMap<>(1);
        map.put("scope", scopeVo);
        return JsonUtil.buildData(map);
    }

    /**
     * 获取渠道数据,取到详情
     * @param request
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "/channelDataByDateScope")
    @ResponseBody
    public String channelDataByDateScope(HttpServletRequest request,
                                         @RequestParam(value = "startDate")String startDate,
                                         @RequestParam(value = "endDate")String endDate) {
        List<UserDataVo> vos;
        UserDataVo scopeVo;

        String today = ElBase.fmtDay(System.currentTimeMillis());
        if(today.equals(startDate)) {
            vos = userDataStatisticsService.selectTodayChannelData(today);
            scopeVo = userDataStatisticsService.selectTodayData(today);
        }else {
            vos = userDataStatisticsService.selectChannelDataByDateScope(startDate, endDate);
            scopeVo = userDataStatisticsService.selectDataByTime(startDate, endDate);
        }

        Map<String,Object> map = new HashMap<>(2);
        map.put("scope", scopeVo);
        map.put("vos", vos);
        return JsonUtil.buildData(map);
    }

    /**
     * 生成某一日用户数据
     *
     * @param request
     * @param date
     * @return
     */
    @RequestMapping(value = "/statistics")
    @ResponseBody
    public String statistics(HttpServletRequest request,
                             @RequestParam(value = "date") String date) {
        return userDataStatisticsService.statistics(date);
    }

}



