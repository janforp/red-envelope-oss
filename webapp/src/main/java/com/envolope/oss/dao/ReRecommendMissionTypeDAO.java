package com.envolope.oss.dao;

import com.envolope.oss.model.ReRecommendMissionType;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-12-16
 */
@Repository
public class ReRecommendMissionTypeDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer typeId) {
        ReRecommendMissionType _key = new ReRecommendMissionType();
        _key.setTypeId(typeId);
        return getSqlSession().delete("re_recommend_mission_type.deleteByPrimaryKey", _key);
    }

    public void insert(ReRecommendMissionType record) {
        getSqlSession().insert("re_recommend_mission_type.insert", record);
    }

    public void insertSelective(ReRecommendMissionType record) {
        getSqlSession().insert("re_recommend_mission_type.insertSelective", record);
    }

    public void insertBatch(List<ReRecommendMissionType> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_recommend_mission_type.insertBatch", records);
    }

    public ReRecommendMissionType selectByPrimaryKey(Integer typeId) {
        ReRecommendMissionType _key = new ReRecommendMissionType();
        _key.setTypeId(typeId);
        return getSqlSession().selectOne("re_recommend_mission_type.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReRecommendMissionType record) {
        return getSqlSession().update("re_recommend_mission_type.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReRecommendMissionType record) {
        return getSqlSession().update("re_recommend_mission_type.updateByPrimaryKey", record);
    }

    public List<ReRecommendMissionType> getAllFatherTypes(){
        return getSqlSession().selectList("re_recommend_mission_type.getAllFatherTypes");
    }
}