package com.envolope.oss.dao;

import com.envolope.oss.model.ReArticleClickDetail;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-12-06
 */
@Repository
public class ReArticleClickDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long articleId, String openId) {
        ReArticleClickDetail _key = new ReArticleClickDetail();
        _key.setArticleId(articleId);
        _key.setOpenId(openId);
        return getSqlSession().delete("re_article_click_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReArticleClickDetail record) {
        getSqlSession().insert("re_article_click_detail.insert", record);
    }

    public void insertSelective(ReArticleClickDetail record) {
        getSqlSession().insert("re_article_click_detail.insertSelective", record);
    }

    public void insertBatch(List<ReArticleClickDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_article_click_detail.insertBatch", records);
    }

    public ReArticleClickDetail selectByPrimaryKey(Long articleId, String openId) {
        ReArticleClickDetail _key = new ReArticleClickDetail();
        _key.setArticleId(articleId);
        _key.setOpenId(openId);
        return getSqlSession().selectOne("re_article_click_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReArticleClickDetail record) {
        return getSqlSession().update("re_article_click_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReArticleClickDetail record) {
        return getSqlSession().update("re_article_click_detail.updateByPrimaryKey", record);
    }
}