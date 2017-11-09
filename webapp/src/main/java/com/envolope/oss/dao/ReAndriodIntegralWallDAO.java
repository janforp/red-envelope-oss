package com.envolope.oss.dao;

import com.envolope.oss.model.ReAndriodIntegralWall;

import com.envolope.oss.model.para.SelfIntegralSelectParam;
import com.envolope.oss.model.vo.mission.SelfIntegralListVo;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-12-02
 */
@Repository
public class ReAndriodIntegralWallDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long wallId) {
        ReAndriodIntegralWall _key = new ReAndriodIntegralWall();
        _key.setWallId(wallId);
        return getSqlSession().delete("re_andriod_integral_wall.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndriodIntegralWall record) {
        getSqlSession().insert("re_andriod_integral_wall.insert", record);
    }

    public void insertSelective(ReAndriodIntegralWall record) {
        getSqlSession().insert("re_andriod_integral_wall.insertSelective", record);
    }

    public void insertBatch(List<ReAndriodIntegralWall> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_andriod_integral_wall.insertBatch", records);
    }

    public ReAndriodIntegralWall selectByPrimaryKey(Long wallId) {
        ReAndriodIntegralWall _key = new ReAndriodIntegralWall();
        _key.setWallId(wallId);
        return getSqlSession().selectOne("re_andriod_integral_wall.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndriodIntegralWall record) {
        return getSqlSession().update("re_andriod_integral_wall.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndriodIntegralWall record) {
        return getSqlSession().update("re_andriod_integral_wall.updateByPrimaryKey", record);
    }

    /**
     * 分页查询出数据
     * @param param
     * @param bounds
     * @return
     */
    public List<SelfIntegralListVo> getList(SelfIntegralSelectParam param, RowBounds bounds){

        String now = ElBase.fmtTime(System.currentTimeMillis());

        Map<String ,Object> params = new HashMap<>(5);
        params.put("appName",param.getAppName());
        params.put("appPackage",param.getAppPackage());
        params.put("status",param.getStatus());
        params.put("startTime",param.getStartTime());
        params.put("nowTime",now);

        return getSqlSession().selectList("re_andriod_integral_wall.getList",params,bounds);
    }

    /**
     * 分页查询出数据的总数
     * @param param
     * @return
     */
    public int getNum(SelfIntegralSelectParam param){

        String now = ElBase.fmtTime(System.currentTimeMillis());

        Map<String ,Object> params = new HashMap<>(5);
        params.put("appName",param.getAppName());
        params.put("appPackage",param.getAppPackage());
        params.put("status",param.getStatus());
        params.put("startTime",param.getStartTime());
        params.put("nowTime",now);

        return getSqlSession().selectOne("re_andriod_integral_wall.getNum",params);
    }
}