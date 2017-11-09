package com.envolope.oss.service.userManager;

import com.envolope.oss.dao.ReAccountDetailDAO;
import com.envolope.oss.dao.ReScoreDetailDAO;
import com.envolope.oss.dao.ReUserChannelDAO;
import com.envolope.oss.dao.ReUserDAO;
import com.envolope.oss.model.RePackageChannel;
import com.envolope.oss.model.ReUserChannel;
import com.envolope.oss.model.vo.user.ChannelDataVo;
import com.envolope.oss.model.vo.user.DayDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/9/29.
 * 用户管理
 */
@Service
public class UserService {

    @Autowired
    private ReUserDAO reUserDAO ;
    @Autowired
    private ReScoreDetailDAO reScoreDetailDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReUserChannelDAO reUserChannelDAO;
    @Autowired
    private ChannelPackageService channelPackageService;

    /**
     * 获取某一日数据:
     * 今日新增用户:562
     * 总用户:8000
     * @return
     */
    public DayDataVo getDataOfOneDay(String date){
        //当日注册用户
        Integer registerUsers = reUserDAO.getTotalRegisterUserOfOneDay(date);
        if (registerUsers == null){
            registerUsers = 0;
        }
        //目前为止的总用户
        Integer totalUsers = reUserDAO.getTotalUsers(date);
        if (totalUsers == null){
            totalUsers = 0 ;
        }
        //当日注册用户获得的金币
        Integer newUserGetScore = reScoreDetailDAO.getTotalScoreByDateAndType(1,date);
        if (newUserGetScore == null) {
            newUserGetScore = 0 ;
        }
        Integer newUserConsumeScore = reScoreDetailDAO.getTotalScoreByDateAndType(0,date);
        if (newUserConsumeScore == null){
            newUserConsumeScore = 0 ;
        }
        BigDecimal newUserGetMoney = reAccountDetailDAO.getTotalMoneyByDateAndType(1,date);
        if (newUserGetMoney == null){
            newUserGetMoney = new BigDecimal("0.00") ;
        }
        BigDecimal newUserWithdrawMoney = reAccountDetailDAO.getTotalMoneyByDateAndType(0,date);
        if (newUserWithdrawMoney == null){
            newUserWithdrawMoney = new BigDecimal("0.00") ;
        }
        BigDecimal totalUserGetMoney = reAccountDetailDAO.getTotalGetMoneyByType(1,date);
        if (totalUserGetMoney == null){
            totalUserGetMoney = new BigDecimal("0.00") ;
        }
        BigDecimal totalUserWithdrawMoney = reAccountDetailDAO.getTotalGetMoneyByType(0,date);
        if (totalUserWithdrawMoney == null){
            totalUserWithdrawMoney = new BigDecimal("0.00") ;
        }
        Integer totalUserGetScore = reScoreDetailDAO.getTotalGetScoreByType(1,date);
        if (totalUserGetScore == null){
            totalUserGetScore = 0 ;
        }
        Integer totalUserConsumeScore = reScoreDetailDAO.getTotalGetScoreByType(0,date);
        if (totalUserConsumeScore == null){
            totalUserConsumeScore = 0 ;
        }
        DayDataVo vo = new DayDataVo();
        vo.setRegisterUsers(registerUsers);
        vo.setTotalUsers(totalUsers);
        vo.setStartTime(date);
        vo.setEndTime(date);
        vo.setNewUserGetMoney(newUserGetMoney);
        vo.setNewUserWithdrawMoney(newUserWithdrawMoney);
        vo.setNewUserGetScore(newUserGetScore);
        vo.setNewUserConsumeScore(newUserConsumeScore);
        vo.setTotalUserGetMoney(totalUserGetMoney);
        vo.setTotalUserWithdrawMoney(totalUserWithdrawMoney);
        vo.setTotalUserGetScore(totalUserGetScore);
        vo.setTotalUserConsumeScore(totalUserConsumeScore);

        return vo;
    }

