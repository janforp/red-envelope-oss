package com.envolope.oss.dao;

import com.envolope.oss.model.ReRecommendMissionRequire;

import com.envolope.oss.model.ReRecommendTask;
import com.envolope.oss.model.para.RecommendTaskSelectParamVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-07
 */
@Repository
public class ReRecommendMissionRequireDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long requireId) {
        ReRecommendMissionRequire _key = new ReRecommendMissionRequire();
        _key.setRequireId(requireId);
        return getSqlSession().delete("re_recommend_mission_require.deleteByPrimaryKey", _key);
    }

    public void insert(ReRecommendMissionRequire record) {
        getSqlSession().insert("re_recommend_mission_require.insert", record);
    }

    public void insertSelective(ReRecommendMissionRequire record) {
        getSqlSession().insert("re_recommend_mission_require.insertSelective", record);
    }

    public void insertBatch(List<ReRecommendMissionRequire> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_recommend_mission_require.insertBatch", records);
    }

    public ReRecommendMissionRequire selectByPrimaryKey(Long requireId) {
        ReRecommendMissionRequire _key = new ReRecommendMissionRequire();
        _key.setRequireId(requireId);
        return getSqlSession().selectOne("re_recommend_mission_require.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReRecommendMissionRequire record) {
        return getSqlSession().update("re_recommend_mission_require.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReRecommendMissionRequire record) {
        return getSqlSession().update("re_recommend_mission_require.updateByPrimaryKey", record);
    }

    public List<ReRecommendMissionRequire> getAll(){
        return getSqlSession().selectList("re_recommend_mission_require.getAll");
    }

}