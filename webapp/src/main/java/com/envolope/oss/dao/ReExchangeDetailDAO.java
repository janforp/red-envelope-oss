package com.envolope.oss.dao;

import com.envolope.oss.model.ReBanner;
import com.envolope.oss.model.ReExchangeDetail;

import com.envolope.oss.model.dto.PageDto;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-09
 */
@Repository
public class ReExchangeDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReExchangeDetail _key = new ReExchangeDetail();
        _key.setId(id);
        return getSqlSession().delete("re_exchange_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReExchangeDetail record) {
        getSqlSession().insert("re_exchange_detail.insert", record);
    }

    public void insertSelective(ReExchangeDetail record) {
        getSqlSession().insert("re_exchange_detail.insertSelective", record);
    }

    public void insertBatch(List<ReExchangeDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_exchange_detail.insertBatch", records);
    }

    public ReExchangeDetail selectByPrimaryKey(Long id) {
        ReExchangeDetail _key = new ReExchangeDetail();
        _key.setId(id);
        return getSqlSession().selectOne("re_exchange_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReExchangeDetail record) {
        return getSqlSession().update("re_exchange_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReExchangeDetail record) {
        return getSqlSession().update("re_exchange_detail.updateByPrimaryKey", record);
    }

    public Integer getExchangeNum(Integer exchangeStatus){

        Map<String ,Object> params = new HashMap<>(1);
        params.put("exchangeStatus",exchangeStatus);

        return getSqlSession().selectOne("re_exchange_detail.getExchangeNum",params);
    }

    public List<ReExchangeDetail> getDetailByPageNum(Integer status , RowBounds bounds){

        Map<String ,Object> params = new HashMap<>(1);
        params.put("status",status);

        return getSqlSession().selectList("re_exchange_detail.getDetailByPageNum",params,bounds);
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