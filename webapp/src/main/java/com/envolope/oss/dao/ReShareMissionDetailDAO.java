package com.envolope.oss.dao;

import com.envolope.oss.model.ReShareMissionDetail;

import com.envolope.oss.model.vo.data_statistics.ModuleDetail;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-14
 */
@Repository
public class ReShareMissionDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long missionId, String openId) {
        ReShareMissionDetail _key = new ReShareMissionDetail();
        _key.setMissionId(missionId);
        _key.setOpenId(openId);
        return getSqlSession().delete("re_share_mission_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReShareMissionDetail record) {
        getSqlSession().insert("re_share_mission_detail.insert", record);
    }

    public void insertSelective(ReShareMissionDetail record) {
        getSqlSession().insert("re_share_mission_detail.insertSelective", record);
    }

    public void insertBatch(List<ReShareMissionDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_share_mission_detail.insertBatch", records);
    }

    public ReShareMissionDetail selectByPrimaryKey(Long missionId, String openId) {
        ReShareMissionDetail _key = new ReShareMissionDetail();
        _key.setMissionId(missionId);
        _key.setOpenId(openId);
        return getSqlSession().selectOne("re_share_mission_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReShareMissionDetail record) {
        return getSqlSession().update("re_share_mission_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReShareMissionDetail record) {
        return getSqlSession().update("re_share_mission_detail.updateByPrimaryKey", record);
    }

    /**
     * 完成任务的用户数：去掉了重复做的
     * @param today
     * @return
     */
    public Integer getDisCompAmount(String  today){

        return getSqlSession().selectOne("re_share_mission_detail.getDisCompAmount",today);
    }

    /**
     *  完成任务的总次数，单个用户可统计多此
     * @param today
     * @return
     */
    public Integer getTotalCompAmount(String today){
        return getSqlSession().selectOne("re_share_mission_detail.getTotalCompAmount",today);
    }

    /**
     * 获取该模块<code>oneDate</code>天内完成的详情
     * @param today
     * @return
     */
    public List<ModuleDetail> getCompleteDetailByDate(String today,RowBounds bounds){

        return getSqlSession().selectList("re_share_mission_detail.getCompleteDetailByDate",today,bounds);
    }

    /**
     * 获取该模块<code>oneDate</code>天内完成的详情
     * @param today
     * @return
     */
    public Integer getCompleteDetailNum(String today){

        return getSqlSession().selectOne("re_share_mission_detail.getCompleteDetailNum",today);
    }

}