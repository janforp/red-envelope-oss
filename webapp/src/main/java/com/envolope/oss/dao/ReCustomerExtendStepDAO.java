package com.envolope.oss.dao;

import com.envolope.oss.model.ReCustomerExtendStep;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-07-15
 */
@Repository
public class ReCustomerExtendStepDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer id) {
        ReCustomerExtendStep _key = new ReCustomerExtendStep();
        _key.setId(id);
        return getSqlSession().delete("re_customer_extend_step.deleteByPrimaryKey", _key);
    }

    public void insert(ReCustomerExtendStep record) {
        getSqlSession().insert("re_customer_extend_step.insert", record);
    }

    public void insertSelective(ReCustomerExtendStep record) {
        getSqlSession().insert("re_customer_extend_step.insertSelective", record);
    }

    public void insertBatch(List<ReCustomerExtendStep> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_customer_extend_step.insertBatch", records);
    }

    public ReCustomerExtendStep selectByPrimaryKey(Integer id) {
        ReCustomerExtendStep _key = new ReCustomerExtendStep();
        _key.setId(id);
        return getSqlSession().selectOne("re_customer_extend_step.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCustomerExtendStep record) {
        return getSqlSession().update("re_customer_extend_step.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCustomerExtendStep record) {
        return getSqlSession().update("re_customer_extend_step.updateByPrimaryKey", record);
    }

    /**
     * 取到id 是extendId的推广的步骤
     * @param extendId
     * @return
     */
    public List<ReCustomerExtendStep> getStepsByExtendId (Integer extendId){

        return getSqlSession().selectList("re_customer_extend_step.getStepsByExtendId",extendId);
    }
}