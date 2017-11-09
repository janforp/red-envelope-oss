package com.envolope.oss.dao;

import com.envolope.oss.model.ReCodeExchangeDetail;

import com.envolope.oss.model.vo.data_statistics.ModuleDetail;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-10
 */
@Repository
public class ReCodeExchangeDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long missionId, Integer userId) {
        ReCodeExchangeDetail _key = new ReCodeExchangeDetail();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_code_exchange_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReCodeExchangeDetail record) {
        getSqlSession().insert("re_code_exchange_detail.insert", record);
    }

    public void insertSelective(ReCodeExchangeDetail record) {
        getSqlSession().insert("re_code_exchange_detail.insertSelective", record);
    }

    public void insertBatch(List<ReCodeExchangeDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_code_exchange_detail.insertBatch", records);
    }

    public ReCodeExchangeDetail selectByPrimaryKey(Long missionId, Integer userId) {
        ReCodeExchangeDetail _key = new ReCodeExchangeDetail();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_code_exchange_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCodeExchangeDetail record) {
        return getSqlSession().update("re_code_exchange_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCodeExchangeDetail record) {
        return getSqlSession().update("re_code_exchange_detail.updateByPrimaryKey", record);
    }


    /**
     * 关注任务参与人数统计：每个用户只统计一次
     * @param today
     * @return
     */
    public Integer getDisPartAmount(String today){

        return getSqlSession().selectOne("re_code_exchange_detail.getDisPartAmount",today);
    }

    /**
     * 关注任务参与总数
     * @param today
     * @return
     */
    public Integer getTotalPartAmount(String today){

        return getSqlSession().selectOne("re_code_exchange_detail.getTotalPartAmount",today);
    }

    /**
     * 统计完成关注任务的总用户数，每个用户统计一次
     * @param today
     * @return
     */
    public Integer getDisCompAmount(String today){

        return getSqlSession().selectOne("re_code_exchange_detail.getDisCompAmount",today);
    }

    /**
     * 完成任务的总次数
     * @param today
     * @return
     */
    public Integer getTotalCompAmount(String today){

        return getSqlSession().selectOne("re_code_exchange_detail.getTotalCompAmount",today);
    }

    /**
     * 获取该模块<code>oneDate</code>天内完成的详情
     * @param today
     * @return
     */
    public List<ModuleDetail> getCompleteDetailByDate(String today,RowBounds bounds){

        return getSqlSession().selectList("re_code_exchange_detail.getCompleteDetailByDate",today,bounds);
    }

    /**
     * 获取该模块<code>oneDate</code>天内完成的详情
     * @param today
     * @return
     */
    public Integer getCompleteDetailNum(String today){

        return getSqlSession().selectOne("re_code_exchange_detail.getCompleteDetailNum",today);
    }

}