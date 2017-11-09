package com.envolope.oss.dao;

import com.envolope.oss.model.ReIndexSort;

import com.envolope.oss.model.dto.PageDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-18
 */
@Repository
public class ReIndexSortDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer sortId) {
        ReIndexSort _key = new ReIndexSort();
        _key.setSortId(sortId);
        return getSqlSession().delete("re_index_sort.deleteByPrimaryKey", _key);
    }

    public void insert(ReIndexSort record) {
        getSqlSession().insert("re_index_sort.insert", record);
    }

    public void insertSelective(ReIndexSort record) {
        getSqlSession().insert("re_index_sort.insertSelective", record);
    }

    public void insertBatch(List<ReIndexSort> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_index_sort.insertBatch", records);
    }

    public ReIndexSort selectByPrimaryKey(Integer sortId) {
        ReIndexSort _key = new ReIndexSort();
        _key.setSortId(sortId);
        return getSqlSession().selectOne("re_index_sort.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReIndexSort record) {
        return getSqlSession().update("re_index_sort.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReIndexSort record) {
        return getSqlSession().update("re_index_sort.updateByPrimaryKey", record);
    }


    public PageDto selectByPage(int pageNo, int pageSize) {
        PageDto page = new PageDto();
        Long rowCount = getSqlSession().selectOne("re_index_sort.selectCount");
        if (rowCount == null) {
            rowCount = 0L;
        }
        page.init(pageNo, pageSize, rowCount);
        pageNo = page.getPageNo();// 有可能pageNo超过了允许的范围，所以使用PageDto计算过的结果
        List<ReIndexSort> list;
        if (rowCount <= 0) {
            list = new ArrayList<>(0);
        } else {
            Map<String, Object> param = new HashMap<>(2);
            param.put("start", (pageNo - 1) * pageSize);
            param.put("pageSize", pageSize);
            list = getSqlSession().selectList("re_index_sort.selectByPage", param);
        }
        page.setList(list);
        return page;
    }

}