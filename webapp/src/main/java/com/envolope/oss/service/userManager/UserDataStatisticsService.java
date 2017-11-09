package com.envolope.oss.service.userManager;

import com.envolope.oss.dao.*;
import com.envolope.oss.model.RePackageChannel;
import com.envolope.oss.model.ReUserDataStatistics;
import com.envolope.oss.model.vo.user.DayDataVo;
import com.envolope.oss.model.vo.user.statistics.UserDataVo;
import com.envolope.oss.util.JsonUtil;
import jxl.common.BaseUnit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/11/29.
 */
@Service
public class UserDataStatisticsService {

    @Autowired
    private ReScoreDetailDAO reScoreDetailDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReUserChannelDAO reUserChannelDAO;
    @Autowired
    private ChannelPackageService channelPackageService;
    @Autowired
    private ReUserDataStatisticsDAO reUserDataStatisticsDAO;


    /**
     * 查询今天的用户数据
     *
     * @param dayTime
     */
    public UserDataVo selectTodayData(String dayTime) {

        BigDecimal def = new BigDecimal("0.00");

        // 渠道包名当日新增
        Integer registerUsers = reUserChannelDAO.selectRegisterUserByDay(null, dayTime);
        if (registerUsers == null){
            registerUsers = 0;
        }

        // 渠道包名截止当日累计用户
        Integer totalUsers = reUserChannelDAO.selectTotalRegisterUserByTime(null, dayTime);
        if (totalUsers == null){
            totalUsers = 0;
        }

        // 当日注册用户获得的金币
        Integer todayUserGiveCoin = reScoreDetailDAO.selectTodayUserCoin(1, null, dayTime);
        if (todayUserGiveCoin == null) {
            todayUserGiveCoin = 0 ;
        }

        // 今日总发放金币
        Integer todayTotalGiveCoin = reScoreDetailDAO.selectTodayTotalCoin(1, null, dayTime);
        if (todayTotalGiveCoin == null) {
            todayTotalGiveCoin = 0 ;
        }

        // 当日注册用户消耗的金币
        Integer todayUserExpendCoin = reScoreDetailDAO.selectTodayUserCoin(0, null, dayTime);
        if (todayUserExpendCoin == null) {
            todayUserExpendCoin = 0 ;
        }

        // 今日总消耗金币
        Integer todayTotalExpendCoin = reScoreDetailDAO.selectTodayTotalCoin(0, null, dayTime);
        if (todayTotalExpendCoin == null) {
            todayTotalExpendCoin = 0 ;
        }

        // 新增用户发放金额(元)
        BigDecimal todayUserGiveMoney = reAccountDetailDAO.selectTodayUserMoney(1, null, dayTime);
        if(todayUserGiveMoney == null) {
            todayUserGiveMoney = def;
        }

        // 今日总发放金额(元)
        BigDecimal todayTotalGiveMoney = reAccountDetailDAO.selectTodayTotalMoney(1, null, dayTime);
        if(todayTotalGiveMoney == null) {
            todayTotalGiveMoney = def;
        }

        // 新增用户消耗金额(元)
        BigDecimal todayUserExpendMoney = reAccountDetailDAO.selectTodayUserMoney(0, null, dayTime);
        if(todayUserExpendMoney == null) {
            todayUserExpendMoney = def;
        }

        // 今日总消耗金额(元)
        BigDecimal todayTotalExpendMoney = reAccountDetailDAO.selectTodayTotalMoney(0, null, dayTime);
        if(todayTotalExpendMoney == null) {
            todayTotalExpendMoney = def;
        }

        UserDataVo today = new UserDataVo();
        today.setTodayUser(registerUsers);
        today.setTotalUser(totalUsers);
        today.setStartTime(dayTime);
        today.setEndTime(dayTime);
        today.setTodayUserGiveMoney(todayUserGiveMoney);
        today.setTodayUserExpendMoney(todayUserExpendMoney);
        today.setTodayUserGiveCoin(todayUserGiveCoin);
        today.setTodayUserExpendCoin(todayUserExpendCoin);
        today.setTodayTotalGiveMoney(todayTotalGiveMoney);
        today.setTodayTotalExpendMoney(todayTotalExpendMoney);
        today.setTodayTotalGiveCoin(todayTotalGiveCoin);
        today.setTodayTotalExpendCoin(todayTotalExpendCoin);

        return today;

    }


