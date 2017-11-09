package com.envolope.oss.dao;

import com.envolope.oss.model.ReArticleMission;

import com.envolope.oss.model.para.ArticleSelectParamVo;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-12-06
 */
@Repository
public class ReArticleMissionDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long articleId) {
        ReArticleMission _key = new ReArticleMission();
        _key.setArticleId(articleId);
        return getSqlSession().delete("re_article_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReArticleMission record) {
        getSqlSession().insert("re_article_mission.insert", record);
    }

    public void insertSelective(ReArticleMission record) {
        getSqlSession().insert("re_article_mission.insertSelective", record);
    }

    public void insertBatch(List<ReArticleMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_article_mission.insertBatch", records);
    }

    public ReArticleMission selectByPrimaryKey(Long articleId) {
        ReArticleMission _key = new ReArticleMission();
        _key.setArticleId(articleId);
        return getSqlSession().selectOne("re_article_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReArticleMission record) {
        return getSqlSession().update("re_article_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReArticleMission record) {
        return getSqlSession().update("re_article_mission.updateByPrimaryKey", record);
    }

    /**
     * 列表的总数
     * @param param
     * @return
     */
    public Integer getNum(ArticleSelectParamVo param){

        String nowTime = ElBase.fmtTime(System.currentTimeMillis());

        Map<String ,Object> map = new HashMap<>(5);
        map.put("articleTitle",param.getArticleTitle());
        map.put("articleType",param.getArticleType());
        map.put("status",param.getStatus());
        map.put("startMissionTime",param.getStartMissionTime());
        map.put("nowTime",nowTime);

        return getSqlSession().selectOne("re_article_mission.getNum",map);
    }

    /**
     * 分页查询
     * @param param
     * @param bounds
     * @return
     */
    public List<ReArticleMission> getList(ArticleSelectParamVo param, RowBounds bounds){

        String nowTime = ElBase.fmtTime(System.currentTimeMillis());

        Map<String ,Object> map = new HashMap<>(5);
        map.put("articleTitle",param.getArticleTitle());
        map.put("articleType",param.getArticleType());
        map.put("status",param.getStatus());
        map.put("startMissionTime",param.getStartMissionTime());
        map.put("nowTime",nowTime);

        return getSqlSession().selectList("re_article_mission.getList",map,bounds);
    }

    /**
     * 锁定一条数据
     * @param articleId
     * @return
     */
    public ReArticleMission getArticleByIdLock(Long articleId){

        return getSqlSession().selectOne("re_article_mission.getArticleByIdLock",articleId);
    }
}