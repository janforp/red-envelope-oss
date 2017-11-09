package com.envolope.oss.dao;

import com.envolope.oss.model.ReTaskLabel;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-18
 */
@Repository
public class ReTaskLabelDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long labelId) {
        ReTaskLabel _key = new ReTaskLabel();
        _key.setLabelId(labelId);
        return getSqlSession().delete("re_task_label.deleteByPrimaryKey", _key);
    }

    public void insert(ReTaskLabel record) {
        getSqlSession().insert("re_task_label.insert", record);
    }

    public void insertSelective(ReTaskLabel record) {
        getSqlSession().insert("re_task_label.insertSelective", record);
    }

    public void insertBatch(List<ReTaskLabel> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_task_label.insertBatch", records);
    }

    public ReTaskLabel selectByPrimaryKey(Long labelId) {
        ReTaskLabel _key = new ReTaskLabel();
        _key.setLabelId(labelId);
        return getSqlSession().selectOne("re_task_label.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReTaskLabel record) {
        return getSqlSession().update("re_task_label.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReTaskLabel record) {
        return getSqlSession().update("re_task_label.updateByPrimaryKey", record);
    }

    public List<ReTaskLabel> getAll(){
        return getSqlSession().selectList("re_task_label.getAll");
    }

}