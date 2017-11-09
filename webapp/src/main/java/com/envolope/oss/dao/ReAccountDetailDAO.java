package com.envolope.oss.dao;

import com.envolope.oss.model.ReAccountDetail;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-27
 */
@Repository
public class ReAccountDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long detailId) {
        ReAccountDetail _key = new ReAccountDetail();
        _key.setDetailId(detailId);
        return getSqlSession().delete("re_account_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReAccountDetail record) {
        getSqlSession().insert("re_account_detail.insert", record);
    }

    public void insertSelective(ReAccountDetail record) {
        getSqlSession().insert("re_account_detail.insertSelective", record);
    }

    public void insertBatch(List<ReAccountDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_account_detail.insertBatch", records);
    }

    public ReAccountDetail selectByPrimaryKey(Long detailId) {
        ReAccountDetail _key = new ReAccountDetail();
        _key.setDetailId(detailId);
        return getSqlSession().selectOne("re_account_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAccountDetail record) {
        return getSqlSession().update("re_account_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAccountDetail record) {
        return getSqlSession().update("re_account_detail.updateByPrimaryKey", record);
    }


    /**
     * 获取某一天的注册的用户消耗的/获得的金钱
     *
     * @param detailType  类型;0:支出,1:收入
     * @param appId
     * @param dateTime
     * @return
     */
    public BigDecimal selectTodayUserMoney(Integer detailType, Integer appId, String dateTime){
        Map<String,Object> map = new HashMap<>(3);
        map.put("detailType", detailType);
        map.put("appId", appId);
        map.put("dateTime", dateTime);
        return getSqlSession().selectOne("re_account_detail.selectTodayUserMoney", map);
    }

    /**
     * 获取某一天的用户消耗的/获得的金钱
     *
     * @param detailType  类型;0:支出,1:收入
     * @param appId
     * @param dateTime
     * @return
     */
    public BigDecimal selectTodayTotalMoney(Integer detailType, Integer appId, String dateTime){
        Map<String,Object> map = new HashMap<>(3);
        map.put("detailType", detailType);
        map.put("appId", appId);
        map.put("dateTime", dateTime);
        return getSqlSession().selectOne("re_account_detail.selectTodayTotalMoney", map);
    }

    /**
     * 获取某一天的注册的用户消耗的/获得的金钱
     * @param type  1:获得的,0:消耗的
     * @param date  如:2016-07-28
     * @return
     */
    public BigDecimal getTotalMoneyByDateAndType(Integer type, String date){

        Map<String,Object> params = new HashMap<>(2);
        params.put("type",type);
        params.put("date",date);

        return getSqlSession().selectOne("re_account_detail.getTotalMoneyByDateAndType",params);
    }

    /**
     * 获取某一天所有用户消耗的/获得的金钱
     * @param type
     * @param date
     * @return
     */
    public BigDecimal getTotalGetMoneyByType(Integer type, String date){

        Map<String,Object> params = new HashMap<>(2);
        params.put("type",type);
        params.put("date",date);

        return getSqlSession().selectOne("re_account_detail.getTotalGetMoneyByType",params);
    }

    /**
     * 获取某段时间内
     * 新注册用户
     * 获得/消耗的金钱
     * @param startDate
     * @param endDate
     * @return
     */
    public BigDecimal getTotalMoneyByNewUserAndTimeScopeAndType(Integer type,String startDate,String  endDate){

        Map<String,Object> params = new HashMap<>(3);
        params.put("type",type);
        params.put("startDate",startDate);
        params.put("endDate",endDate);

        return getSqlSession().selectOne("re_account_detail.getTotalMoneyByNewUserAndTimeScopeAndType",params);
    }

    /**
     * 获取某段时间呢内
     * 所有用户
     * 获得/消耗的金币
     * 如:2016-7-8至2016-7-26日之间
     * 则统计在这段时间注册的用户,截止2016-7-26日所获取/消耗的金钱
     * @param type
     * @param startDate
     * @param endDate
     * @return
     */
    public BigDecimal getTotalMoneyByAllUserAndTimeScopeAndType(Integer type,String  startDate,String endDate) {


        Map<String,Object> params = new HashMap<>(3);
        params.put("type",type);
        params.put("startDate",startDate);
        params.put("endDate",endDate);

        return getSqlSession().selectOne("re_account_detail.getTotalMoneyByAllUserAndTimeScopeAndType",params);
    }

    /**
     * 获取这些userId在这段时间内获得/消耗的金额
     * @param userIds
     * @return
     */
    public BigDecimal getTotalMoneyByUserIds(List<Integer> userIds,Integer type,String  startDate,String endDate){

        Map<String,Object> params = new HashMap<>(4);
        params.put("type",type);
        params.put("userIds",userIds);
        params.put("startDate",startDate);
        params.put("endDate",endDate);

        return getSqlSession().selectOne("re_account_detail.getTotalMoneyByUserIds",params);
    }

    /**
     * 获取userId的总的账户记录数量,分页
     * @param userId
     * @return
     */
    public Integer getNum(Integer userId){
        return getSqlSession().selectOne("re_account_detail.getNum", userId);
    }

    /**
     * 获取用户的账户记录
     * @return
     */
    public List<ReAccountDetail> getList(Integer userId, RowBounds bounds){
        return getSqlSession().selectList("re_account_detail.getList", userId, bounds);
    }

    /**
     * 根据用户id以及提现状态
     * 获取该用户不同提现状态的总金额
     * @param type 类型;0:支出,1:收入
     * @param userId
     * @return
     */
    public BigDecimal getTotalMoneyByUserIdAndType(Integer type,Integer userId){

        Map<String,Object> map = new HashMap<>(2);

        map.put("type",type);
        map.put("userId",userId);

        return getSqlSession().selectOne("re_account_detail.getTotalMoneyByUserIdAndType",map);
    }

    /**
     * 获取某个任务，某天的发放奖励
     * @param missionType
     * @param today
     * @return
     */
    public BigDecimal getShouldPayMoney(Integer missionType,Integer subMissionType,String today){

        Map<String,Object> map = new HashMap<>(3);

        map.put("missionType",missionType);
        map.put("today",today);
        map.put("subType",subMissionType);

        return getSqlSession().selectOne("re_account_detail.getShouldPayMoney",map);
    }

    /**
     * 获取某个任务，某天的实际发放奖励
     * @param missionType
     * @param today
     * @return
     */
    public BigDecimal getFinalPayMoney(Integer missionType,Integer subMissionType,String today){

        Map<String,Object> map = new HashMap<>(3);
        map.put("missionType",missionType);
        map.put("today",today);
        map.put("subType",subMissionType);

        return getSqlSession().selectOne("re_account_detail.getFinalPayMoney",map);
    }
}