    /**
     * 查询今天 渠道信息
     *
     * @param dayTime
     */
    public List<UserDataVo> selectTodayChannelData(String dayTime) {

        BigDecimal def = new BigDecimal("0.00");

        // 查询所有渠道包名
        List<RePackageChannel> channelList = channelPackageService.getPackageChannelList();
        List<UserDataVo> list = new ArrayList<>(channelList.size());

        for (RePackageChannel rePackageChannel : channelList) {

            UserDataVo userData = new UserDataVo();
            Integer appId = rePackageChannel.getAppId();
            String packageName = rePackageChannel.getPackageName();
            String channelName = rePackageChannel.getChannelName();

            // 渠道包名当日新增
            Integer registerUsers = reUserChannelDAO.selectRegisterUserByDay(appId, dayTime);
            if (registerUsers == null){
                registerUsers = 0;
            }

            // 渠道包名截止当日累计用户
            Integer totalUsers = reUserChannelDAO.selectTotalRegisterUserByTime(appId, dayTime);
            if (totalUsers == null){
                totalUsers = 0;
            }

            // 当日注册用户获得的金币
            Integer todayUserGiveCoin = reScoreDetailDAO.selectTodayUserCoin(1, appId, dayTime);
            if (todayUserGiveCoin == null) {
                todayUserGiveCoin = 0 ;
            }

            // 今日总发放金币
            Integer todayTotalGiveCoin = reScoreDetailDAO.selectTodayTotalCoin(1, appId, dayTime);
            if (todayTotalGiveCoin == null) {
                todayTotalGiveCoin = 0 ;
            }

            // 当日注册用户消耗的金币
            Integer todayUserExpendCoin = reScoreDetailDAO.selectTodayUserCoin(0, appId, dayTime);
            if (todayUserExpendCoin == null) {
                todayUserExpendCoin = 0 ;
            }

            // 今日总消耗金币
            Integer todayTotalExpendCoin = reScoreDetailDAO.selectTodayTotalCoin(0, appId, dayTime);
            if (todayTotalExpendCoin == null) {
                todayTotalExpendCoin = 0 ;
            }

            // 新增用户发放金额(元)
            BigDecimal todayUserGiveMoney = reAccountDetailDAO.selectTodayUserMoney(1, appId, dayTime);
            if(todayUserGiveMoney == null) {
                todayUserGiveMoney = def;
            }

            // 今日总发放金额(元)
            BigDecimal todayTotalGiveMoney = reAccountDetailDAO.selectTodayTotalMoney(1, appId, dayTime);
            if(todayTotalGiveMoney == null) {
                todayTotalGiveMoney = def;
            }

            // 新增用户消耗金额(元)
            BigDecimal todayUserExpendMoney = reAccountDetailDAO.selectTodayUserMoney(0, appId, dayTime);
            if(todayUserExpendMoney == null) {
                todayUserExpendMoney = def;
            }

            // 今日总消耗金额(元)
            BigDecimal todayTotalExpendMoney = reAccountDetailDAO.selectTodayTotalMoney(0, appId, dayTime);
            if(todayTotalExpendMoney == null) {
                todayTotalExpendMoney = def;
            }

            userData.setPackageName(packageName);
            userData.setChannelName(channelName);
            userData.setDataTime(dayTime);
            userData.setTodayUser(registerUsers);
            userData.setTotalUser(totalUsers);
            userData.setTodayUserGiveCoin(todayUserGiveCoin);
            userData.setTodayTotalGiveCoin(todayTotalGiveCoin);
            userData.setTodayUserExpendCoin(todayUserExpendCoin);
            userData.setTodayTotalExpendCoin(todayTotalExpendCoin);
            userData.setTodayUserGiveMoney(todayUserGiveMoney);
            userData.setTodayTotalGiveMoney(todayTotalGiveMoney);
            userData.setTodayUserExpendMoney(todayUserExpendMoney);
            userData.setTodayTotalExpendMoney(todayTotalExpendMoney);

            list.add(userData);

        }

        return list;

    }


    /**
     * 查询某一时间段的用户数据
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public UserDataVo selectDataByTime(String startTime, String endTime) {
        ReUserDataStatistics data = reUserDataStatisticsDAO.selectDataByTime(startTime, endTime, null, null);
        UserDataVo day = new UserDataVo();
        if(data != null){
            BeanUtils.copyProperties(data, day);
        }
        day.setStartTime(startTime);
        day.setEndTime(endTime);

        if(!startTime.equals(endTime)) {
            ReUserDataStatistics last = reUserDataStatisticsDAO.selectDataByTime(endTime, endTime, null, null);
            if(last != null){
                day.setTotalUser(last.getTotalUser());
            }
        }
        return day;
    }

    /**
     * 查询时间段的渠道详情
     * @param startTime
     * @param endTime
     * @return
     */
    public List<UserDataVo> selectChannelDataByDateScope(String startTime, String endTime) {

        // 查询所有渠道包名
        List<RePackageChannel> channelList = channelPackageService.getPackageChannelList();
        List<UserDataVo> list = new ArrayList<>(channelList.size());

        for (RePackageChannel rePackageChannel : channelList) {

            UserDataVo userData = new UserDataVo();

            String packageName = rePackageChannel.getPackageName();
            String channelName = rePackageChannel.getChannelName();

            ReUserDataStatistics data = reUserDataStatisticsDAO.selectDataByTime(startTime, endTime, packageName, channelName);
            if(data != null){
                BeanUtils.copyProperties(data, userData);
            }

            if(!startTime.equals(endTime)) {
                ReUserDataStatistics last = reUserDataStatisticsDAO.selectDataByTime(endTime, endTime, packageName, channelName);
                if(last != null){
                    userData.setTotalUser(last.getTotalUser());
                }
            }
            userData.setPackageName(packageName);
            userData.setChannelName(channelName);

            list.add(userData);

        }


        return list;
    }

