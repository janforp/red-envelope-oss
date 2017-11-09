package com.envolope.oss.dao;

import com.envolope.oss.model.ReCodeRedDetail;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-24
 */
@Repository
public class ReCodeRedDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReCodeRedDetail _key = new ReCodeRedDetail();
        _key.setId(id);
        return getSqlSession().delete("re_code_red_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReCodeRedDetail record) {
        getSqlSession().insert("re_code_red_detail.insert", record);
    }

    public void insertSelective(ReCodeRedDetail record) {
        getSqlSession().insert("re_code_red_detail.insertSelective", record);
    }

    public void insertBatch(List<ReCodeRedDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_code_red_detail.insertBatch", records);
    }

    public ReCodeRedDetail selectByPrimaryKey(Long id) {
        ReCodeRedDetail _key = new ReCodeRedDetail();
        _key.setId(id);
        return getSqlSession().selectOne("re_code_red_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCodeRedDetail record) {
        return getSqlSession().update("re_code_red_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCodeRedDetail record) {
        return getSqlSession().update("re_code_red_detail.updateByPrimaryKey", record);
    }
}