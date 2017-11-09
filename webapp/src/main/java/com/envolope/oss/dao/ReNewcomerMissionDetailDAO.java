package com.envolope.oss.dao;

import com.envolope.oss.model.ReNewcomerMissionDetail;

import com.envolope.oss.model.vo.data_statistics.ModuleDetail;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-28
 */
@Repository
public class ReNewcomerMissionDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long missionId, Integer userId) {
        ReNewcomerMissionDetail _key = new ReNewcomerMissionDetail();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_newcomer_mission_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReNewcomerMissionDetail record) {
        getSqlSession().insert("re_newcomer_mission_detail.insert", record);
    }

    public void insertSelective(ReNewcomerMissionDetail record) {
        getSqlSession().insert("re_newcomer_mission_detail.insertSelective", record);
    }

    public void insertBatch(List<ReNewcomerMissionDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_newcomer_mission_detail.insertBatch", records);
    }

    public ReNewcomerMissionDetail selectByPrimaryKey(Long missionId, Integer userId) {
        ReNewcomerMissionDetail _key = new ReNewcomerMissionDetail();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_newcomer_mission_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReNewcomerMissionDetail record) {
        return getSqlSession().update("re_newcomer_mission_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReNewcomerMissionDetail record) {
        return getSqlSession().update("re_newcomer_mission_detail.updateByPrimaryKey", record);
    }

    /**
     * 统计完成新手任务的总用户数，每个用户统计一次
     * @param today
     * @return
     */
    public Integer getDisCompAmount(String today){

        return getSqlSession().selectOne("re_newcomer_mission_detail.getDisCompAmount",today);
    }

    /**
     * 统计完成新手任务的总次数
     * @param today
     * @return
     */
    public Integer getTotalCompAmount(String today){

        return getSqlSession().selectOne("re_newcomer_mission_detail.getTotalCompAmount",today);
    }


    /**
     * 新手任务某个日期发放的金额
     * @param today
     * @return
     */
    public BigDecimal getFinalPayMoney(String today){

        return getSqlSession().selectOne("re_newcomer_mission_detail.getFinalPayMoney",today);
    }

    /**
     * 获取该模块<code>oneDate</code>天内完成的详情
     * @param today
     * @return
     */
    public List<ModuleDetail> getCompleteDetailByDate(String today,RowBounds bounds){

        return getSqlSession().selectList("re_newcomer_mission_detail.getCompleteDetailByDate",today,bounds);
    }

    /**
     * 获取该模块<code>oneDate</code>天内完成的详情
     * @param today
     * @return
     */
    public Integer getCompleteDetailNum(String today){

        return getSqlSession().selectOne("re_newcomer_mission_detail.getCompleteDetailNum",today);
    }

}