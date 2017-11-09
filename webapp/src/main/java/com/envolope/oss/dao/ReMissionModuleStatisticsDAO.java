package com.envolope.oss.dao;

import com.envolope.oss.model.ReMissionModuleStatistics;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-28
 */
@Repository
public class ReMissionModuleStatisticsDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(String oneDate, Integer module) {
        ReMissionModuleStatistics _key = new ReMissionModuleStatistics();
        _key.setOneDate(oneDate);
        _key.setModule(module);
        return getSqlSession().delete("re_mission_module_statistics.deleteByPrimaryKey", _key);
    }

    public void insert(ReMissionModuleStatistics record) {
        getSqlSession().insert("re_mission_module_statistics.insert", record);
    }

    public void insertSelective(ReMissionModuleStatistics record) {
        getSqlSession().insert("re_mission_module_statistics.insertSelective", record);
    }

    public void insertBatch(List<ReMissionModuleStatistics> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_mission_module_statistics.insertBatch", records);
    }

    public ReMissionModuleStatistics selectByPrimaryKey(String oneDate, Integer module) {
        ReMissionModuleStatistics _key = new ReMissionModuleStatistics();
        _key.setOneDate(oneDate);
        _key.setModule(module);
        return getSqlSession().selectOne("re_mission_module_statistics.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReMissionModuleStatistics record) {
        return getSqlSession().update("re_mission_module_statistics.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReMissionModuleStatistics record) {
        return getSqlSession().update("re_mission_module_statistics.updateByPrimaryKey", record);
    }


    /**
     * 获取今天之前的各模块的统计数据，这些数据都是由定时任务存入表：re_mission_module_statistics中
     * @param module            模块
     * @param startDate         2016-11-26
     * @param endDate           2016-11-30
     * @return
     */
    public List<ReMissionModuleStatistics> getDateByTimeScope(Integer module,String startDate,String endDate){

        Map<String,Object> params = new HashMap<>(3);
        params.put("module",module);
        params.put("startDate",startDate);
        params.put("endDate",endDate);

        return getSqlSession().selectList("re_mission_module_statistics.getDateByTimeScope",params);
    }
}