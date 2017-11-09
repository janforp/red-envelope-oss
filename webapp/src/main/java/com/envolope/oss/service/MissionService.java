package com.envolope.oss.service;

import com.envolope.oss.dao.ReMissionDAO;
import com.envolope.oss.dao.ReMissionSortDAO;
import com.envolope.oss.model.ReMission;
import com.envolope.oss.model.ReMissionSort;
import com.envolope.oss.model.vo.MissionListVo;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Jan on 16/8/11.
 * 任务管理
 */
@Service
public class MissionService {

    @Autowired
    private ReMissionDAO reMissionDAO;
    @Autowired
    private ReMissionSortDAO reMissionSortDAO;

    /**
     * 按条件查询  分页
     * @param request
     * @param sortId
     * @param missionName
     * @param pageNow
     * @param hot
     * @param pageSize
     * @return
     */
    public List<MissionListVo> doGetMissionList(HttpServletRequest request,
                                                Integer sortId,
                                                String missionName,
                                                Integer pageNow,
                                                Integer hot,
                                                Integer pageSize) {

        if (StringUtils.isEmpty(sortId) || sortId == 0) {
            sortId = null;
        }
        if (StringUtils.isEmpty(missionName)) {
            missionName = null;
        }
        if (StringUtils.isEmpty(hot)) {
            hot = null;
        }

        int offset = (pageNow - 1) * pageSize;
        RowBounds bounds = new RowBounds(offset,pageSize);

        List<ReMission> missions =  reMissionDAO.getMissionList(sortId,missionName,hot,bounds);

        List<MissionListVo> vos = new ArrayList<>(missions.size());

        for (ReMission mission : missions){

            MissionListVo vo = turnReMissionToMissionListVo(mission);

            vos.add(vo);
        }

        return vos;
    }

    /**
     *
     * @param mission
     * @return
     */
    public MissionListVo turnReMissionToMissionListVo (ReMission mission) {

        MissionListVo vo = new MissionListVo();

        vo.setMissionId(mission.getMissionId());
        String missionName = mission.getMissionName();
        if (StringUtil.isEmpty(missionName)){
            missionName = "";
        }
        vo.setMissionName(missionName);

        String merchantName = mission.getMerchantName();
        if (StringUtil.isEmpty(merchantName)) {
            merchantName = "";
        }
        vo.setMerchantName(merchantName);

        String merchantDetail = mission.getMerchantDetail();
        if (StringUtil.isEmpty(merchantDetail)){
            merchantDetail = "";
        }
        vo.setMerchantDetail(merchantDetail);

        String missionImg = mission.getMissionImg();
        if (StringUtil.isEmpty(missionImg)) {
            missionImg = "";
        }
        vo.setMissionImg(missionImg);

        String missionReward = mission.getMissionReward();
        if (StringUtil.isEmpty(missionReward)) {
            missionReward = "";
        }
        vo.setMissionReward(missionReward);

        Integer gainRewardNum = mission.getGainRewardNum();
        if (StringUtil.isEmpty(gainRewardNum)){
            gainRewardNum = 0 ;
        }
        vo.setGainRewardNum(gainRewardNum);

        String missionAdImg = mission.getMissionAdImg();
        if (StringUtil.isEmpty(missionAdImg)) {
            missionAdImg = "";
        }
        vo.setMissionAdImg(missionAdImg);

        String missionUrl = mission.getMissionUrl();
        if (StringUtil.isEmpty(missionUrl)) {
            missionUrl = "";
        }
        vo.setMissionUrl(missionUrl);

        Integer missionHot = mission.getMissionHot();
        vo.setMissionHot(missionHot);
        if (missionHot == 0){
            vo.setShowHot("非热门");
        }
        if (missionHot == 1){
            vo.setShowHot("热门");
        }

        Integer missionSort = mission.getMissionSort();
        ReMissionSort sort = reMissionSortDAO.selectByPrimaryKey(missionSort);
        if (sort != null) {
            vo.setSortName(sort.getSortName());
        }
        if (sort == null){
            vo.setSortName("");
        }
        vo.setMissionSort(missionSort);


        vo.setMissionOrder(mission.getMissionOrder());

        Integer participantsNum = mission.getParticipantsNum();
        if (StringUtil.isEmpty(participantsNum)){
            participantsNum = 0 ;
        }
        vo.setParticipantsNum(participantsNum);

        //startTime endTime
        Integer missionStatus = mission.getMissionStatus();
        if (missionStatus == 0){
            vo.setShowStatus("已结束");
        }
        if (missionStatus == 1){
            vo.setShowStatus("进行中");
        }

        String missionExtraReward = mission.getMissionExtraReward();
        if (StringUtil.isEmpty(missionExtraReward)) {
            missionExtraReward = "";
        }
        vo.setMissionExtraReward(missionExtraReward);

        Integer showOrNot = mission.getShowOrNot();
        vo.setShowOrNot(showOrNot);

        return vo;
    }
    /**
     * 按条件查询  分页 总数
     * @param request
     * @param sortId
     * @param missionName
     * @param hot
     * @return
     */
    public Integer getMissionListNum (HttpServletRequest request,
                                      Integer sortId,
                                      String missionName,
                                      Integer hot) {

        if (StringUtils.isEmpty(sortId) || sortId == 0) {
            sortId = null;
        }
        if (StringUtils.isEmpty(missionName)) {
            missionName = null;
        }
        if (StringUtils.isEmpty(hot)) {
            hot = null;
        }

        return reMissionDAO.getMissionListNum(sortId,missionName,hot);
    }

    /**
     *
     * @param missionId
     * @return
     */
    public ReMission getMissionById(Integer missionId) {

        return reMissionDAO.selectByPrimaryKey(missionId);
    }

    /**
     * 修改 或者 添加 任务
     * @param request
     * @param mission
     * @param showStartTime
     * @param showEndTime
     * @return
     * @throws Exception
     */
    public String doSaveOrUpdateMission (HttpServletRequest request, ReMission mission,String showStartTime,String showEndTime) throws Exception {

        Integer missionId = mission.getMissionId();

        Integer startTime = Integer.valueOf(ElBase.get10Timestamp(showStartTime));
        Integer endTime = Integer.valueOf(ElBase.get10Timestamp(showEndTime));

        mission.setStartTime(startTime);
        mission.setEndTime(endTime);

        if (StringUtils.isEmpty(missionId)) { //ID空,则为添加新任务

            reMissionDAO.insertSelective(mission);

            missionId = mission.getMissionId() ;

            return JsonUtil.buildSuccess(missionId.toString());

        }else { //有ID,则为修改任务

            reMissionDAO.updateByPrimaryKeySelective(mission);
            return JsonUtil.buildSuccess(missionId.toString());
        }
    }

    public String doDeleteMissions(HttpServletRequest request,String missionIds) {


        int n = 0 ;

        if (!StringUtils.isEmpty(missionIds)) {
            String[] missions = missionIds.split("&");
            List<String> missionList = Arrays.asList(missions);

            for (String mission : missionList) {
                Integer missionId = Integer.valueOf(mission);
                ReMission reMissionSort = reMissionDAO.selectByPrimaryKey(missionId);
                if (reMissionSort != null) {
                    reMissionDAO.deleteByPrimaryKey(missionId);
                    n ++ ;
                }
            }
        }

        return JsonUtil.buildSuccess("成功删除"+n+"条纪录");
    }

}
