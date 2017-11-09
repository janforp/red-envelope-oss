package com.envolope.oss.dao;

import com.envolope.oss.model.ReCustomer;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-07-13
 */
@Repository
public class ReCustomerDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(String customerWx) {
        ReCustomer _key = new ReCustomer();
        _key.setCustomerWx(customerWx);
        return getSqlSession().delete("re_customer.deleteByPrimaryKey", _key);
    }

    /**
     * 根据id删除对应纪录
     * @param customerId
     * @return
     */
    public Integer deleteById (Integer customerId) {
        return getSqlSession().delete("re_customer.deleteById",customerId);
    }


    public void insert(ReCustomer record) {
        getSqlSession().insert("re_customer.insert", record);
    }

    public void insertSelective(ReCustomer record) {
        getSqlSession().insert("re_customer.insertSelective", record);
    }

    public void insertBatch(List<ReCustomer> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_customer.insertBatch", records);
    }

    public ReCustomer selectByPrimaryKey(Integer customerId) {
        ReCustomer _key = new ReCustomer();
        _key.setCustomerId(customerId);
        return getSqlSession().selectOne("re_customer.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCustomer record) {
        return getSqlSession().update("re_customer.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCustomer record) {
        return getSqlSession().update("re_customer.updateByPrimaryKey", record);
    }

    /**
     * 获取 所有开发者模式的  客户 列表
     * @return
     */
    public List<ReCustomer> getAllDevelopModeCostumers(String customerName, String customerWx,Integer type,Integer mode, RowBounds bounds){

        Map<String,Object> para = new HashMap<>(4);
        para.put("customerName",customerName);
        para.put("customerWx",customerWx);
        para.put("customerType",type);
        para.put("mode",mode);

        return getSqlSession().selectList("re_customer.getAllDevelopModeCostumers",para,bounds);
    }

    /**
     * 获取 所有开发者模式的 客户 列表
     * @return
     */
    public List<ReCustomer> getAllCostumers(String customerName, String customerWx,Integer customerType, RowBounds bounds){

        Map<String,Object> para = new HashMap<>(3);
        para.put("customerName",customerName);
        para.put("customerWx",customerWx);
        para.put("customerType",customerType);

        return getSqlSession().selectList("re_customer.getAllCostumers",para,bounds);
    }

    /**
     * 获取 所有客户 列表 的总数量
     * @return
     */
    public Integer getAllCostumersNum(String customerName, String customerWx,Integer type){

        Map<String,Object> para = new HashMap<>(3);
        para.put("customerName",customerName);
        para.put("customerWx",customerWx);
        para.put("customerType",type);

        return getSqlSession().selectOne("re_customer.getAllCostumersNum",para);
    }

    /**
     * 获取 所有开发者模式客户 列表 的总数量
     * @return
     */
    public Integer getDevelopModeCostumersNum(String customerName, String customerWx,Integer type,Integer mode){

        Map<String,Object> para = new HashMap<>(4);
        para.put("customerName",customerName);
        para.put("customerWx",customerWx);
        para.put("customerType",type);
        para.put("mode",mode);

        return getSqlSession().selectOne("re_customer.getDevelopModeCostumersNum",para);
    }


    /**
     * 找到推广都已经结束了的微信号
     * @return
     */
    public List<ReCustomer> getAllWxWhichItsExtendsEnd (){

        return getSqlSession().selectList("re_customer.getAllWxWhichItsExtendsEnd");
    }

    /**
     * 由 微信号 查询
     * @param wx
     * @return
     */
    public ReCustomer selectByWx(String wx) {

        return getSqlSession().selectOne("re_customer.selectByWx",wx);
    }

    /**
     * 由用户名查找纪录
     * @param customerName
     * @return
     */
    public ReCustomer selectByName (String  customerName) {
        return getSqlSession().selectOne("re_customer.selectByName",customerName);
    }
}