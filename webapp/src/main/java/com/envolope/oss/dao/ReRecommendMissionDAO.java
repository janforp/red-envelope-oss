package com.envolope.oss.dao;

import com.envolope.oss.model.RePasswordRed;
import com.envolope.oss.model.ReRecommendMission;

import com.envolope.oss.model.para.RecommendSelectParamVo;
import com.sun.rowset.internal.Row;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-31
 */
@Repository
public class ReRecommendMissionDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long missionId) {
        ReRecommendMission _key = new ReRecommendMission();
        _key.setMissionId(missionId);
        return getSqlSession().delete("re_recommend_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReRecommendMission record) {
        getSqlSession().insert("re_recommend_mission.insert", record);
    }

    public void insertSelective(ReRecommendMission record) {
        getSqlSession().insert("re_recommend_mission.insertSelective", record);
    }

    public void insertBatch(List<ReRecommendMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_recommend_mission.insertBatch", records);
    }

    public ReRecommendMission selectByPrimaryKey(Long missionId) {
        ReRecommendMission _key = new ReRecommendMission();
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("re_recommend_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReRecommendMission record) {
        return getSqlSession().update("re_recommend_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReRecommendMission record) {
        return getSqlSession().update("re_recommend_mission.updateByPrimaryKey", record);
    }

    /**
     * 数据列表
     * @return
     */
    public List<ReRecommendMission> getList(RecommendSelectParamVo paramVo, RowBounds bounds){

        Map<String,Object> params = new HashMap<>(4);

        params.put("type",paramVo.getType());
        params.put("missionTitle",paramVo.getMissionTitle());
        params.put("isLimitTime",paramVo.getIsLimitTime());
        params.put("isVerify",paramVo.getIsVerify());

        return getSqlSession().selectList("re_recommend_mission.getList",params,bounds);
    }

    /**
     * 总数
     * @param paramVo
     * @return
     */
    public Integer getListNum(RecommendSelectParamVo paramVo){

        Map<String,Object> params = new HashMap<>(4);

        params.put("type",paramVo.getType());
        params.put("missionTitle",paramVo.getMissionTitle());
        params.put("isLimitTime",paramVo.getIsLimitTime());
        params.put("isVerify",paramVo.getIsVerify());

        return getSqlSession().selectOne("re_recommend_mission.getListNum", params);
    }

    /**
     * 查询任务
     *
     * @param missionId
     * @return
     */
    public ReRecommendMission selectLockByMissionId(Long missionId){
        return getSqlSession().selectOne("re_recommend_mission.selectLockByMissionId", missionId);
    }


    /**
     * 兑换码任务总数
     *
     * @return
     */
    public int selectExchangeMissionCount(){
        return getSqlSession().selectOne("re_recommend_mission.selectExchangeMissionCount");
    }

    /**
     * 兑换码任务列表
     *
     * @param bounds
     * @return
     */
    public List<ReRecommendMission> selectExchangeMissionList(RowBounds bounds){
        return getSqlSession().selectList("re_recommend_mission.selectExchangeMissionList", null, bounds);
    }


    /**
     * 查询兑换码任务
     *
     * @param exchangeCode
     * @return
     */
    public ReRecommendMission selectByCode(String exchangeCode){
        return getSqlSession().selectOne("re_recommend_mission.selectByCode", exchangeCode);
    }

    /**
     * 总数
     * @param type 0:需审核任务, 1:兑换码红包,2:注册任务
     * @return
     */
    public Integer getNum(Integer type,String  title){
        Map<String,Object> params = new HashMap<>(2);
        params.put("type",type);
        params.put("title",title);
        return getSqlSession().selectOne("re_recommend_mission.getNum",params);
    }

    /**
     * 列表
     * @param bounds
     * @param type      0:需审核任务, 1:兑换码红包,2:注册任务
     * @return
     */
    public List<ReRecommendMission> getMissionList(RowBounds bounds,Integer type,String title){

        Map<String,Object> params = new HashMap<>(2);
        params.put("type",type);
        params.put("title",title);

        return getSqlSession().selectList("re_recommend_mission.getMissionList",params,bounds);
    }
}