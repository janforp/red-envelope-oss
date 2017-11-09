package com.envolope.oss.dao;

import com.envolope.oss.model.ReShareMissionUserRecord;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-28
 */
@Repository
public class ReShareMissionUserRecordDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long missionId, Integer userId) {
        ReShareMissionUserRecord _key = new ReShareMissionUserRecord();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_share_mission_user_record.deleteByPrimaryKey", _key);
    }

    public void insert(ReShareMissionUserRecord record) {
        getSqlSession().insert("re_share_mission_user_record.insert", record);
    }

    public void insertSelective(ReShareMissionUserRecord record) {
        getSqlSession().insert("re_share_mission_user_record.insertSelective", record);
    }

    public void insertBatch(List<ReShareMissionUserRecord> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_share_mission_user_record.insertBatch", records);
    }

    public ReShareMissionUserRecord selectByPrimaryKey(Long missionId, Integer userId) {
        ReShareMissionUserRecord _key = new ReShareMissionUserRecord();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_share_mission_user_record.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReShareMissionUserRecord record) {
        return getSqlSession().update("re_share_mission_user_record.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReShareMissionUserRecord record) {
        return getSqlSession().update("re_share_mission_user_record.updateByPrimaryKey", record);
    }

    /**
     * 参与任务的总用户数，一个用户只统计一次
     * @param oneDate
     * @return
     */
    public Integer getDisPartAmount(String oneDate){

        return getSqlSession().selectOne("re_share_mission_user_record.getDisPartAmount",oneDate);
    }


    /**
     * 参与任务总次数，单个用户可能统计多次
     * @param today
     * @return
     */
    public Integer getTotalPartAmount(String  today){

        return getSqlSession().selectOne("re_share_mission_user_record.getTotalPartAmount",today);
    }

    /**
     * 完成任务的用户数：去掉了重复做的
     * @param today
     * @return
     */
    public Integer getDisCompAmount(String  today){

        return getSqlSession().selectOne("re_share_mission_user_record.getDisCompAmount",today);
    }

    /**
     *  完成任务的总次数，单个用户可统计多此
     * @param today
     * @return
     */
    public Integer getTotalCompAmount(String today){
        return getSqlSession().selectOne("re_share_mission_user_record.getTotalCompAmount",today);
    }
}