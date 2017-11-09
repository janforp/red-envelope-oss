package com.envolope.oss.dao;

import com.envolope.oss.model.ReUserChannel;

import org.springframework.stereotype.Repository;
import sun.security.krb5.internal.PAData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-30
 */
@Repository
public class ReUserChannelDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReUserChannel _key = new ReUserChannel();
        _key.setId(id);
        return getSqlSession().delete("re_user_channel.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserChannel record) {
        getSqlSession().insert("re_user_channel.insert", record);
    }

    public void insertSelective(ReUserChannel record) {
        getSqlSession().insert("re_user_channel.insertSelective", record);
    }

    public void insertBatch(List<ReUserChannel> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_channel.insertBatch", records);
    }

    public ReUserChannel selectByPrimaryKey(Long id) {
        ReUserChannel _key = new ReUserChannel();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_channel.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserChannel record) {
        return getSqlSession().update("re_user_channel.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserChannel record) {
        return getSqlSession().update("re_user_channel.updateByPrimaryKey", record);
    }

    /**
     * 查询某个渠道包名 在此时间段内新增的用户
     *
     * @param channelName
     * @param packageName
     * @param startDate
     * @param endDate
     * @return
     */
    public Integer getNewUserNumByChannelPackageAndTime(String channelName, String packageName, String startDate, String endDate){
        Map<String ,Object> params = new HashMap<>(4);
        params.put("channelName",channelName);
        params.put("packageName",packageName);
        params.put("startDate",startDate);
        params.put("endDate",endDate);
        return getSqlSession().selectOne("re_user_channel.getNewUserNumByChannelPackageAndTime", params);
    }

    /**
     * 查询某个渠道包名的用户id
     *
     * @param channelName
     * @param packageName
     * @return
     */
    public List<ReUserChannel> getAllUserIdByChannelPackage(String channelName, String packageName){
        Map<String ,Object> params = new HashMap<>(2);
        params.put("channelName",channelName);
        params.put("packageName",packageName);
        return getSqlSession().selectList("re_user_channel.getAllUserIdByChannelPackage", params);
    }

    /**
     * 查询某个渠道包名的用户数
     *
     * @param channelName
     * @param packageName
     * @return
     */
    public Integer getAllUserNumByChannelPackage(String channelName, String packageName){
        Map<String ,Object> params = new HashMap<>(2);
        params.put("channelName",channelName);
        params.put("packageName",packageName);
        return getSqlSession().selectOne("re_user_channel.getAllUserNumByChannelPackage", params);
    }

    /**
     * 获取某一天新增的总人数
     *
     * @param appId
     * @param dayTime
     * @return
     */
    public Integer selectRegisterUserByDay(Integer appId, String dayTime){
        Map<String ,Object> map = new HashMap<>(2);
        map.put("appId", appId);
        map.put("dayTime", dayTime);
        return getSqlSession().selectOne("re_user_channel.selectRegisterUserByDay", map);
    }

    /**
     * 截止当日的总用户数
     *
     * @param appId
     * @param dayTime
     * @return
     */
    public Integer selectTotalRegisterUserByTime(Integer appId, String dayTime){
        dayTime = dayTime + " 23:59:59";
        Map<String ,Object> map = new HashMap<>(2);
        map.put("appId", appId);
        map.put("dayTime", dayTime);
        return getSqlSession().selectOne("re_user_channel.selectTotalRegisterUserByTime", map);
    }





}