package com.envolope.oss.dao;

import com.envolope.oss.model.WxShareMission;

import com.envolope.oss.model.para.ShareMissionSelectParamVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-10-31
 */
@Repository
public class WxShareMissionDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long missionId) {
        WxShareMission _key = new WxShareMission();
        _key.setMissionId(missionId);
        return getSqlSession().delete("wx_share_mission.deleteByPrimaryKey", _key);
    }

    public void insert(WxShareMission record) {
        getSqlSession().insert("wx_share_mission.insert", record);
    }

    public void insertSelective(WxShareMission record) {
        getSqlSession().insert("wx_share_mission.insertSelective", record);
    }

    public void insertBatch(List<WxShareMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("wx_share_mission.insertBatch", records);
    }

    public WxShareMission selectByPrimaryKey(Long missionId) {
        WxShareMission _key = new WxShareMission();
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("wx_share_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(WxShareMission record) {
        return getSqlSession().update("wx_share_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(WxShareMission record) {
        return getSqlSession().update("wx_share_mission.updateByPrimaryKey", record);
    }

    /**
     * 分页查询
     * @param paramVo
     * @param bounds
     * @return
     */
    public List<WxShareMission> getList(ShareMissionSelectParamVo paramVo, RowBounds bounds){

        /**
         *  //任务名称
         private String missionTitle;
         //商家名称
         private String  merchantName;
         //是否结束:0:结束,1:没有结束
         private Integer isEnd;
         */
        Map<String,Object> params = new HashMap<>();
        params.put("missionTitle",paramVo.getMissionTitle());
        params.put("merchantName",paramVo.getMerchantName());
        params.put("isEnd",paramVo.getIsEnd());

        return getSqlSession().selectList("wx_share_mission.getList",paramVo,bounds);
    }

    /**
     * 总数
     * @param paramVo
     * @return
     */
    public Integer getListNum(ShareMissionSelectParamVo paramVo){

        Map<String,Object> params = new HashMap<>();
        params.put("missionTitle",paramVo.getMissionTitle());
        params.put("merchantName",paramVo.getMerchantName());
        params.put("isEnd",paramVo.getIsEnd());

        return getSqlSession().selectOne("wx_share_mission.getListNum",params);
    }
}