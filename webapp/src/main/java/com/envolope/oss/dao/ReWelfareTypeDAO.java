package com.envolope.oss.dao;

import com.envolope.oss.model.ReWelfareType;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-12-13
 */
@Repository
public class ReWelfareTypeDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer typeId) {
        ReWelfareType _key = new ReWelfareType();
        _key.setTypeId(typeId);
        return getSqlSession().delete("re_welfare_type.deleteByPrimaryKey", _key);
    }

    public void insert(ReWelfareType record) {
        getSqlSession().insert("re_welfare_type.insert", record);
    }

    public void insertSelective(ReWelfareType record) {
        getSqlSession().insert("re_welfare_type.insertSelective", record);
    }

    public void insertBatch(List<ReWelfareType> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_welfare_type.insertBatch", records);
    }

    public ReWelfareType selectByPrimaryKey(Integer typeId) {
        ReWelfareType _key = new ReWelfareType();
        _key.setTypeId(typeId);
        return getSqlSession().selectOne("re_welfare_type.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReWelfareType record) {
        return getSqlSession().update("re_welfare_type.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReWelfareType record) {
        return getSqlSession().update("re_welfare_type.updateByPrimaryKey", record);
    }

    /**
     * 获取福利所以的类型
     * @return
     */
    public List<ReWelfareType> getTypes(){
        return getSqlSession().selectList("re_welfare_type.getTypes");
    }
}