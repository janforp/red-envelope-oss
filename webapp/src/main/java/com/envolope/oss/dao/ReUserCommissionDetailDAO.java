package com.envolope.oss.dao;

import com.envolope.oss.model.ReUserCommissionDetail;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-27
 */
@Repository
public class ReUserCommissionDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReUserCommissionDetail _key = new ReUserCommissionDetail();
        _key.setId(id);
        return getSqlSession().delete("re_user_commission_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserCommissionDetail record) {
        getSqlSession().insert("re_user_commission_detail.insert", record);
    }

    public void insertSelective(ReUserCommissionDetail record) {
        getSqlSession().insert("re_user_commission_detail.insertSelective", record);
    }

    public void insertBatch(List<ReUserCommissionDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_commission_detail.insertBatch", records);
    }

    public ReUserCommissionDetail selectByPrimaryKey(Long id) {
        ReUserCommissionDetail _key = new ReUserCommissionDetail();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_commission_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserCommissionDetail record) {
        return getSqlSession().update("re_user_commission_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserCommissionDetail record) {
        return getSqlSession().update("re_user_commission_detail.updateByPrimaryKey", record);
    }
}