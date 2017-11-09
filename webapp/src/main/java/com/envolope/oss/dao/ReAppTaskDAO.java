package com.envolope.oss.dao;

import com.envolope.oss.model.ReAppTask;

import com.envolope.oss.model.vo.data_statistics.ModuleDetail;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-25
 */
@Repository
public class ReAppTaskDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long taskId) {
        ReAppTask _key = new ReAppTask();
        _key.setTaskId(taskId);
        return getSqlSession().delete("re_app_task.deleteByPrimaryKey", _key);
    }

    public void insert(ReAppTask record) {
        getSqlSession().insert("re_app_task.insert", record);
    }

    public void insertSelective(ReAppTask record) {
        getSqlSession().insert("re_app_task.insertSelective", record);
    }

    public void insertBatch(List<ReAppTask> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_app_task.insertBatch", records);
    }

    public ReAppTask selectByPrimaryKey(Long taskId) {
        ReAppTask _key = new ReAppTask();
        _key.setTaskId(taskId);
        return getSqlSession().selectOne("re_app_task.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAppTask record) {
        return getSqlSession().update("re_app_task.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAppTask record) {
        return getSqlSession().update("re_app_task.updateByPrimaryKey", record);
    }



    /**
     * 关注任务参与人数统计：每个用户只统计一次,只要领取任务即可
     * @param today
     * @return
     */
    public Integer getDisPartAmount(String today){

        return getSqlSession().selectOne("re_app_task.getDisPartAmount",today);
    }

    /**
     * 关注任务参与人数统计
     * @param today
     * @return
     */
    public Integer getTotalPartAmount(String today){

        return getSqlSession().selectOne("re_app_task.getTotalPartAmount",today);
    }

    /**
     * 统计完成关注任务的总用户数，每个用户统计一次
     * @param today
     * @return
     */
    public Integer getDisCompAmount(String today){

        return getSqlSession().selectOne("re_app_task.getDisCompAmount",today);
    }

    /**
     * 统计完成关注任务的总次数
     * @param today
     * @return
     */
    public Integer getTotalCompAmount(String today){

        return getSqlSession().selectOne("re_app_task.getTotalCompAmount",today);
    }

    /**
     * 获取该模块<code>oneDate</code>天内完成的详情
     * @param today
     * @return
     */
    public List<ModuleDetail> getCompleteDetailByDate(String today,RowBounds bounds){

        return getSqlSession().selectList("re_app_task.getCompleteDetailByDate",today,bounds);
    }

    /**
     * 获取该模块<code>oneDate</code>天内完成的详情
     * @param today
     * @return
     */
    public Integer getCompleteDetailNum(String today){

        return getSqlSession().selectOne("re_app_task.getCompleteDetailNum",today);
    }

}