    /**
     * 统计某一天的用户数据
     *
     * @param dayTime
     */
    public String statistics(String dayTime) {

        List<ReUserDataStatistics> list = new ArrayList<>();

        BigDecimal def = new BigDecimal("0.00");

        // 查询所有渠道包名
        List<RePackageChannel> channelList = channelPackageService.getPackageChannelList();
        for (RePackageChannel rePackageChannel : channelList) {

            ReUserDataStatistics statistics = new ReUserDataStatistics();
            String packageName = rePackageChannel.getPackageName();
            String channelName = rePackageChannel.getChannelName();
            Integer appId = rePackageChannel.getAppId();

            // 渠道包名当日新增
            Integer registerUsers = reUserChannelDAO.selectRegisterUserByDay(appId, dayTime);
            if (registerUsers == null){
                registerUsers = 0;
            }

            // 渠道包名截止当日累计用户
            Integer totalUsers = reUserChannelDAO.selectTotalRegisterUserByTime(appId, dayTime);
            if (totalUsers == null){
                totalUsers = 0;
            }

            // 当日注册用户获得的金币
            Integer todayUserGiveCoin = reScoreDetailDAO.selectTodayUserCoin(1, appId, dayTime);
            if (todayUserGiveCoin == null) {
                todayUserGiveCoin = 0 ;
            }

            // 今日总发放金币
            Integer todayTotalGiveCoin = reScoreDetailDAO.selectTodayTotalCoin(1, appId, dayTime);
            if (todayTotalGiveCoin == null) {
                todayTotalGiveCoin = 0 ;
            }

            // 当日注册用户消耗的金币
            Integer todayUserExpendCoin = reScoreDetailDAO.selectTodayUserCoin(0, appId, dayTime);
            if (todayUserExpendCoin == null) {
                todayUserExpendCoin = 0 ;
            }

            // 今日总消耗金币
            Integer todayTotalExpendCoin = reScoreDetailDAO.selectTodayTotalCoin(0, appId, dayTime);
            if (todayTotalExpendCoin == null) {
                todayTotalExpendCoin = 0 ;
            }

            // 新增用户发放金额(元)
            BigDecimal todayUserGiveMoney = reAccountDetailDAO.selectTodayUserMoney(1, appId, dayTime);
            if(todayUserGiveMoney == null) {
                todayUserGiveMoney = def;
            }

            // 今日总发放金额(元)
            BigDecimal todayTotalGiveMoney = reAccountDetailDAO.selectTodayTotalMoney(1, appId, dayTime);
            if(todayTotalGiveMoney == null) {
                todayTotalGiveMoney = def;
            }

            // 新增用户消耗金额(元)
            BigDecimal todayUserExpendMoney = reAccountDetailDAO.selectTodayUserMoney(0, appId, dayTime);
            if(todayUserExpendMoney == null) {
                todayUserExpendMoney = def;
            }

            // 今日总消耗金额(元)
            BigDecimal todayTotalExpendMoney = reAccountDetailDAO.selectTodayTotalMoney(0, appId, dayTime);
            if(todayTotalExpendMoney == null) {
                todayTotalExpendMoney = def;
            }

            statistics.setPackageName(packageName);
            statistics.setChannelName(channelName);
            statistics.setDataTime(dayTime);
            statistics.setTodayUser(registerUsers);
            statistics.setTotalUser(totalUsers);
            statistics.setTodayUserGiveCoin(todayUserGiveCoin);
            statistics.setTodayTotalGiveCoin(todayTotalGiveCoin);
            statistics.setTodayUserExpendCoin(todayUserExpendCoin);
            statistics.setTodayTotalExpendCoin(todayTotalExpendCoin);
            statistics.setTodayUserGiveMoney(todayUserGiveMoney);
            statistics.setTodayTotalGiveMoney(todayTotalGiveMoney);
            statistics.setTodayUserExpendMoney(todayUserExpendMoney);
            statistics.setTodayTotalExpendMoney(todayTotalExpendMoney);

            list.add(statistics);

        }

        reUserDataStatisticsDAO.insertBatch(list);

        return JsonUtil.buildSuccess();

    }


}
