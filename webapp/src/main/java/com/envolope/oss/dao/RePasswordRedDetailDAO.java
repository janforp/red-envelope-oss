package com.envolope.oss.dao;

import com.envolope.oss.model.RePasswordRedDetail;

import com.envolope.oss.model.vo.data_statistics.ModuleDetail;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-28
 */
@Repository
public class RePasswordRedDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long passwordId, Integer userId) {
        RePasswordRedDetail _key = new RePasswordRedDetail();
        _key.setPasswordId(passwordId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_password_red_detail.deleteByPrimaryKey", _key);
    }

    public void insert(RePasswordRedDetail record) {
        getSqlSession().insert("re_password_red_detail.insert", record);
    }

    public void insertSelective(RePasswordRedDetail record) {
        getSqlSession().insert("re_password_red_detail.insertSelective", record);
    }

    public void insertBatch(List<RePasswordRedDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_password_red_detail.insertBatch", records);
    }

    public RePasswordRedDetail selectByPrimaryKey(Long passwordId, Integer userId) {
        RePasswordRedDetail _key = new RePasswordRedDetail();
        _key.setPasswordId(passwordId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_password_red_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(RePasswordRedDetail record) {
        return getSqlSession().update("re_password_red_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(RePasswordRedDetail record) {
        return getSqlSession().update("re_password_red_detail.updateByPrimaryKey", record);
    }

    /**
     * 去重参加总次数
     * @param today
     * @return
     */
    public int getDisPartAmount(String  today){

        return getSqlSession().selectOne("re_password_red_detail.getDisPartAmount",today);
    }

    /**
     * 参加总次数，不去重
     * @param today
     * @return
     */
    public int getTotalPartAmount(String today){

        return getSqlSession().selectOne("re_password_red_detail.getTotalPartAmount",today);
    }

    /**
     * 完成任务的总用户数
     * @param today
     * @return
     */
    public int getDisCompAmount(String today){

        return getSqlSession().selectOne("re_password_red_detail.getDisCompAmount",today);
    }
    /**
     * 完成任务的总次数
     * @param today
     * @return
     */
    public int getTotalCompAmount(String today){

        return getSqlSession().selectOne("re_password_red_detail.getTotalCompAmount",today);
    }

    /**
     * 应发奖励
     * @param today
     * @return
     */
    public BigDecimal getShouldPayMoney(String today){

        return getSqlSession().selectOne("re_password_red_detail.getShouldPayMoney",today);
    }

    /**
     * 实际发奖励
     * @param today
     * @return
     */
    public BigDecimal getFinalPayMoney(String today){

        return getSqlSession().selectOne("re_password_red_detail.getFinalPayMoney",today);
    }

    /**
     * 获取该模块<code>oneDate</code>天内完成的详情
     * @param today
     * @return
     */
    public List<ModuleDetail> getCompleteDetailByDate(String today,RowBounds bounds){

        return getSqlSession().selectList("re_password_red_detail.getCompleteDetailByDate",today,bounds);
    }

    /**
     * 获取该模块<code>oneDate</code>天内完成的详情
     * @param today
     * @return
     */
    public Integer getCompleteDetailNum(String today){

        return getSqlSession().selectOne("re_password_red_detail.getCompleteDetailNum",today);
    }
}