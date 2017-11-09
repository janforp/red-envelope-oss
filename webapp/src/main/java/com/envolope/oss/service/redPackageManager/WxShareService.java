package com.envolope.oss.service.redPackageManager;

import com.envolope.oss.dao.WxShareMissionDAO;
import com.envolope.oss.dao.WxShareRedDetailDAO;
import com.envolope.oss.model.WxShareMission;
import com.envolope.oss.model.WxShareRedDetail;
import com.envolope.oss.model.para.ShareMissionSelectParamVo;
import com.envolope.oss.util.EntityUtil;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.RandomUtil;
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
 * Created by Jan on 16/10/28.
 * 分享任务
 */
@Service
public class WxShareService {

    @Autowired
    private WxShareMissionDAO wxShareMissionDAO;
    @Autowired
    private WxShareRedDetailDAO wxShareRedDetailDAO;

    /**
     * 处理查询参数
     * @return
     */
    public ShareMissionSelectParamVo handleParamVo(ShareMissionSelectParamVo paramVo){

        if (StringUtils.isEmpty(paramVo.getPageSize()) || paramVo.getPageSize() == 0){
            paramVo.setPageSize(10);
        }
        if (StringUtils.isEmpty(paramVo.getPageNum()) || paramVo.getPageNum() == 0) {
            paramVo.setPageNum(1);
        }
        String missionTitle = paramVo.getMissionTitle();
        if (StringUtil.isEmpty(missionTitle)){
            paramVo.setMissionTitle(null);
        }
        String  merchantName = paramVo.getMerchantName();
        if (StringUtil.isEmpty(merchantName)){
            paramVo.setMerchantName(null);
        }
        Integer isEnd = paramVo.getIsEnd();
        if (StringUtil.isEmpty(isEnd)){
            paramVo.setIsEnd(null);
        }

        return paramVo;
    }

    /**
     * 按条件查询  分页
     *
     * @param request
     * @param paramVo   查询参数对象
     * @return
     */
    public List<WxShareMission> doGetList(HttpServletRequest request, ShareMissionSelectParamVo paramVo) {

        int offset = (paramVo.getPageNum() - 1) * paramVo.getPageSize();
        RowBounds bounds = new RowBounds(offset, paramVo.getPageSize());

        List<WxShareMission> missions = wxShareMissionDAO.getList(paramVo,  bounds);

        List<WxShareMission> missionList = new ArrayList<>(missions.size());

        for (WxShareMission mission : missions) {

            WxShareMission wxShareMission = (WxShareMission) EntityUtil.nullToEmptyString(mission);

            //查询红包剩余数量
            Integer leftNum = wxShareRedDetailDAO.getLeftNumByMissionId(mission.getMissionId());

            wxShareMission.setLeftRedNum(leftNum);

            missionList.add(wxShareMission);
        }

        return missionList;
    }

    /**
     * 按条件查询  分页 总数
     *
     * @param request
     * @param paramVo
     * @return
     */
    public Integer getListNum(HttpServletRequest request,ShareMissionSelectParamVo paramVo) {

        return wxShareMissionDAO.getListNum(paramVo);
    }

    /**
     * @param missionId
     * @return
     */
    public WxShareMission getMissionById(Long missionId) {

        return wxShareMissionDAO.selectByPrimaryKey(missionId);
    }

    /**
     * 修改 或者 添加 任务
     *
     * @param request
     * @param mission
     * @return
     * @throws Exception
     */
    public String doSaveOrUpdateMission(HttpServletRequest request, WxShareMission mission) throws Exception {

        Long missionId = mission.getMissionId();

        if (StringUtils.isEmpty(missionId)) { //ID空,则为添加新任务

            mission.setCreateTime(ElBase.fmtTime(System.currentTimeMillis()));

            wxShareMissionDAO.insertSelective(mission);

            //生成对应的红包

            Integer num = createRedByMission(mission);

            return JsonUtil.buildSuccess("添加成功,生成"+num+"个红包");

        } else { //有ID,则为修改任务

            wxShareMissionDAO.updateByPrimaryKeySelective(mission);
            return JsonUtil.buildSuccess(missionId.toString());
        }
    }


