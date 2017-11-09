package com.envolope.oss.dao;

import com.envolope.oss.model.ReCustomerEnvelope;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-07-14
 */
@Repository
public class ReCustomerEnvelopeDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer id) {
        ReCustomerEnvelope _key = new ReCustomerEnvelope();
        _key.setId(id);
        return getSqlSession().delete("re_customer_envelope.deleteByPrimaryKey", _key);
    }

    public void insert(ReCustomerEnvelope record) {
        getSqlSession().insert("re_customer_envelope.insert", record);
    }

    public void insertSelective(ReCustomerEnvelope record) {
        getSqlSession().insert("re_customer_envelope.insertSelective", record);
    }

    public void insertBatch(List<ReCustomerEnvelope> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_customer_envelope.insertBatch", records);
    }

    public ReCustomerEnvelope selectByPrimaryKey(Integer id) {
        ReCustomerEnvelope _key = new ReCustomerEnvelope();
        _key.setId(id);
        return getSqlSession().selectOne("re_customer_envelope.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCustomerEnvelope record) {
        return getSqlSession().update("re_customer_envelope.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCustomerEnvelope record) {
        return getSqlSession().update("re_customer_envelope.updateByPrimaryKey", record);
    }
}