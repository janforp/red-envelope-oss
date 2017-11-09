package com.envolope.oss.dao;

import com.envolope.oss.model.ReFixedRed;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-16
 */
@Repository
public class ReFixedRedDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer fixedId) {
        ReFixedRed _key = new ReFixedRed();
        _key.setFixedId(fixedId);
        return getSqlSession().delete("re_fixed_red.deleteByPrimaryKey", _key);
    }

    public void insert(ReFixedRed record) {
        getSqlSession().insert("re_fixed_red.insert", record);
    }

    public void insertSelective(ReFixedRed record) {
        getSqlSession().insert("re_fixed_red.insertSelective", record);
    }

    public void insertBatch(List<ReFixedRed> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_fixed_red.insertBatch", records);
    }

    public ReFixedRed selectByPrimaryKey(Integer fixedId) {
        ReFixedRed _key = new ReFixedRed();
        _key.setFixedId(fixedId);
        return getSqlSession().selectOne("re_fixed_red.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReFixedRed record) {
        return getSqlSession().update("re_fixed_red.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReFixedRed record) {
        return getSqlSession().update("re_fixed_red.updateByPrimaryKey", record);
    }

    public List<ReFixedRed> getAllFixedReds (){
        return getSqlSession().selectList("re_fixed_red.getAllFixedReds");
    }
}