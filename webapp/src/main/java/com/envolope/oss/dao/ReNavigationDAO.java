package com.envolope.oss.dao;

import com.envolope.oss.model.ReNavigation;

import com.envolope.oss.model.dto.NavigationPageDto;
import com.envolope.oss.model.dto.PageDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-15
 */
@Repository
public class ReNavigationDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer navigationId) {
        ReNavigation _key = new ReNavigation();
        _key.setNavigationId(navigationId);
        return getSqlSession().delete("re_navigation.deleteByPrimaryKey", _key);
    }

    public void insert(ReNavigation record) {
        getSqlSession().insert("re_navigation.insert", record);
    }

    public void insertSelective(ReNavigation record) {
        getSqlSession().insert("re_navigation.insertSelective", record);
    }

    public void insertBatch(List<ReNavigation> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_navigation.insertBatch", records);
    }

    public ReNavigation selectByPrimaryKey(Integer navigationId) {
        ReNavigation _key = new ReNavigation();
        _key.setNavigationId(navigationId);
        return getSqlSession().selectOne("re_navigation.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReNavigation record) {
        return getSqlSession().update("re_navigation.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReNavigation record) {
        return getSqlSession().update("re_navigation.updateByPrimaryKey", record);
    }

    /**
     * 导航列表
     * @return
     */
    public List<ReNavigation> getAllNavigation(){
        return getSqlSession().selectList("re_navigation.getAllNavigation");
    }

    // 返回的pageDto.list是List<NavigationPageDto>
    public PageDto selectByPage(int pageNo, int pageSize) {
        PageDto page = new PageDto();
        Long rowCount = getSqlSession().selectOne("re_navigation.selectCount");
        if (rowCount == null) {
            rowCount = 0L;
        }
        page.init(pageNo, pageSize, rowCount);
        pageNo = page.getPageNo();// 有可能pageNo超过了允许的范围，所以使用PageDto计算过的结果
        List<NavigationPageDto> list;
        if (rowCount <= 0) {
            list = new ArrayList<>(0);
        } else {
            Map<String, Object> param = new HashMap<>(2);
            param.put("start", (pageNo - 1) * pageSize);
            param.put("pageSize", pageSize);
            list = getSqlSession().selectList("re_navigation.selectByPage", param);
        }
        page.setList(list);
        return page;
    }


}