    public String doDeleteMissions(HttpServletRequest request, String missionIds) {


        int n = 0;

        if (!StringUtils.isEmpty(missionIds)) {
            String[] missions = missionIds.split("&");
            List<String> missionList = Arrays.asList(missions);

            for (String mission : missionList) {
                Long missionId = Long.valueOf(mission);
                WxShareMission mission1 = wxShareMissionDAO.selectByPrimaryKey(missionId);
                if (mission1 != null) {
                    wxShareMissionDAO.deleteByPrimaryKey(missionId);
                    n++;
                }
            }
        }

        return JsonUtil.buildSuccess("成功删除" + n + "条纪录");
    }


    /**
     * 确定添加红包
     * @param request
     * @param mission
     * @return
     */
    public String doAddRed(HttpServletRequest request,WxShareMission mission){

        Integer createNum = createRedByMission(mission);

        Long missionId = mission.getMissionId() ;

        WxShareMission wxShareMission = wxShareMissionDAO.selectByPrimaryKey(missionId);

        wxShareMission.setTotalRedNum(wxShareMission.getTotalRedNum()+createNum);

        wxShareMissionDAO.updateByPrimaryKey(wxShareMission);

        return JsonUtil.buildSuccess("成功添加"+createNum+"个红包");
    }

    /**
     * 关闭任务
     * @param request
     * @param missionId
     * @return
     */
    public String endMission(HttpServletRequest request,Long missionId){

        WxShareMission mission = wxShareMissionDAO.selectByPrimaryKey(missionId);

        if (mission == null) {

            return JsonUtil.buildError("数据不存在");
        }
        if (mission.getIsEnd() == 0) {

            return JsonUtil.buildError("该任务早已关闭");
        }
        mission.setIsEnd(0);

        wxShareMissionDAO.updateByPrimaryKey(mission);

        return JsonUtil.buildSuccess();
    }

    /**
     * 打开任务
     * @param request
     * @param missionId
     * @return
     */
    public String openMission(HttpServletRequest request,Long missionId){

        WxShareMission mission = wxShareMissionDAO.selectByPrimaryKey(missionId);

        if (mission == null) {

            return JsonUtil.buildError("数据不存在");
        }
        if (mission.getIsEnd() == 1) {

            return JsonUtil.buildError("该任务早已打开");
        }
        mission.setIsEnd(1);

        wxShareMissionDAO.updateByPrimaryKey(mission);

        return JsonUtil.buildSuccess();
    }

    /**
     * 生成红包
     * @return
     */
    public Integer createRedByMission(WxShareMission mission){

        Long missionId = mission.getMissionId();

        Integer totalRedNum = mission.getTotalRedNum();

        Integer maxMoney = mission.getMaxMoney();

        Integer minMoney = mission.getMinMoney();

        List<WxShareRedDetail> redDetails = new ArrayList<>(totalRedNum);

        for (int i=0;i<totalRedNum;i++){

            WxShareRedDetail detail = new WxShareRedDetail();
            detail.setFlag(0);
            detail.setMissionId(missionId);
            Integer money = RandomUtil.getRandomBetweenMaxAndMin(minMoney,maxMoney);
            detail.setMoney(money);
            detail.setRedStatus(0);

            redDetails.add(detail);
        }
        wxShareRedDetailDAO.insertBatch(redDetails);
        return redDetails.size();
    }

    /**
     * 生成红包
     * @param missionId
     * @param maxMoney
     * @param minMoney
     * @param num
     * @return
     */
    public Integer createRedByNum(Long missionId,Integer maxMoney,Integer minMoney,Integer num){

        List<WxShareRedDetail> redDetails = new ArrayList<>(num);

        for (int i=0;i<num;i++){

            WxShareRedDetail detail = new WxShareRedDetail();
            detail.setFlag(0);
            detail.setMissionId(missionId);
            Integer money = RandomUtil.getRandomBetweenMaxAndMin(minMoney,maxMoney);
            detail.setMoney(money);
            detail.setRedStatus(0);

            redDetails.add(detail);
        }
        wxShareRedDetailDAO.insertBatch(redDetails);
        return redDetails.size();
    }
}
