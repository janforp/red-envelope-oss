package com.envolope.oss.dao;

import com.envolope.oss.model.ReMissionSort;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-11
 */
@Repository
public class ReMissionSortDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer sortId) {
        ReMissionSort _key = new ReMissionSort();
        _key.setSortId(sortId);
        return getSqlSession().delete("re_mission_sort.deleteByPrimaryKey", _key);
    }

    public void insert(ReMissionSort record) {
        getSqlSession().insert("re_mission_sort.insert", record);
    }

    public void insertSelective(ReMissionSort record) {
        getSqlSession().insert("re_mission_sort.insertSelective", record);
    }

    public void insertBatch(List<ReMissionSort> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_mission_sort.insertBatch", records);
    }

    public ReMissionSort selectByPrimaryKey(Integer sortId) {
        ReMissionSort _key = new ReMissionSort();
        _key.setSortId(sortId);
        return getSqlSession().selectOne("re_mission_sort.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReMissionSort record) {
        return getSqlSession().update("re_mission_sort.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReMissionSort record) {
        return getSqlSession().update("re_mission_sort.updateByPrimaryKey", record);
    }

    /**
     * 取得所有的分类信息
     * @return
     */
    public List<ReMissionSort> getAllSort (){
        return getSqlSession().selectList("re_mission_sort.getAllSort");
    }
}