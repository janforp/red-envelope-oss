package com.envolope.oss.dao;

import com.envolope.oss.model.ReDiscover;

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
public class ReDiscoverDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer discoverId) {
        ReDiscover _key = new ReDiscover();
        _key.setDiscoverId(discoverId);
        return getSqlSession().delete("re_discover.deleteByPrimaryKey", _key);
    }

    public void insert(ReDiscover record) {
        getSqlSession().insert("re_discover.insert", record);
    }

    public void insertSelective(ReDiscover record) {
        getSqlSession().insert("re_discover.insertSelective", record);
    }

    public void insertBatch(List<ReDiscover> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_discover.insertBatch", records);
    }

    public ReDiscover selectByPrimaryKey(Integer discoverId) {
        ReDiscover _key = new ReDiscover();
        _key.setDiscoverId(discoverId);
        return getSqlSession().selectOne("re_discover.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReDiscover record) {
        return getSqlSession().update("re_discover.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReDiscover record) {
        return getSqlSession().update("re_discover.updateByPrimaryKey", record);
    }

    /**
     * 获取列表
     * @return
     */
    public List<ReDiscover> getAllDiscover(){
        return getSqlSession().selectList("re_discover.getAllDiscover");
    }


    // 返回的pageDto.list是List<NavigationPageDto>
    public PageDto selectByPage(int pageNo, int pageSize) {
        PageDto page = new PageDto();
        Long rowCount = getSqlSession().selectOne("re_discover.selectCount");
        if (rowCount == null) {
            rowCount = 0L;
        }
        page.init(pageNo, pageSize, rowCount);
        pageNo = page.getPageNo();// 有可能pageNo超过了允许的范围，所以使用PageDto计算过的结果
        List<ReDiscover> list;
        if (rowCount <= 0) {
            list = new ArrayList<>(0);
        } else {
            Map<String, Object> param = new HashMap<>(2);
            param.put("start", (pageNo - 1) * pageSize);
            param.put("pageSize", pageSize);
            list = getSqlSession().selectList("re_discover.selectByPage", param);
        }
        page.setList(list);
        return page;
    }
}