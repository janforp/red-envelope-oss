package com.envolope.oss.dao;

import com.envolope.oss.model.ReApp;
import com.envolope.oss.model.ReAppKeywords;

import com.envolope.oss.model.para.DemoSelectParam;
import com.envolope.oss.model.vo.integralWall.AppSelectParamVo;
import com.envolope.oss.model.vo.mission.KeywordListVo;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-14
 */
@Repository
public class ReAppKeywordsDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long keywordId) {
        ReAppKeywords _key = new ReAppKeywords();
        _key.setKeywordId(keywordId);
        return getSqlSession().delete("re_app_keywords.deleteByPrimaryKey", _key);
    }

    public void insert(ReAppKeywords record) {
        getSqlSession().insert("re_app_keywords.insert", record);
    }

    public void insertSelective(ReAppKeywords record) {
        getSqlSession().insert("re_app_keywords.insertSelective", record);
    }

    public void insertBatch(List<ReAppKeywords> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_app_keywords.insertBatch", records);
    }

    public ReAppKeywords selectByPrimaryKey(Long keywordId) {
        ReAppKeywords _key = new ReAppKeywords();
        _key.setKeywordId(keywordId);
        return getSqlSession().selectOne("re_app_keywords.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAppKeywords record) {
        return getSqlSession().update("re_app_keywords.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAppKeywords record) {
        return getSqlSession().update("re_app_keywords.updateByPrimaryKey", record);
    }

    public List<ReAppKeywords> getAllList(Long appId){
        return getSqlSession().selectList("re_app_keywords.getAllList",appId);
    }

    /**
     *
     * @param param
     * @return
     */
    public int getNum(DemoSelectParam param){

        Map<String,Object> map = new HashMap<>(6);
        map.put("keyword",param.getKeyword());
        map.put("appName",param.getAppName());
        map.put("marketId",param.getMarketId());
        map.put("status",param.getStatus());
        map.put("releaseTime",param.getReleaseTime());

        String nowTime = ElBase.fmtTime(System.currentTimeMillis());
        map.put("nowTime",nowTime);

        return getSqlSession().selectOne("re_app_keywords.getNum",map);
    }

    /**
     *
     * @param bounds
     * @param param
     * @return
     */
    public List<KeywordListVo> getList(RowBounds bounds, DemoSelectParam param){

        Map<String,Object> map = new HashMap<>(6);
        map.put("keyword",param.getKeyword());
        map.put("appName",param.getAppName());
        map.put("marketId",param.getMarketId());
        map.put("status",param.getStatus());
        map.put("releaseTime",param.getReleaseTime());

        String nowTime = ElBase.fmtTime(System.currentTimeMillis());
        map.put("nowTime",nowTime);

        return getSqlSession().selectList("re_app_keywords.getList",map,bounds);
    }
}