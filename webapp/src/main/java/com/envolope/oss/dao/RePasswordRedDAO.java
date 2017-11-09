package com.envolope.oss.dao;

import com.envolope.oss.model.RePasswordRed;

import com.envolope.oss.model.ReShareMission;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-21
 */
@Repository
public class RePasswordRedDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        RePasswordRed _key = new RePasswordRed();
        _key.setId(id);
        return getSqlSession().delete("re_password_red.deleteByPrimaryKey", _key);
    }

    public void insert(RePasswordRed record) {
        getSqlSession().insert("re_password_red.insert", record);
    }

    public void insertSelective(RePasswordRed record) {
        getSqlSession().insert("re_password_red.insertSelective", record);
    }

    public void insertBatch(List<RePasswordRed> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_password_red.insertBatch", records);
    }

    public RePasswordRed selectByPrimaryKey(Long id) {
        RePasswordRed _key = new RePasswordRed();
        _key.setId(id);
        return getSqlSession().selectOne("re_password_red.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(RePasswordRed record) {
        return getSqlSession().update("re_password_red.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(RePasswordRed record) {
        return getSqlSession().update("re_password_red.updateByPrimaryKey", record);
    }


    public Integer getNum(){
        return getSqlSession().selectOne("re_password_red.getNum");
    }

    public List<RePasswordRed> getList(RowBounds bounds){
        return getSqlSession().selectList("re_password_red.getList",null,bounds);
    }


    public RePasswordRed selectByPrimaryKeyAndLock(Long redId){
        return getSqlSession().selectOne("re_password_red.selectByPrimaryKeyAndLock",redId);
    }
}