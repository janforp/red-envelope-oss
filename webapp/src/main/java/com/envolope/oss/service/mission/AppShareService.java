package com.envolope.oss.service.mission;

import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReShareMissionDAO;
import com.envolope.oss.model.ReShareMission;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/11/14.
 * app分享点击任务
 */
@Service
public class AppShareService {
    @Autowired
    private ReShareMissionDAO reShareMissionDAO;

    /**
     * 总数,分页用
     * @return
     */
    public Integer getNum(HttpServletRequest request){
        return reShareMissionDAO.getNum();
    }

    /**
     * 列表查询
     * @param request
     * @return
     */
    public List<ReShareMission> getList(HttpServletRequest request, Integer pageNum){
        int offset = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offset,ValueConsts.PAGE_SIZE);
        List<ReShareMission> details = reShareMissionDAO.getList(bounds);
        return details;
    }


    /**
     * 添加或修改任务
     * @param request
     * @param mission
     * @return
     */
    public String save(HttpServletRequest request,ReShareMission mission){

        Long missionId = mission.getMissionId();
        if (StringUtil.isEmpty(missionId)){

            mission.setCreateTime(ElBase.fmtTime(System.currentTimeMillis()));
            reShareMissionDAO.insert(mission);
        }else {
            reShareMissionDAO.updateByPrimaryKeySelective(mission);
        }
        return JsonUtil.buildSuccess();
    }

    /**
     * 修改数据获取
     * @param request
     * @param missionId
     * @return
     */
    public ReShareMission modifyData(HttpServletRequest request,Long missionId){

        ReShareMission mission = reShareMissionDAO.selectByPrimaryKeyAndLock(missionId);

        return mission;
    }

}
