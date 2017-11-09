package com.envolope.oss.dao;

import com.envolope.oss.model.ReUser;

import com.envolope.oss.model.vo.user.UserSelectParamVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-09
 */
@Repository
public class ReUserDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer userId) {
        ReUser _key = new ReUser();
        _key.setUserId(userId);
        return getSqlSession().delete("re_user.deleteByPrimaryKey", _key);
    }

    public void insert(ReUser record) {
        getSqlSession().insert("re_user.insert", record);
    }

    public void insertSelective(ReUser record) {
        getSqlSession().insert("re_user.insertSelective", record);
    }

    public void insertBatch(List<ReUser> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user.insertBatch", records);
    }

    public ReUser selectByPrimaryKey(Integer userId) {
        ReUser _key = new ReUser();
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_user.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUser record) {
        return getSqlSession().update("re_user.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUser record) {
        return getSqlSession().update("re_user.updateByPrimaryKey", record);
    }

    /**
     * 查询用户
     *
     * @param userId
     * @return
     */
    public ReUser selectLockByUserId(Integer userId) {
        return getSqlSession().selectOne("re_user.selectLockByUserId", userId);
    }


    /**
     * 获取今日新增的总人数(包括各种渠道的)
     * @param date (如:2016-07-28)
     * @return
     */
    public Integer getTotalRegisterUserOfOneDay(String date){

        return getSqlSession().selectOne("re_user.getTotalRegisterUserOfOneDay",date);
    }

    /**
     * 目前为止的总用户人数(不包括封号的)
     * @return
     */
    public Integer getTotalUsers(String  date){

        return getSqlSession().selectOne("re_user.getTotalUsers",date);
    }

    /**
     * 获取一段时间范围内的注册用户
     * @param startDate 2016-10-20 0:0:0
     * @param endDate   2016-10-22 0:0:0
     * @return
     */
    public Integer getTotalRegisterUserOfOneTimeScope(String startDate,String endDate){

        Map<String,Object> params = new HashMap<>(2);
        params.put("startDate",startDate);
        params.put("endDate",endDate);

        return getSqlSession().selectOne("re_user.getTotalRegisterUserOfOneTimeScope",params);
    }

    /**
     * 分页查询
     * @param paramVo
     * @param bounds
     * @return
     */
    public List<ReUser> getList(UserSelectParamVo paramVo,RowBounds bounds){

        Map<String ,Object> params = new HashMap<>(4);
        params.put("nickname",paramVo.getNickname());
        params.put("phone",paramVo.getPhone());
        params.put("start",paramVo.getStartDate());
        params.put("end",paramVo.getEndDate());

        return getSqlSession().selectList("re_user.getList",params,bounds);
    }

    /**
     * 查询数量,分页
     * @param paramVo
     * @return
     */
    public Integer getNum(UserSelectParamVo paramVo){

        Map<String ,Object> params = new HashMap<>(4);
        params.put("nickname",paramVo.getNickname());
        params.put("phone",paramVo.getPhone());
        params.put("start",paramVo.getStartDate());
        params.put("end",paramVo.getEndDate());

        return getSqlSession().selectOne("re_user.getNum",params);
    }


    /**
     * 获取给定时间内注册的用户数量,
     * 如:给定2016-11-11 01点,
     * 则得到01-02点之间注册的数量
     * @param dateHour
     * @return
     */
    public Integer getRegisterNumByHour(String dateHour){
        return getSqlSession().selectOne("re_user.getRegisterNumByHour",dateHour);
    }

    /**
     * 获取今天注册用户的列表
     * @param oneDate
     * @return
     */
    public List<ReUser> getRegisterUserToday(String oneDate){

        return getSqlSession().selectList("re_user.getRegisterUserToday",oneDate);
    }
}