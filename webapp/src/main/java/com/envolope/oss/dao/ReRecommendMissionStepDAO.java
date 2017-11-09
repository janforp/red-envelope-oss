package com.envolope.oss.dao;

import com.envolope.oss.model.ReRecommendMissionStep;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-07
 */
@Repository
public class ReRecommendMissionStepDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long stepId) {
        ReRecommendMissionStep _key = new ReRecommendMissionStep();
        _key.setStepId(stepId);
        return getSqlSession().delete("re_recommend_mission_step.deleteByPrimaryKey", _key);
    }

    public void insert(ReRecommendMissionStep record) {
        getSqlSession().insert("re_recommend_mission_step.insert", record);
    }

    public void insertSelective(ReRecommendMissionStep record) {
        getSqlSession().insert("re_recommend_mission_step.insertSelective", record);
    }

    public void insertBatch(List<ReRecommendMissionStep> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_recommend_mission_step.insertBatch", records);
    }

    public ReRecommendMissionStep selectByPrimaryKey(Long stepId) {
        ReRecommendMissionStep _key = new ReRecommendMissionStep();
        _key.setStepId(stepId);
        return getSqlSession().selectOne("re_recommend_mission_step.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReRecommendMissionStep record) {
        return getSqlSession().update("re_recommend_mission_step.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReRecommendMissionStep record) {
        return getSqlSession().update("re_recommend_mission_step.updateByPrimaryKey", record);
    }

    /**
     * 获取任务步骤
     * @param missionId
     * @return
     */
    public List<ReRecommendMissionStep> getAllByMissionId(Long missionId){

        return getSqlSession().selectList("re_recommend_mission_step.getAllByMissionId",missionId);
    }

    /**
     * 删除此任务的所有步骤
     * @param missionId
     */
    public void deleteByMissionId(Long missionId){
        getSqlSession().delete("re_recommend_mission_step.deleteByMissionId",missionId);
    }
}