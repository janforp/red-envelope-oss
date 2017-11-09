package com.envolope.oss.dao;

import com.envolope.oss.model.ReUserCommissionWithdraw;

import com.envolope.oss.model.para.WithdrawSelectParamVo;
import com.envolope.oss.model.vo.CommissionSelectParamVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-27
 */
@Repository
public class ReUserCommissionWithdrawDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReUserCommissionWithdraw _key = new ReUserCommissionWithdraw();
        _key.setId(id);
        return getSqlSession().delete("re_user_commission_withdraw.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserCommissionWithdraw record) {
        getSqlSession().insert("re_user_commission_withdraw.insert", record);
    }

    public void insertSelective(ReUserCommissionWithdraw record) {
        getSqlSession().insert("re_user_commission_withdraw.insertSelective", record);
    }

    public void insertBatch(List<ReUserCommissionWithdraw> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_commission_withdraw.insertBatch", records);
    }

    public ReUserCommissionWithdraw selectByPrimaryKey(Long id) {
        ReUserCommissionWithdraw _key = new ReUserCommissionWithdraw();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_commission_withdraw.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserCommissionWithdraw record) {
        return getSqlSession().update("re_user_commission_withdraw.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserCommissionWithdraw record) {
        return getSqlSession().update("re_user_commission_withdraw.updateByPrimaryKey", record);
    }

    /**
     * 分页查询佣金提现记录
     * @param paramVo
     * @param bounds
     * @return
     */
    public List<ReUserCommissionWithdraw> getAllCommissions(CommissionSelectParamVo paramVo, RowBounds bounds) {

        Map<String,Object> map = new HashMap<>(5);

        map.put("status",paramVo.getStatus());
        map.put("startTime",paramVo.getStartTime());
        map.put("endTime",paramVo.getEndTime());
        map.put("cellphone",paramVo.getCellphone());

        return getSqlSession().selectList("re_user_commission_withdraw.getAllCommissions",map,bounds);

    }

    /**
     * 数
     * @param paramVo
     * @return
     */
    public Integer getCommissionNum(CommissionSelectParamVo paramVo){

        Map<String,Object> map = new HashMap<>(5);

        map.put("status",paramVo.getStatus());
        map.put("startTime",paramVo.getStartTime());
        map.put("endTime",paramVo.getEndTime());
        map.put("cellphone",paramVo.getCellphone());

        return getSqlSession().selectOne("re_user_commission_withdraw.getCommissionNum",map);
    }
}