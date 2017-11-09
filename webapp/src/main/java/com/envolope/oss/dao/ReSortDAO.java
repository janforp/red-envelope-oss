package com.envolope.oss.dao;

import com.envolope.oss.model.ReSort;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-16
 */
@Repository
public class ReSortDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer sortId) {
        ReSort _key = new ReSort();
        _key.setSortId(sortId);
        return getSqlSession().delete("re_sort.deleteByPrimaryKey", _key);
    }

    public void insert(ReSort record) {
        getSqlSession().insert("re_sort.insert", record);
    }

    public void insertSelective(ReSort record) {
        getSqlSession().insert("re_sort.insertSelective", record);
    }

    public void insertBatch(List<ReSort> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_sort.insertBatch", records);
    }

    public ReSort selectByPrimaryKey(Integer sortId) {
        ReSort _key = new ReSort();
        _key.setSortId(sortId);
        return getSqlSession().selectOne("re_sort.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReSort record) {
        return getSqlSession().update("re_sort.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReSort record) {
        return getSqlSession().update("re_sort.updateByPrimaryKey", record);
    }

    public List<ReSort> getAllReSorts(){
        return getSqlSession().selectList("re_sort.getAllReSorts");
    }
}