package com.envolope.oss.dao;

import com.envolope.oss.model.ReWelfare;

import com.envolope.oss.model.para.WelfareSelectParamVo;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-12-12
 */
@Repository
public class ReWelfareDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long welfareId) {
        ReWelfare _key = new ReWelfare();
        _key.setWelfareId(welfareId);
        return getSqlSession().delete("re_welfare.deleteByPrimaryKey", _key);
    }

    public void insert(ReWelfare record) {
        getSqlSession().insert("re_welfare.insert", record);
    }

    public void insertSelective(ReWelfare record) {
        getSqlSession().insert("re_welfare.insertSelective", record);
    }

    public void insertBatch(List<ReWelfare> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_welfare.insertBatch", records);
    }

    public ReWelfare selectByPrimaryKey(Long welfareId) {
        ReWelfare _key = new ReWelfare();
        _key.setWelfareId(welfareId);
        return getSqlSession().selectOne("re_welfare.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReWelfare record) {
        return getSqlSession().update("re_welfare.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReWelfare record) {
        return getSqlSession().update("re_welfare.updateByPrimaryKey", record);
    }

    /**
     * 总数
     * 用于总页数
     * @param paramVo
     * @return
     */
    public Integer getNum(WelfareSelectParamVo paramVo){

        Map<String ,Object> map = new HashMap<>(4);
        map.put("welfareTitle",paramVo.getWelfareTitle());
        map.put("welfareType",paramVo.getWelfareType());
        map.put("isSelection",paramVo.getIsSelection());
        map.put("merchantName",paramVo.getMerchantName());

        return getSqlSession().selectOne("re_welfare.getNum",map);

    }

    /**
     * 福利查询
     * 分页
     * @param paramVo
     * @param bounds
     * @return
     */
    public List<ReWelfare> getList(WelfareSelectParamVo paramVo, RowBounds bounds){

        Map<String ,Object> map = new HashMap<>(4);
        map.put("welfareTitle",paramVo.getWelfareTitle());
        map.put("welfareType",paramVo.getWelfareType());
        map.put("isSelection",paramVo.getIsSelection());
        map.put("merchantName",paramVo.getMerchantName());

        return getSqlSession().selectList("re_welfare.getList",map,bounds);
    }
}