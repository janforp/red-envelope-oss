package com.envolope.oss.dao;

import com.envolope.oss.model.ReAndriodUserTask;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-12-03
 */
@Repository
public class ReAndriodUserTaskDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long wallId, Integer userId) {
        ReAndriodUserTask _key = new ReAndriodUserTask();
        _key.setWallId(wallId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_andriod_user_task.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndriodUserTask record) {
        getSqlSession().insert("re_andriod_user_task.insert", record);
    }

    public void insertSelective(ReAndriodUserTask record) {
        getSqlSession().insert("re_andriod_user_task.insertSelective", record);
    }

    public void insertBatch(List<ReAndriodUserTask> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_andriod_user_task.insertBatch", records);
    }

    public ReAndriodUserTask selectByPrimaryKey(Long wallId, Integer userId) {
        ReAndriodUserTask _key = new ReAndriodUserTask();
        _key.setWallId(wallId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_andriod_user_task.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndriodUserTask record) {
        return getSqlSession().update("re_andriod_user_task.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndriodUserTask record) {
        return getSqlSession().update("re_andriod_user_task.updateByPrimaryKey", record);
    }
}