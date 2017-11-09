package com.envolope.oss.dao;

import com.envolope.oss.model.ReNewcomerMission;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-28
 */
@Repository
public class ReNewcomerMissionDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long missionId) {
        ReNewcomerMission _key = new ReNewcomerMission();
        _key.setMissionId(missionId);
        return getSqlSession().delete("re_newcomer_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReNewcomerMission record) {
        getSqlSession().insert("re_newcomer_mission.insert", record);
    }

    public void insertSelective(ReNewcomerMission record) {
        getSqlSession().insert("re_newcomer_mission.insertSelective", record);
    }

    public void insertBatch(List<ReNewcomerMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_newcomer_mission.insertBatch", records);
    }

    public ReNewcomerMission selectByPrimaryKey(Long missionId) {
        ReNewcomerMission _key = new ReNewcomerMission();
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("re_newcomer_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReNewcomerMission record) {
        return getSqlSession().update("re_newcomer_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReNewcomerMission record) {
        return getSqlSession().update("re_newcomer_mission.updateByPrimaryKey", record);
    }
}