package com.envolope.oss.dao;

import com.envolope.oss.model.ReDiscover;
import com.envolope.oss.model.ReStartAd;

import com.envolope.oss.model.dto.PageDto;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-16
 */
@Repository
public class ReStartAdDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer adId) {
        ReStartAd _key = new ReStartAd();
        _key.setAdId(adId);
        return getSqlSession().delete("re_start_ad.deleteByPrimaryKey", _key);
    }

    public void insert(ReStartAd record) {
        getSqlSession().insert("re_start_ad.insert", record);
    }

    public void insertSelective(ReStartAd record) {
        getSqlSession().insert("re_start_ad.insertSelective", record);
    }

    public void insertBatch(List<ReStartAd> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_start_ad.insertBatch", records);
    }

    public ReStartAd selectByPrimaryKey(Integer adId) {
        ReStartAd _key = new ReStartAd();
        _key.setAdId(adId);
        return getSqlSession().selectOne("re_start_ad.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReStartAd record) {
        return getSqlSession().update("re_start_ad.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReStartAd record) {
        return getSqlSession().update("re_start_ad.updateByPrimaryKey", record);
    }

    public List<ReStartAd> getAllStartAds () {
        return getSqlSession().selectList("re_start_ad.getAllStartAds");
    }

    // 返回的pageDto.list是List<NavigationPageDto>
    public PageDto selectByPage(int pageNo, int pageSize) {
        PageDto page = new PageDto();
        Long rowCount = getSqlSession().selectOne("re_start_ad.selectCount");
        if (rowCount == null) {
            rowCount = 0L;
        }
        page.init(pageNo, pageSize, rowCount);
        pageNo = page.getPageNo();// 有可能pageNo超过了允许的范围，所以使用PageDto计算过的结果
        List<ReStartAd> list;
        if (rowCount <= 0) {
            list = new ArrayList<>(0);
        } else {
            Map<String, Object> param = new HashMap<>(2);
            param.put("start", (pageNo - 1) * pageSize);
            param.put("pageSize", pageSize);
            list = getSqlSession().selectList("re_start_ad.selectByPage", param);
        }
        page.setList(list);
        return page;
    }
}