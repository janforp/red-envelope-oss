package com.envolope.oss.dao;

import com.envolope.oss.model.ReSortRed;

import com.envolope.oss.model.para.RedSelectParamVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-17
 */
@Repository
public class ReSortRedDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer redId) {
        ReSortRed _key = new ReSortRed();
        _key.setRedId(redId);
        return getSqlSession().delete("re_sort_red.deleteByPrimaryKey", _key);
    }

    public void insert(ReSortRed record) {
        getSqlSession().insert("re_sort_red.insert", record);
    }

    public void insertSelective(ReSortRed record) {
        getSqlSession().insert("re_sort_red.insertSelective", record);
    }

    public void insertBatch(List<ReSortRed> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_sort_red.insertBatch", records);
    }

    public ReSortRed selectByPrimaryKey(Integer redId) {
        ReSortRed _key = new ReSortRed();
        _key.setRedId(redId);
        return getSqlSession().selectOne("re_sort_red.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReSortRed record) {
        return getSqlSession().update("re_sort_red.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReSortRed record) {
        return getSqlSession().update("re_sort_red.updateByPrimaryKey", record);
    }

    /**
     private String      redName;
     private String      merchantName;
     private Integer     redSort;
     private Integer     redStatus;
     private Integer     showOrNot;
     private Long        startTime;
     private Long        endTime;
     * @param vo
     * @param bounds
     * @return
     */
    public List<ReSortRed> getAllReds(RedSelectParamVo vo, RowBounds bounds){

        Map<String ,Object> map = new HashMap<>(7);
        map.put("redName",vo.getRedName());
        map.put("merchantName",vo.getMerchantName());
        map.put("redSort",vo.getRedSort());
        map.put("redStatus",vo.getRedStatus());
        map.put("showOrNot",vo.getShowOrNot());
        map.put("startTime",vo.getStartTime());
        map.put("endTime",vo.getEndTime());

        return getSqlSession().selectList("re_sort_red.getAllReds",map,bounds);
    }

    public Integer getRedsListNum(RedSelectParamVo vo){

        Map<String ,Object> map = new HashMap<>(7);
        map.put("redName",vo.getRedName());
        map.put("merchantName",vo.getMerchantName());
        map.put("redSort",vo.getRedSort());
        map.put("redStatus",vo.getRedStatus());
        map.put("showOrNot",vo.getShowOrNot());
        map.put("startTime",vo.getStartTime());
        map.put("endTime",vo.getEndTime());

        return getSqlSession().selectOne("re_sort_red.getRedsListNum",map);
    }
}