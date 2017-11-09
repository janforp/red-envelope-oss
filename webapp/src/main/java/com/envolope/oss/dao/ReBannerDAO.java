package com.envolope.oss.dao;

import com.envolope.oss.model.ReBanner;

import com.envolope.oss.model.ReStartAd;
import com.envolope.oss.model.dto.PageDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-08-12
 */
@Repository
public class ReBannerDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer bannerId) {
        ReBanner _key = new ReBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().delete("re_banner.deleteByPrimaryKey", _key);
    }

    public void insert(ReBanner record) {
        getSqlSession().insert("re_banner.insert", record);
    }

    public void insertSelective(ReBanner record) {
        getSqlSession().insert("re_banner.insertSelective", record);
    }

    public void insertBatch(List<ReBanner> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_banner.insertBatch", records);
    }

    public ReBanner selectByPrimaryKey(Integer bannerId) {
        ReBanner _key = new ReBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().selectOne("re_banner.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReBanner record) {
        return getSqlSession().update("re_banner.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReBanner record) {
        return getSqlSession().update("re_banner.updateByPrimaryKey", record);
    }

    /**
     * 后去所有的📱数据
     * @return
     */
    public List<ReBanner> getAllBanners(){
        return getSqlSession().selectList("re_banner.getAllBanners");
    }


    // 返回的pageDto.list是List<NavigationPageDto>
    public PageDto selectByPage(int pageNo, int pageSize) {
        PageDto page = new PageDto();
        Long rowCount = getSqlSession().selectOne("re_banner.selectCount");
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
            list = getSqlSession().selectList("re_banner.selectByPage", param);
        }
        page.setList(list);
        return page;
    }
}