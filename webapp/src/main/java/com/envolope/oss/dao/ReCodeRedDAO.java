package com.envolope.oss.dao;

import com.envolope.oss.model.ReCodeRed;

import com.envolope.oss.model.para.CodeRedSelectParamVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-24
 */
@Repository
public class ReCodeRedDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer codeId) {
        ReCodeRed _key = new ReCodeRed();
        _key.setCodeId(codeId);
        return getSqlSession().delete("re_code_red.deleteByPrimaryKey", _key);
    }

    public void insert(ReCodeRed record) {
        getSqlSession().insert("re_code_red.insert", record);
    }

    public void insertSelective(ReCodeRed record) {
        getSqlSession().insert("re_code_red.insertSelective", record);
    }

    public void insertBatch(List<ReCodeRed> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_code_red.insertBatch", records);
    }

    public ReCodeRed selectByPrimaryKey(Integer codeId) {
        ReCodeRed _key = new ReCodeRed();
        _key.setCodeId(codeId);
        return getSqlSession().selectOne("re_code_red.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCodeRed record) {
        return getSqlSession().update("re_code_red.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCodeRed record) {
        return getSqlSession().update("re_code_red.updateByPrimaryKey", record);
    }

    /**
     * 获取总数
     * @param vo
     * @return
     */
    public Integer getCodeRedListNum(CodeRedSelectParamVo vo){

        Map<String ,Object> map = new HashMap<>(2);

        map.put("customerName",vo.getCustomerName());
        map.put("codeStatus",vo.getCodeStatus());

        return getSqlSession().selectOne("re_code_red.getCodeRedListNum",map);
    }

    /**
     * 分页查询
     * @param vo
     * @param bounds
     * @return
     */
    public List<ReCodeRed> getAllCodeReds(CodeRedSelectParamVo vo, RowBounds bounds){

        Map<String ,Object> map = new HashMap<>(2);

        map.put("customerName",vo.getCustomerName());
        map.put("codeStatus",vo.getCodeStatus());

        return getSqlSession().selectList("re_code_red.getAllCodeReds",map,bounds);
    }

    /**
     *
     * @param codeId
     * @return
     */
    public ReCodeRed selectLockCodeRedById(Integer codeId){

        return getSqlSession().selectOne("re_code_red.selectLockCodeRedById",codeId);
    }
}