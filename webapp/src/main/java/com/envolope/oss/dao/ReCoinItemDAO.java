package com.envolope.oss.dao;

import com.envolope.oss.model.ReCoinItem;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-12
 */
@Repository
public class ReCoinItemDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long itemId) {
        ReCoinItem _key = new ReCoinItem();
        _key.setItemId(itemId);
        return getSqlSession().delete("re_coin_item.deleteByPrimaryKey", _key);
    }

    public void insert(ReCoinItem record) {
        getSqlSession().insert("re_coin_item.insert", record);
    }

    public void insertSelective(ReCoinItem record) {
        getSqlSession().insert("re_coin_item.insertSelective", record);
    }

    public void insertBatch(List<ReCoinItem> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_coin_item.insertBatch", records);
    }

    public ReCoinItem selectByPrimaryKey(Long itemId) {
        ReCoinItem _key = new ReCoinItem();
        _key.setItemId(itemId);
        return getSqlSession().selectOne("re_coin_item.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCoinItem record) {
        return getSqlSession().update("re_coin_item.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCoinItem record) {
        return getSqlSession().update("re_coin_item.updateByPrimaryKey", record);
    }
}