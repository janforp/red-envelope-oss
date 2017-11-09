package com.envolope.oss.dao;

import com.envolope.oss.model.WxShareRedDetail;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-31
 */
@Repository
public class WxShareRedDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long redId) {
        WxShareRedDetail _key = new WxShareRedDetail();
        _key.setRedId(redId);
        return getSqlSession().delete("wx_share_red_detail.deleteByPrimaryKey", _key);
    }

    public void insert(WxShareRedDetail record) {
        getSqlSession().insert("wx_share_red_detail.insert", record);
    }

    public void insertSelective(WxShareRedDetail record) {
        getSqlSession().insert("wx_share_red_detail.insertSelective", record);
    }

    public void insertBatch(List<WxShareRedDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("wx_share_red_detail.insertBatch", records);
    }

    public WxShareRedDetail selectByPrimaryKey(Long redId) {
        WxShareRedDetail _key = new WxShareRedDetail();
        _key.setRedId(redId);
        return getSqlSession().selectOne("wx_share_red_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(WxShareRedDetail record) {
        return getSqlSession().update("wx_share_red_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(WxShareRedDetail record) {
        return getSqlSession().update("wx_share_red_detail.updateByPrimaryKey", record);
    }

    /**
     * 查询某个任务的剩余红包个数
     * @param missionId
     * @return
     */
    public Integer getLeftNumByMissionId(Long missionId){

        return getSqlSession().selectOne("wx_share_red_detail.getLeftNumByMissionId", missionId);
    }
}