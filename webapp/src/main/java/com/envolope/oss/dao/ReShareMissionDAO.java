package com.envolope.oss.dao;

import com.envolope.oss.model.ReShareMission;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-14
 */
@Repository
public class ReShareMissionDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long missionId) {
        ReShareMission _key = new ReShareMission();
        _key.setMissionId(missionId);
        return getSqlSession().delete("re_share_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReShareMission record) {
        getSqlSession().insert("re_share_mission.insert", record);
    }

    public void insertSelective(ReShareMission record) {
        getSqlSession().insert("re_share_mission.insertSelective", record);
    }

    public void insertBatch(List<ReShareMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_share_mission.insertBatch", records);
    }

    public ReShareMission selectByPrimaryKey(Long missionId) {
        ReShareMission _key = new ReShareMission();
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("re_share_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReShareMission record) {
        return getSqlSession().update("re_share_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReShareMission record) {
        return getSqlSession().update("re_share_mission.updateByPrimaryKey", record);
    }


    public Integer getNum(){
        return getSqlSession().selectOne("re_share_mission.getNum");
    }

    public List<ReShareMission> getList(RowBounds bounds){
        return getSqlSession().selectList("re_share_mission.getList",null,bounds);
    }

    public ReShareMission selectByPrimaryKeyAndLock(Long missionId){
        return getSqlSession().selectOne("re_share_mission.selectByPrimaryKeyAndLock",missionId);
    }
}