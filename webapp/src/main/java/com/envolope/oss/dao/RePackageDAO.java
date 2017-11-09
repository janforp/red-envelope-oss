package com.envolope.oss.dao;

import com.envolope.oss.model.RePackage;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-29
 */
@Repository
public class RePackageDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        RePackage _key = new RePackage();
        _key.setId(id);
        return getSqlSession().delete("re_package.deleteByPrimaryKey", _key);
    }

    public void insert(RePackage record) {
        getSqlSession().insert("re_package.insert", record);
    }

    public void insertSelective(RePackage record) {
        getSqlSession().insert("re_package.insertSelective", record);
    }

    public void insertBatch(List<RePackage> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_package.insertBatch", records);
    }

    public RePackage selectByPrimaryKey(Long id) {
        RePackage _key = new RePackage();
        _key.setId(id);
        return getSqlSession().selectOne("re_package.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(RePackage record) {
        return getSqlSession().update("re_package.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(RePackage record) {
        return getSqlSession().update("re_package.updateByPrimaryKey", record);
    }

    public List<RePackage> selectAll() {
        return getSqlSession().selectList("re_package.selectAll");
    }

}