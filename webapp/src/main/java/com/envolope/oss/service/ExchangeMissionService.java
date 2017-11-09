package com.envolope.oss.service;

import com.envolope.oss.consts.SizeConsts;
import com.envolope.oss.dao.ReRecommendMissionDAO;
import com.envolope.oss.model.ReRecommendMission;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.RandomUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Summer on 2016/11/8.
 */
@Service
public class ExchangeMissionService {

    @Autowired
    private ReRecommendMissionDAO reRecommendMissionDAO;

    /**
     * 查询兑换码任务数
     *
     * @return
     */
    public int selectExchangeMissionCount() {
        return reRecommendMissionDAO.selectExchangeMissionCount();
    }

    /**
     * 查询兑换码任务列表
     * @param pageNo 页码
     * @return
     */
    public List<ReRecommendMission> selectExchangeMissionList(int pageNo) {
        int offset = (pageNo - 1) * SizeConsts.PAGE_SIZE_DEFAULT;
        RowBounds bounds = new RowBounds(offset, SizeConsts.PAGE_SIZE_DEFAULT);
        List<ReRecommendMission> missionList = reRecommendMissionDAO.selectExchangeMissionList(bounds);
        return missionList;
    }
    /**
     * 删除任务
     *
     * @param missionIds
     * @return
     */
    public String deleteMissions(String missionIds) {
        int n = 0;
        if (!StringUtils.isEmpty(missionIds)) {
            String[] missions = missionIds.split("&");
            List<String> missionList = Arrays.asList(missions);
            for (String mission : missionList) {
                Long missionId = Long.valueOf(mission);
                ReRecommendMission mission1 = reRecommendMissionDAO.selectByPrimaryKey(missionId);
                if (mission1 != null) {
                    n++;
                    reRecommendMissionDAO.deleteByPrimaryKey(missionId);
                }
            }
        }
        return JsonUtil.buildSuccess("成功删除" + n + "条纪录");
    }
    /**
     * 查询任务详情
     *
     * @param missionId
     * @return
     */
    public ReRecommendMission selectMissionById(Long missionId) {
        return reRecommendMissionDAO.selectByPrimaryKey(missionId);
    }


    /**
     * 保存 任务
     *
     * @param mission
     * @return
     */
    public String save(ReRecommendMission mission) {

        Long missionId = mission.getMissionId();

        if (StringUtils.isEmpty(missionId)) { // ID空,则为添加新任务

            boolean flag = true;
            while (flag) { // 生成兑换码(不能重复)
                String code = RandomUtil.getRandomLowerCaseString(15);
                ReRecommendMission reRecommendMission = reRecommendMissionDAO.selectByCode(code);
                if(reRecommendMission == null) {
                    flag = false;
                    mission.setExchangeCode(code);
                }
            }

            reRecommendMissionDAO.insertSelective(mission);

        } else { // 有ID,则为修改任务

            mission.setTotalNum(null);
            mission.setLeftNum(null);
            mission.setExchangeCode(null);
            reRecommendMissionDAO.updateByPrimaryKeySelective(mission);

        }

        return JsonUtil.buildSuccess();

    }

}
