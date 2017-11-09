package com.envolope.oss.dao;

import com.envolope.oss.model.ReWithdrawSort;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-18
 */
@Repository
public class ReWithdrawSortDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer withdrawSortId) {
        ReWithdrawSort _key = new ReWithdrawSort();
        _key.setWithdrawId(withdrawSortId);
        return getSqlSession().delete("re_withdraw_sort.deleteByPrimaryKey", _key);
    }

    public void insert(ReWithdrawSort record) {
        getSqlSession().insert("re_withdraw_sort.insert", record);
    }

    public void insertSelective(ReWithdrawSort record) {
        getSqlSession().insert("re_withdraw_sort.insertSelective", record);
    }

    public void insertBatch(List<ReWithdrawSort> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_withdraw_sort.insertBatch", records);
    }

    public ReWithdrawSort selectByPrimaryKey(Integer withdrawSortId) {
        ReWithdrawSort _key = new ReWithdrawSort();
        _key.setWithdrawId(withdrawSortId);
        return getSqlSession().selectOne("re_withdraw_sort.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReWithdrawSort record) {
        return getSqlSession().update("re_withdraw_sort.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReWithdrawSort record) {
        return getSqlSession().update("re_withdraw_sort.updateByPrimaryKey", record);
    }

    public List<ReWithdrawSort> getAllWithdraws(){
        return getSqlSession().selectList("re_withdraw_sort.getAllWithdraws");
    }
}