package com.envolope.oss.dao;

import com.envolope.oss.model.ReAccessToken;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-04
 */
@Repository
public class ReAccessTokenDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(String appId) {
        ReAccessToken _key = new ReAccessToken();
        _key.setAppId(appId);
        return getSqlSession().delete("re_access_token.deleteByPrimaryKey", _key);
    }

    public void insert(ReAccessToken record) {
        getSqlSession().insert("re_access_token.insert", record);
    }

    public void insertSelective(ReAccessToken record) {
        getSqlSession().insert("re_access_token.insertSelective", record);
    }

    public void insertBatch(List<ReAccessToken> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_access_token.insertBatch", records);
    }

    public ReAccessToken selectByPrimaryKey(String appId) {
        ReAccessToken _key = new ReAccessToken();
        _key.setAppId(appId);
        return getSqlSession().selectOne("re_access_token.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAccessToken record) {
        return getSqlSession().update("re_access_token.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAccessToken record) {
        return getSqlSession().update("re_access_token.updateByPrimaryKey", record);
    }
}