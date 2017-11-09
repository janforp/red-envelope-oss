package com.envolope.oss.dao;

import com.envolope.oss.model.ReBanner;
import com.envolope.oss.model.ReDiscoverBanner;

import com.envolope.oss.model.dto.PageDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-09
 */
@Repository
public class ReDiscoverBannerDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer bannerId) {
        ReDiscoverBanner _key = new ReDiscoverBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().delete("re_discover_banner.deleteByPrimaryKey", _key);
    }

    public void insert(ReDiscoverBanner record) {
        getSqlSession().insert("re_discover_banner.insert", record);
    }

    public void insertSelective(ReDiscoverBanner record) {
        getSqlSession().insert("re_discover_banner.insertSelective", record);
    }

    public void insertBatch(List<ReDiscoverBanner> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_discover_banner.insertBatch", records);
    }

    public ReDiscoverBanner selectByPrimaryKey(Integer bannerId) {
        ReDiscoverBanner _key = new ReDiscoverBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().selectOne("re_discover_banner.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReDiscoverBanner record) {
        return getSqlSession().update("re_discover_banner.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReDiscoverBanner record) {
        return getSqlSession().update("re_discover_banner.updateByPrimaryKey", record);
    }


    /**
     * 后去所有的📱数据
     * @return
     */
    public List<ReDiscoverBanner> getAllBanners(){
        return getSqlSession().selectList("re_discover_banner.getAllBanners");
    }


    // 返回的pageDto.list是List<NavigationPageDto>
    public PageDto selectByPage(int pageNo, int pageSize) {
        PageDto page = new PageDto();
        Long rowCount = getSqlSession().selectOne("re_discover_banner.selectCount");
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
            list = getSqlSession().selectList("re_discover_banner.selectByPage", param);
        }
        page.setList(list);
        return page;
    }
}