    /**
     * 获取一段时间范围的数据
     * @param startDate 2016-08-19
     * @param endDate   2016-08-30
     * @return
     */
    public DayDataVo getDataOfTimeScope(String startDate,String endDate){
        //新注册用户
        Integer registerUsers = reUserDAO.getTotalRegisterUserOfOneTimeScope(startDate,endDate);
        if (registerUsers == null){
            registerUsers = 0;
        }
        //截止日总用户
        Integer totalUsers = reUserDAO.getTotalUsers(endDate);
        if (totalUsers == null){
            totalUsers = 0 ;
        }
        //新注册用户获得的金币
        Integer newUserGetScore = reScoreDetailDAO.getTotalScoreByNewUsersAndTimeScopeAndType(1,startDate,endDate);
        if (newUserGetScore == null) {
            newUserGetScore = 0 ;
        }
        //新注册用户消耗的金币
        Integer newUserConsumeScore = reScoreDetailDAO.getTotalScoreByNewUsersAndTimeScopeAndType(0,startDate,endDate);
        if (newUserConsumeScore == null){
            newUserConsumeScore = 0 ;
        }
        //新注册用户获得的钱
        BigDecimal newUserGetMoney = reAccountDetailDAO.getTotalMoneyByNewUserAndTimeScopeAndType(1,startDate,endDate);
        if (newUserGetMoney == null){
            newUserGetMoney = new BigDecimal("0.00") ;
        }
        //新注册用户提现的钱
        BigDecimal newUserWithdrawMoney = reAccountDetailDAO.getTotalMoneyByNewUserAndTimeScopeAndType(0,startDate,endDate);
        if (newUserWithdrawMoney == null){
            newUserWithdrawMoney = new BigDecimal("0.00") ;
        }
        //时间段内,用户总共获得的钱
        BigDecimal totalUserGetMoney = reAccountDetailDAO.getTotalMoneyByAllUserAndTimeScopeAndType(1,startDate,endDate);
        if (totalUserGetMoney == null){
            totalUserGetMoney = new BigDecimal("0.00") ;
        }
        //时间段内,用户总共提现的钱
        BigDecimal totalUserWithdrawMoney = reAccountDetailDAO.getTotalMoneyByAllUserAndTimeScopeAndType(0,startDate,endDate);
        if (totalUserWithdrawMoney == null){
            totalUserWithdrawMoney = new BigDecimal("0.00") ;
        }
        //时间段内,用户总共获得的金币
        Integer totalUserGetScore = reScoreDetailDAO.getTotalScoreByAllUserAndTimeScopeAndType(1,startDate,endDate);
        if (totalUserGetScore == null){
            totalUserGetScore = 0 ;
        }
        //时间段内,用户总共提现的金币
        Integer totalUserConsumeScore = reScoreDetailDAO.getTotalScoreByAllUserAndTimeScopeAndType(0,startDate,endDate);
        if (totalUserConsumeScore == null){
            totalUserConsumeScore = 0 ;
        }

        DayDataVo vo = new DayDataVo();
        vo.setRegisterUsers(registerUsers);
        vo.setTotalUsers(totalUsers);
        vo.setStartTime(startDate);
        vo.setEndTime(endDate);
        vo.setNewUserGetMoney(newUserGetMoney);
        vo.setNewUserWithdrawMoney(newUserWithdrawMoney);
        vo.setNewUserGetScore(newUserGetScore);
        vo.setNewUserConsumeScore(newUserConsumeScore);
        vo.setTotalUserGetMoney(totalUserGetMoney);
        vo.setTotalUserWithdrawMoney(totalUserWithdrawMoney);
        vo.setTotalUserGetScore(totalUserGetScore);
        vo.setTotalUserConsumeScore(totalUserConsumeScore);

        return vo;
    }

    /**
     * 渠道数据
     * @param startDate
     * @param endDate
     * @return
     */
    public List<ChannelDataVo> getChannelDataByDateScope(String startDate,String endDate){

        startDate = startDate+" 00:00:00";

        endDate =endDate+" 23:59:59";

        List<RePackageChannel> packageChannels = channelPackageService.getPackageChannelList();

        List<ChannelDataVo> vos = new ArrayList<>();

        for (RePackageChannel packageChannel : packageChannels){

            ChannelDataVo vo = new ChannelDataVo();

            String packageName = packageChannel.getPackageName();
            String channelName = packageChannel.getChannelName();

            vo.setChannelName(channelName);
            vo.setPackageName(packageName);
            //此包名+渠道名 : 在此时间段内新增的用户
            Integer newUsersNum = reUserChannelDAO.getNewUserNumByChannelPackageAndTime(channelName,packageName,startDate,endDate);

            if (newUsersNum == null){
                newUsersNum = 0 ;
            }
            vo.setNewUserNum(newUsersNum);

            //此包名+渠道名 : 的总用户
            Integer allUserNum = reUserChannelDAO.getAllUserNumByChannelPackage(channelName,packageName);
            if (allUserNum == null) {
                allUserNum = 0;
            }
            vo.setAllUserNum(allUserNum);

            //获取此 渠道+包名 对应的所有user的userId
            List<ReUserChannel> userChannels = reUserChannelDAO.getAllUserIdByChannelPackage(channelName,packageName);
            //若此渠道+包名:没有注册用户,则直接返回属性为0的对象
            if (userChannels == null || userChannels.size() == 0){

                vo.setAllUserGetScore(0);
                vo.setAllUserConsumeScore(0);
                vo.setAllUserGetMoney(new BigDecimal("0.00"));
                vo.setAllUserConsumeMoney(new BigDecimal("0.00"));

                vos.add(vo);
                continue;
            }

            List<Integer> userIds = new ArrayList<>();
            for (ReUserChannel userChannel : userChannels){
                Integer userId = userChannel.getUserId();
                userIds.add(userId);
            }

            //此渠道+包名: 用户在此时间内发放的金币
            Integer userGetScores = reScoreDetailDAO.getTotalScoresByUserIds(userIds,1,startDate,endDate);
            if (userGetScores == null) { userGetScores = 0;}
            vo.setAllUserGetScore(userGetScores);

            //此渠道+包名: 用户在此时间内消耗的金币
            Integer userConsumeScores = reScoreDetailDAO.getTotalScoresByUserIds(userIds,0,startDate,endDate);
            if (userConsumeScores == null) { userConsumeScores = 0;}
            vo.setAllUserConsumeScore(userConsumeScores);

            //此渠道+包名: 用户在此时间内发放的金额
            BigDecimal userGetMoney = reAccountDetailDAO.getTotalMoneyByUserIds(userIds,1,startDate,endDate);
            if (userGetMoney == null) { userGetMoney = new BigDecimal("0.00");}
            vo.setAllUserGetMoney(userGetMoney);
            //此渠道+包名: 用户在此时间内消耗的金额
            BigDecimal userConsumeMoney = reAccountDetailDAO.getTotalMoneyByUserIds(userIds,0,startDate,endDate);
            if (userConsumeMoney == null) { userConsumeMoney = new BigDecimal("0.00");}
            vo.setAllUserConsumeMoney(userConsumeMoney);

            vos.add(vo);
        }
        return vos;
    }

}
