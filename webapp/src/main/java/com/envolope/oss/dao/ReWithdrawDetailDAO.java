package com.envolope.oss.dao;

import com.envolope.oss.model.ReWithdrawDetail;

import com.envolope.oss.model.para.WithdrawSelectParamVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-18
 */
@Repository
public class ReWithdrawDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long withdrawId) {
        ReWithdrawDetail _key = new ReWithdrawDetail();
        _key.setWithdrawId(withdrawId);
        return getSqlSession().delete("re_withdraw_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReWithdrawDetail record) {
        getSqlSession().insert("re_withdraw_detail.insert", record);
    }

    public void insertSelective(ReWithdrawDetail record) {
        getSqlSession().insert("re_withdraw_detail.insertSelective", record);
    }

    public void insertBatch(List<ReWithdrawDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_withdraw_detail.insertBatch", records);
    }

    public ReWithdrawDetail selectByPrimaryKey(Long withdrawId) {
        ReWithdrawDetail _key = new ReWithdrawDetail();
        _key.setWithdrawId(withdrawId);
        return getSqlSession().selectOne("re_withdraw_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReWithdrawDetail record) {
        return getSqlSession().update("re_withdraw_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReWithdrawDetail record) {
        return getSqlSession().update("re_withdraw_detail.updateByPrimaryKey", record);
    }

    public Integer getWithdrawNum(WithdrawSelectParamVo paramVo){

        Map<String,Object> map = new HashMap<>(5);

        map.put("withdrawType",paramVo.getWithdrawType());
        map.put("withdrawStatus",paramVo.getWithdrawStatus());
        map.put("startTime",paramVo.getStartTime());
        map.put("endTime",paramVo.getEndTime());
        map.put("cellphone",paramVo.getCellphone());

        return getSqlSession().selectOne("re_withdraw_detail.getWithdrawNum",map);
    }

    public List<ReWithdrawDetail> getAllWithdraws(WithdrawSelectParamVo paramVo, RowBounds bounds){

        Map<String,Object> map = new HashMap<>(5);

        map.put("withdrawType",paramVo.getWithdrawType());
        map.put("withdrawStatus",paramVo.getWithdrawStatus());
        map.put("startTime",paramVo.getStartTime());
        map.put("endTime",paramVo.getEndTime());
        map.put("cellphone",paramVo.getCellphone());

        return getSqlSession().selectList("re_withdraw_detail.getAllWithdraws",map,bounds);
    }


    /**
     * 根据用户id以及提现状态
     * 获取该用户不同提现状态的总金额
     * @param status
     * @param userId
     * @return
     */
    public BigDecimal getWithdrawMoneyByUserIdAndStatus(Integer status,Integer userId){

        Map<String,Object> map = new HashMap<>(2);

        map.put("status",status);
        map.put("userId",userId);

        return getSqlSession().selectOne("re_withdraw_detail.getWithdrawMoneyByUserIdAndStatus",map);
    }
}