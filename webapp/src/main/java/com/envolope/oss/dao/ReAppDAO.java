package com.envolope.oss.dao;

import com.envolope.oss.model.ReApp;

import com.envolope.oss.model.ReUser;
import com.envolope.oss.model.vo.integralWall.AppSelectParamVo;
import com.envolope.oss.model.vo.user.UserSelectParamVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-14
 */
@Repository
public class ReAppDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long appId) {
        ReApp _key = new ReApp();
        _key.setAppId(appId);
        return getSqlSession().delete("re_app.deleteByPrimaryKey", _key);
    }

    public void insert(ReApp record) {
        getSqlSession().insert("re_app.insert", record);
    }

    public void insertSelective(ReApp record) {
        getSqlSession().insert("re_app.insertSelective", record);
    }

    public void insertBatch(List<ReApp> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_app.insertBatch", records);
    }

    public ReApp selectByPrimaryKey(Long appId) {
        ReApp _key = new ReApp();
        _key.setAppId(appId);
        return getSqlSession().selectOne("re_app.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReApp record) {
        return getSqlSession().update("re_app.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReApp record) {
        return getSqlSession().update("re_app.updateByPrimaryKey", record);
    }

    /**
     * 分页查询
     * @param paramVo
     * @param bounds
     * @return
     */
    public List<ReApp> getList(AppSelectParamVo paramVo, RowBounds bounds){

        Map<String ,Object> params = new HashMap<>(5);
        params.put("appName",paramVo.getName());
        params.put("marketId",paramVo.getMarketId());
        params.put("packageName",paramVo.getPackageName());
//        params.put("startDate",paramVo.getStartReleaseTime());
//        params.put("endDate",paramVo.getEndReleaseTime());

        return getSqlSession().selectList("re_app.getList",params,bounds);
    }

    /**
     * 查询数量,分页
     * @param paramVo
     * @return
     */
    public Integer getNum(AppSelectParamVo paramVo){

        Map<String ,Object> params = new HashMap<>(5);
        params.put("appName",paramVo.getName());
        params.put("marketId",paramVo.getMarketId());
        params.put("packageName",paramVo.getPackageName());
//        params.put("startDate",paramVo.getStartReleaseTime());
//        params.put("endDate",paramVo.getEndReleaseTime());

        return getSqlSession().selectOne("re_app.getNum",params);
    }

    public ReApp selectAppByMarketIdAndPackageName(Integer marketId,String packageName){

        Map<String ,Object> params = new HashMap<>(5);
        params.put("marketId",marketId);
        params.put("packageName",packageName);

        return getSqlSession().selectOne("re_app.selectAppByMarketIdAndPackageName",params);
    }

}