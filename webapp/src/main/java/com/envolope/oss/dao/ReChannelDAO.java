package com.envolope.oss.dao;

import com.envolope.oss.model.ReChannel;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-29
 */
@Repository
public class ReChannelDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReChannel _key = new ReChannel();
        _key.setId(id);
        return getSqlSession().delete("re_channel.deleteByPrimaryKey", _key);
    }

    public void insert(ReChannel record) {
        getSqlSession().insert("re_channel.insert", record);
    }

    public void insertSelective(ReChannel record) {
        getSqlSession().insert("re_channel.insertSelective", record);
    }

    public void insertBatch(List<ReChannel> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_channel.insertBatch", records);
    }

    public ReChannel selectByPrimaryKey(Long id) {
        ReChannel _key = new ReChannel();
        _key.setId(id);
        return getSqlSession().selectOne("re_channel.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReChannel record) {
        return getSqlSession().update("re_channel.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReChannel record) {
        return getSqlSession().update("re_channel.updateByPrimaryKey", record);
    }

    public List<ReChannel> selectAll() {
        return getSqlSession().selectList("re_channel.selectAll");
    }


}