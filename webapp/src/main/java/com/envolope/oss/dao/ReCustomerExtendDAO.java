package com.envolope.oss.dao;

import com.envolope.oss.model.ReCustomerExtend;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-07-14
 */
@Repository
public class ReCustomerExtendDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer id) {
        ReCustomerExtend _key = new ReCustomerExtend();
        _key.setId(id);
        return getSqlSession().delete("re_customer_extend.deleteByPrimaryKey", _key);
    }

    public void insert(ReCustomerExtend record) {
        getSqlSession().insert("re_customer_extend.insert", record);
    }

    public void insertSelective(ReCustomerExtend record) {
        getSqlSession().insert("re_customer_extend.insertSelective", record);
    }

    public void insertBatch(List<ReCustomerExtend> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_customer_extend.insertBatch", records);
    }

    public ReCustomerExtend selectByPrimaryKey(Integer id) {
        ReCustomerExtend _key = new ReCustomerExtend();
        _key.setId(id);
        return getSqlSession().selectOne("re_customer_extend.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCustomerExtend record) {
        return getSqlSession().update("re_customer_extend.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCustomerExtend record) {
        return getSqlSession().update("re_customer_extend.updateByPrimaryKey", record);
    }

    /**
     * 获取 所有满条件的纪录
     * @param customerName
     * @param customerWx
     * @param status
     * @param bounds
     * @return
     */
    public List<ReCustomerExtend> getAllExtends (String customerName,
                                                  String customerWx,
                                                  Integer status,
                                                  Integer type,
                                                  RowBounds bounds){

        Map<String ,Object> par = new HashMap<>(4);
        par.put("customerName",customerName);
        par.put("customerWx",customerWx);
        par.put("status",status);
        par.put("type",type);

        return getSqlSession().selectList("re_customer_extend.getAllExtends",par,bounds);

    }

    /**
     * 用于分页
     * @param customerName
     * @param customerWx
     * @param status
     * @return
     */
    public Integer getAllExtendsNum (String customerName,
                                     String customerWx,
                                     Integer status,
                                     Integer type){

        Map<String ,Object> par = new HashMap<>(4);
        par.put("customerName",customerName);
        par.put("customerWx",customerWx);
        par.put("status",status);
        par.put("type",type);

        return getSqlSession().selectOne("re_customer_extend.getAllExtendsNum",par);
    }

    /**
     * 找到所有的进行中的推广的 微信号
     * @return
     */
    public List<Integer> getCustomersWhichStatusIsIng () {
        return getSqlSession().selectList("re_customer_extend.getCustomersWhichStatusIsIng");
    }

    /**
     * 解锁红包时,查询 并加锁
     * @param id
     * @return
     */
    public ReCustomerExtend selectLockExtendById (Integer id) {
        return getSqlSession().selectOne("re_customer_extend.selectLockExtendById",id);
    }
}