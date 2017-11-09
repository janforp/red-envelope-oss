package com.envolope.oss.dao;

import com.envolope.oss.model.ReRecommendMissionTask;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-31
 */
@Repository
public class ReRecommendMissionTaskDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long taskId) {
        ReRecommendMissionTask _key = new ReRecommendMissionTask();
        _key.setTaskId(taskId);
        return getSqlSession().delete("re_recommend_mission_task.deleteByPrimaryKey", _key);
    }

    public void insert(ReRecommendMissionTask record) {
        getSqlSession().insert("re_recommend_mission_task.insert", record);
    }

    public void insertSelective(ReRecommendMissionTask record) {
        getSqlSession().insert("re_recommend_mission_task.insertSelective", record);
    }

    public void insertBatch(List<ReRecommendMissionTask> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_recommend_mission_task.insertBatch", records);
    }

    public ReRecommendMissionTask selectByPrimaryKey(Long taskId) {
        ReRecommendMissionTask _key = new ReRecommendMissionTask();
        _key.setTaskId(taskId);
        return getSqlSession().selectOne("re_recommend_mission_task.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReRecommendMissionTask record) {
        return getSqlSession().update("re_recommend_mission_task.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReRecommendMissionTask record) {
        return getSqlSession().update("re_recommend_mission_task.updateByPrimaryKey", record);
    }
}