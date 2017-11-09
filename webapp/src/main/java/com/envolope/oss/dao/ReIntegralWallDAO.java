package com.envolope.oss.dao;

import com.envolope.oss.model.ReBanner;
import com.envolope.oss.model.ReIntegralWall;

import com.envolope.oss.model.dto.PageDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-12
 */
@Repository
public class ReIntegralWallDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer wallId) {
        ReIntegralWall _key = new ReIntegralWall();
        _key.setWallId(wallId);
        return getSqlSession().delete("re_integral_wall.deleteByPrimaryKey", _key);
    }

    public void insert(ReIntegralWall record) {
        getSqlSession().insert("re_integral_wall.insert", record);
    }

    public void insertSelective(ReIntegralWall record) {
        getSqlSession().insert("re_integral_wall.insertSelective", record);
    }

    public void insertBatch(List<ReIntegralWall> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_integral_wall.insertBatch", records);
    }

    public ReIntegralWall selectByPrimaryKey(Integer wallId) {
        ReIntegralWall _key = new ReIntegralWall();
        _key.setWallId(wallId);
        return getSqlSession().selectOne("re_integral_wall.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReIntegralWall record) {
        return getSqlSession().update("re_integral_wall.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReIntegralWall record) {
        return getSqlSession().update("re_integral_wall.updateByPrimaryKey", record);
    }

    // 返回的pageDto.list是
    public PageDto selectByPage(int pageNo, int pageSize) {
        PageDto page = new PageDto();
        Long rowCount = getSqlSession().selectOne("re_integral_wall.selectCount");
        if (rowCount == null) {
            rowCount = 0L;
        }
        page.init(pageNo, pageSize, rowCount);
        pageNo = page.getPageNo();// 有可能pageNo超过了允许的范围，所以使用PageDto计算过的结果
        List<ReBanner> list;
        if (rowCount <= 0) {
            list = new ArrayList<>(0);
        } else {
            Map<String, Object> param = new HashMap<>(2);
            param.put("start", (pageNo - 1) * pageSize);
            param.put("pageSize", pageSize);
            list = getSqlSession().selectList("re_integral_wall.selectByPage", param);
        }
        page.setList(list);
        return page;
    }
}