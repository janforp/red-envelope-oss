package com.envolope.oss.dao;

import com.envolope.oss.model.ReUserDataStatistics;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-28
 */
@Repository
public class ReUserDataStatisticsDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(String packageName, String channelName, String dataTime) {
        ReUserDataStatistics _key = new ReUserDataStatistics();
        _key.setPackageName(packageName);
        _key.setChannelName(channelName);
        _key.setDataTime(dataTime);
        return getSqlSession().delete("re_user_data_statistics.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserDataStatistics record) {
        getSqlSession().insert("re_user_data_statistics.insert", record);
    }

    public void insertSelective(ReUserDataStatistics record) {
        getSqlSession().insert("re_user_data_statistics.insertSelective", record);
    }

    public void insertBatch(List<ReUserDataStatistics> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_data_statistics.insertBatch", records);
    }

    public ReUserDataStatistics selectByPrimaryKey(String packageName, String channelName, String dataTime) {
        ReUserDataStatistics _key = new ReUserDataStatistics();
        _key.setPackageName(packageName);
        _key.setChannelName(channelName);
        _key.setDataTime(dataTime);
        return getSqlSession().selectOne("re_user_data_statistics.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserDataStatistics record) {
        return getSqlSession().update("re_user_data_statistics.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserDataStatistics record) {
        return getSqlSession().update("re_user_data_statistics.updateByPrimaryKey", record);
    }

    /**
     * 查询某一时间段的数据统计
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public ReUserDataStatistics selectDataByTime(String startTime, String endTime, String packageName, String channelName) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("packageName", packageName);
        map.put("channelName", channelName);
        return getSqlSession().selectOne("re_user_data_statistics.selectDataByTime", map);
    }

}