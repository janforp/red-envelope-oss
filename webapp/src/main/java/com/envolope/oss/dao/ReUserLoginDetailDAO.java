package com.envolope.oss.dao;

import com.envolope.oss.model.ReUserLoginDetail;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-28
 */
@Repository
public class ReUserLoginDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReUserLoginDetail _key = new ReUserLoginDetail();
        _key.setId(id);
        return getSqlSession().delete("re_user_login_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserLoginDetail record) {
        getSqlSession().insert("re_user_login_detail.insert", record);
    }

    public void insertSelective(ReUserLoginDetail record) {
        getSqlSession().insert("re_user_login_detail.insertSelective", record);
    }

    public void insertBatch(List<ReUserLoginDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_login_detail.insertBatch", records);
    }

    public ReUserLoginDetail selectByPrimaryKey(Long id) {
        ReUserLoginDetail _key = new ReUserLoginDetail();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_login_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserLoginDetail record) {
        return getSqlSession().update("re_user_login_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserLoginDetail record) {
        return getSqlSession().update("re_user_login_detail.updateByPrimaryKey", record);
    }

}