package com.envolope.oss.dao;

import com.envolope.oss.model.ReScoreDetail;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-29
 */
@Repository
public class ReScoreDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer scoreId) {
        ReScoreDetail _key = new ReScoreDetail();
        _key.setScoreId(scoreId);
        return getSqlSession().delete("re_score_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReScoreDetail record) {
        getSqlSession().insert("re_score_detail.insert", record);
    }

    public void insertSelective(ReScoreDetail record) {
        getSqlSession().insert("re_score_detail.insertSelective", record);
    }

    public void insertBatch(List<ReScoreDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_score_detail.insertBatch", records);
    }

    public ReScoreDetail selectByPrimaryKey(Integer scoreId) {
        ReScoreDetail _key = new ReScoreDetail();
        _key.setScoreId(scoreId);
        return getSqlSession().selectOne("re_score_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReScoreDetail record) {
        return getSqlSession().update("re_score_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReScoreDetail record) {
        return getSqlSession().update("re_score_detail.updateByPrimaryKey", record);
    }

    /**
     * 获取某一天的注册的用户消耗的/获得的总金币
     *
     * @param scoreType  类型;0:消费积分,1:获得积分
     * @param appId
     * @param dateTime
     * @return
     */
    public Integer selectTodayUserCoin(Integer scoreType, Integer appId, String dateTime){
        Map<String,Object> map = new HashMap<>(3);
        map.put("scoreType", scoreType);
        map.put("appId", appId);
        map.put("dateTime", dateTime);
        return getSqlSession().selectOne("re_score_detail.selectTodayUserCoin", map);
    }

    /**
     * 获取某一天的用户消耗的/获得的总金币
     *
     * @param scoreType  类型;0:消费积分,1:获得积分
     * @param appId
     * @param dateTime
     * @return
     */
    public Integer selectTodayTotalCoin(Integer scoreType, Integer appId, String dateTime){
        Map<String,Object> map = new HashMap<>(3);
        map.put("scoreType", scoreType);
        map.put("appId", appId);
        map.put("dateTime", dateTime);
        return getSqlSession().selectOne("re_score_detail.selectTodayTotalCoin", map);
    }

    /**
     * 获取某一天的注册的用户消耗的/获得的总金币
     * @param type  1:获得的,0:消耗的
     * @param date  如:2016-07-28
     * @return
     */
    public Integer getTotalScoreByDateAndType(Integer type,String date){

        Map<String,Object> params = new HashMap<>(2);
        params.put("type",type);
        params.put("date",date);

        return getSqlSession().selectOne("re_score_detail.getTotalScoreByDateAndType",params);
    }

    /**
     * 获取某一天为止所有用户消耗的/获得的金币
     * @param type
     * @param date
     * @return
     */
    public Integer getTotalGetScoreByType(Integer type, String date){

        Map<String,Object> params = new HashMap<>(2);
        params.put("type",type);
        params.put("date",date);

        return getSqlSession().selectOne("re_score_detail.getTotalGetScoreByType",params);
    }

    /**
     * 获取某段时间内
     * 注册用户
     * 获得/消耗的金币
     * 如:2016-7-8至2016-7-26日之间
     * @param type
     * @param startDate
     * @param endDate
     * @return
     */
    public Integer getTotalScoreByNewUsersAndTimeScopeAndType(Integer type,String  startDate,String endDate){

        Map<String,Object> params = new HashMap<>(3);
        params.put("type",type);
        params.put("startDate",startDate);
        params.put("endDate",endDate);

        return getSqlSession().selectOne("re_score_detail.getTotalScoreByNewUsersAndTimeScopeAndType",params);
    }

    /**
     * 获取某段时间呢内
     * 所有用户
     * 获得/消耗的金币
     * 如:2016-7-8至2016-7-26日之间
     * 则统计在这段时间注册的用户,截止2016-7-26日所获取/消耗的金币数
     * @param type
     * @param startDate
     * @param endDate
     * @return
     */
    public Integer getTotalScoreByAllUserAndTimeScopeAndType(Integer type,String  startDate,String endDate) {

        Map<String,Object> params = new HashMap<>(3);
        params.put("type",type);
        params.put("startDate",startDate);
        params.put("endDate",endDate);

        return getSqlSession().selectOne("re_score_detail.getTotalScoreByAllUserAndTimeScopeAndType",params);
    }

    /**
     * 获取这些userId在这段时间内获得/消耗的积分
     * @param userIds
     * @return
     */
    public Integer getTotalScoresByUserIds(List<Integer> userIds,Integer type,String  startDate,String endDate){

        Map<String,Object> params = new HashMap<>(4);
        params.put("type",type);
        params.put("userIds",userIds);
        params.put("startDate",startDate);
        params.put("endDate",endDate);

        return getSqlSession().selectOne("re_score_detail.getTotalScoresByUserIds",params);
    }

    /**
     * 获取userId的总的记录数量,分页
     * @param userId
     * @return
     */
    public Integer getNum(Integer userId){

        return getSqlSession().selectOne("re_score_detail.getNum",userId);
    }

    /**
     * 获取用户的记录
     * @return
     */
    public List<ReScoreDetail> getList(Integer userId, RowBounds bounds){

        return getSqlSession().selectList("re_score_detail.getList",userId,bounds);
    }

    /**
     * 个人的金币总数(获得的,使用的)
     * @param userId
     * @param type
     * @return
     */
    public Integer getTotalScoreByUserIdAndType(Integer userId,Integer type){

        Map<String,Object> params = new HashMap<>(4);
        params.put("type",type);
        params.put("userId",userId);

        return getSqlSession().selectOne("re_score_detail.getTotalScoreByUserIdAndType",params);
    }

}