package com.envolope.oss.dao;

import com.envolope.oss.model.ReRecommendTask;

import com.envolope.oss.model.para.RecommendTaskSelectParamVo;
import com.envolope.oss.model.vo.data_statistics.ModuleDetail;
import com.sun.rowset.internal.Row;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-07
 */
@Repository
public class ReRecommendTaskDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long taskId) {
        ReRecommendTask _key = new ReRecommendTask();
        _key.setTaskId(taskId);
        return getSqlSession().delete("re_recommend_task.deleteByPrimaryKey", _key);
    }

    public void insert(ReRecommendTask record) {
        getSqlSession().insert("re_recommend_task.insert", record);
    }

    public void insertSelective(ReRecommendTask record) {
        getSqlSession().insert("re_recommend_task.insertSelective", record);
    }

    public void insertBatch(List<ReRecommendTask> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_recommend_task.insertBatch", records);
    }

    public ReRecommendTask selectByPrimaryKey(Long taskId) {
        ReRecommendTask _key = new ReRecommendTask();
        _key.setTaskId(taskId);
        return getSqlSession().selectOne("re_recommend_task.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReRecommendTask record) {
        return getSqlSession().update("re_recommend_task.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReRecommendTask record) {
        return getSqlSession().update("re_recommend_task.updateByPrimaryKey", record);
    }

    /**
     * @param paramVo
     * @param bounds
     * @return
     */
    public List<ReRecommendTask> getList(RecommendTaskSelectParamVo paramVo, RowBounds bounds){

        Map<String,Object> params = new HashMap<>(7);

        params.put("status",paramVo.getStatus());
        params.put("isVerify",paramVo.getIsVerify());
        params.put("missionTitle",paramVo.getMissionTitle());
        params.put("userPhone",paramVo.getUserPhone());
        params.put("startDate",paramVo.getStartDate());
        params.put("endDate",paramVo.getEndDate());
        params.put("missionId",paramVo.getMissionId());


        return getSqlSession().selectList("re_recommend_task.getList",params,bounds);
    }

    public Integer getListNum(RecommendTaskSelectParamVo paramVo){

        Map<String,Object> params = new HashMap<>(7);

        params.put("status",paramVo.getStatus());
        params.put("isVerify",paramVo.getIsVerify());
        params.put("missionTitle",paramVo.getMissionTitle());
        params.put("userPhone",paramVo.getUserPhone());
        params.put("startDate",paramVo.getStartDate());
        params.put("endDate",paramVo.getEndDate());
        params.put("missionId",paramVo.getMissionId());

        return getSqlSession().selectOne("re_recommend_task.getListNum",params);
    }

    /**
     * 把该任务的所有提交过的task导入excel
     * @param missionId
     * @param startDate
     * @param endDate
     * @return
     */
    public List<ReRecommendTask> getSubmitTasksByMissionId(Long missionId,String startDate,String endDate,Integer status){

        Map<String,Object> params = new HashMap<>(4);
        params.put("missionId",missionId);
        params.put("startDate",startDate);
        params.put("endDate",endDate);
        params.put("status",status);

        return getSqlSession().selectList("re_recommend_task.getSubmitTasksByMissionId",params);
    }

    /**
     * 查询当天的参与高额任务的用户数：每个用户只统计一次
     * @param today
     * @return
     */
    public Integer getDisPartAmount(String  today){

        return getSqlSession().selectOne("re_recommend_task.getDisPartAmount",today);
    }

    /**
     * 参与任务总次数，单个用户可能统计多次
     * @param today
     * @return
     */
    public Integer getTotalPartAmount(String  today){

        return getSqlSession().selectOne("re_recommend_task.getTotalPartAmount",today);
    }

    /**
     * 完成任务的用户数：去掉了重复做的人，每个用户，提交的数据只统计一次，提交审核就算完成了：状态只要不是0，4就算完成了
     * @param today
     * @return
     */
    public Integer getDisCompAmount(String  today){

        return getSqlSession().selectOne("re_recommend_task.getDisCompAmount",today);
    }

    /**
     *  完成任务的总次数，单个用户可统计多此
     * @param today
     * @return
     */
    public Integer getTotalCompAmount(String today){
        return getSqlSession().selectOne("re_recommend_task.getTotalCompAmount",today);
    }


    /**
     * 获取该模块<code>oneDate</code>天内完成的详情
     * @param today
     * @return
     */
    public List<ModuleDetail> getCompleteDetailByDate(String today,RowBounds bounds){

        return getSqlSession().selectList("re_recommend_task.getCompleteDetailByDate",today,bounds);
    }

    /**
     * 获取该模块<code>oneDate</code>天内完成的详情
     * @param today
     * @return
     */
    public Integer getCompleteDetailNum(String today){

        return getSqlSession().selectOne("re_recommend_task.getCompleteDetailNum",today);
    }
}