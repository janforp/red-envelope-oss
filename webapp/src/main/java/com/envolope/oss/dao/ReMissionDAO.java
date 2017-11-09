package com.envolope.oss.dao;

import com.envolope.oss.model.ReMission;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-11
 */
@Repository
public class ReMissionDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer missionId) {
        ReMission _key = new ReMission();
        _key.setMissionId(missionId);
        return getSqlSession().delete("re_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReMission record) {
        getSqlSession().insert("re_mission.insert", record);
    }

    public void insertSelective(ReMission record) {
        getSqlSession().insert("re_mission.insertSelective", record);
    }

    public void insertBatch(List<ReMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_mission.insertBatch", records);
    }

    public ReMission selectByPrimaryKey(Integer missionId) {
        ReMission _key = new ReMission();
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("re_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReMission record) {
        return getSqlSession().update("re_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReMission record) {
        return getSqlSession().update("re_mission.updateByPrimaryKey", record);
    }

    /**
     * 分页查询
     * @param sortId
     * @param missionName
     * @param hot
     * @return
     */
    public List<ReMission> getMissionList(Integer sortId, String missionName, Integer hot, RowBounds bounds){

        Map<String,Object> map = new HashMap<>(3);
        map.put("sortId",sortId);
        map.put("missionName",missionName);
        map.put("hot",hot);

        return getSqlSession().selectList("re_mission.getMissionList",map,bounds);

    }

    /**
     * 分页查询 总数
     * @param sortId
     * @param missionName
     * @param hot
     * @return
     */
    public Integer getMissionListNum (Integer sortId, String missionName, Integer hot) {
        Map<String,Object> map = new HashMap<>(3);
        map.put("sortId",sortId);
        map.put("missionName",missionName);
        map.put("hot",hot);

        return getSqlSession().selectOne("re_mission.getMissionListNum",map);
    }
}