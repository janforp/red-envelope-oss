package com.envolope.oss.dao;

import com.envolope.oss.model.ReReceiveDetail;

import com.envolope.oss.model.vo.data_statistics.ModuleDetail;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-10
 */
@Repository
public class ReReceiveDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long detailId) {
        ReReceiveDetail _key = new ReReceiveDetail();
        _key.setDetailId(detailId);
        return getSqlSession().delete("re_receive_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReReceiveDetail record) {
        getSqlSession().insert("re_receive_detail.insert", record);
    }

    public void insertSelective(ReReceiveDetail record) {
        getSqlSession().insert("re_receive_detail.insertSelective", record);
    }

    public void insertBatch(List<ReReceiveDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_receive_detail.insertBatch", records);
    }

    public ReReceiveDetail selectByPrimaryKey(Long detailId) {
        ReReceiveDetail _key = new ReReceiveDetail();
        _key.setDetailId(detailId);
        return getSqlSession().selectOne("re_receive_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReReceiveDetail record) {
        return getSqlSession().update("re_receive_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReReceiveDetail record) {
        return getSqlSession().update("re_receive_detail.updateByPrimaryKey", record);
    }

    /**
     * 查询兑换码记录
     *
     * @return
     */
    public List<ReReceiveDetail> selectCodeList() {
        return getSqlSession().selectList("re_receive_detail.selectCodeList");
    }

    /**
     * 参与的用户数，去重
     * @param today
     * @return
     */
    public int getDisPartAmount(String today){

        return getSqlSession().selectOne("re_receive_detail.getDisPartAmount",today);
    }

    /**
     * 参与的次数
     * @param today
     * @return
     */
    public int getTotalPartAmount(String today){

        return getSqlSession().selectOne("re_receive_detail.getTotalPartAmount",today);
    }

    /**
     * 完成的用户数，去重
     * @param today
     * @return
     */
    public int getDisCompAmount(String today){

        return getSqlSession().selectOne("re_receive_detail.getDisCompAmount",today);
    }

    /**
     * 完成的总次数
     * @param today
     * @return
     */
    public int getTotalCompAmount(String today){

        return getSqlSession().selectOne("re_receive_detail.getTotalCompAmount",today);
    }

    /**
     * 应发金额
     * @param today
     * @return
     */
    public BigDecimal getShouldPayMoney(String today){

        return getSqlSession().selectOne("re_receive_detail.getShouldPayMoney",today);
    }

    /**
     * 实发金额
     * @param today
     * @return
     */
    public BigDecimal getFinalPayMoney(String today){

        return getSqlSession().selectOne("re_receive_detail.getFinalPayMoney",today);
    }

    /**
     * 获取该模块<code>today</code>天内完成的详情
     * @param today
     * @return
     */
    public List<ModuleDetail> getCompleteDetailByDate(String today,RowBounds bounds){

        return getSqlSession().selectList("re_receive_detail.getCompleteDetailByDate",today,bounds);
    }

    /**
     * 获取该模块<code>oneDate</code>天内完成的详情
     * @param today
     * @return
     */
    public Integer getCompleteDetailNum(String today){

        return getSqlSession().selectOne("re_receive_detail.getCompleteDetailNum",today);
    }

}