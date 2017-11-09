package com.envolope.oss.dao;

import com.envolope.oss.model.ReUserRecord;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-07-15
 */
@Repository
public class ReUserRecordDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer id) {
        ReUserRecord _key = new ReUserRecord();
        _key.setId(id);
        return getSqlSession().delete("re_user_record.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserRecord record) {
        getSqlSession().insert("re_user_record.insert", record);
    }

    public void insertSelective(ReUserRecord record) {
        getSqlSession().insert("re_user_record.insertSelective", record);
    }

    public void insertBatch(List<ReUserRecord> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_record.insertBatch", records);
    }

    public ReUserRecord selectByPrimaryKey(Integer id) {
        ReUserRecord _key = new ReUserRecord();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_record.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserRecord record) {
        return getSqlSession().update("re_user_record.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserRecord record) {
        return getSqlSession().update("re_user_record.updateByPrimaryKey", record);
    }

    /**
     * 找到某个微信号的所有关注用户的信息
     * @param customerId      用户id
     * @return
     */
    public List<ReUserRecord> getAllAttentionMemberOfCustomerId (Integer customerId,RowBounds bounds){

        return getSqlSession().selectList("re_user_record.getAllAttentionMemberOfCustomerId",customerId,  bounds);
    }

    /**
     * 找到某个微信号的所有关注用户的信息
     * @param customerId
     * @return
     */
    public Integer getAllAttentionMemberOfCustomerIdNum (Integer customerId){

        return getSqlSession().selectOne("re_user_record.getAllAttentionMemberOfCustomerIdNum",customerId);
    }

    /**
     * 今天的关注人员
     * @param customerId
     * @param timeScope
     * @param bounds
     * @return
     */
    public List<ReUserRecord> getAllAttentionMemberTodayOfCustomerId (Integer customerId ,List<Integer> timeScope,RowBounds bounds) {

        Integer startTime = timeScope.get(0);
        Integer endTime = timeScope.get(1);
        Map<String,Object> par = new HashMap<>(3);

        par.put("startTime",startTime);
        par.put("endTime",endTime);
        par.put("customerId",customerId);

        return getSqlSession().selectList("re_user_record.getAllAttentionMemberTodayOfCustomerId",par,bounds);

    }


    /**
     * 今天的关注人员 总数 分页
     * @param customerId
     * @param timeScope
     * @return
     */
    public Integer getAllAttentionMemberTodayOfCustomerIdNum (Integer customerId ,List<Integer> timeScope) {

        Integer startTime = timeScope.get(0);
        Integer endTime = timeScope.get(1);
        Map<String,Object> par = new HashMap<>(3);

        par.put("startTime",startTime);
        par.put("endTime",endTime);
        par.put("customerId",customerId);

        return getSqlSession().selectOne("re_user_record.getAllAttentionMemberTodayOfCustomerIdNum",par);

    }
}