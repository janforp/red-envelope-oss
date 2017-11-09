package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.ReMission;
import com.envolope.oss.model.ReMissionSort;
import com.envolope.oss.model.vo.MissionListVo;
import com.envolope.oss.service.MissionService;
import com.envolope.oss.service.SortService;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/8/11.
 * 任务管理
 */
@Controller
@RequestMapping(value = "/page/console/auth/mission", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class MissionController {

    @Autowired
    private MissionService missionService;
    @Autowired
    private SortService sortService;

    /**
     * 跳转到任务列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/missionList")
    public String gotoMissionList(HttpServletRequest request) {

        List<ReMissionSort> sorts = sortService.getAllSort(request);

        request.setAttribute("sorts",sorts);

        return "mission_list";
    }

    /**
     * 获得任务列表 数据
     * @param request
     * @param sortId
     * @return
     */
    @RequestMapping(value = "/listByPageSize")
    @ResponseBody
    public String getMissionList(HttpServletRequest request,
                                 @RequestParam(value = ParamConsts.sortId,required = true)Integer sortId,
                                 @RequestParam(value = ParamConsts.missionName,defaultValue = "")String missionName,
                                 @RequestParam(value = ParamConsts.pageNum,defaultValue = "1")Integer pageNum,
                                 @RequestParam(value = ParamConsts.hot,defaultValue = "1")Integer hot,
                                 @RequestParam(value = ParamConsts.pageSize,defaultValue = "15")Integer pageSize) {

        List<MissionListVo> missions = missionService.doGetMissionList(request,sortId,missionName,pageNum,hot,pageSize);

        Integer total = missionService.getMissionListNum(request,sortId,missionName,hot);

        Integer totalPage = total / pageSize ;

        if (total % pageSize > 0) {
            totalPage = totalPage +1 ;
        }
        if (total == 0) {

            totalPage = 0 ;

            pageNum = 0 ;
        }


        Map<String,Object> map = new HashMap<>(3);
        map.put("totalPage",totalPage);
        map.put("missions",missions);
        map.put("pageNow",pageNum);

        return JsonUtil.buildData(map);

    }

    /**
     * 跳转到修改页面
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/updateMissionPage")
    public String gotoUpdateMissionPage(HttpServletRequest request,
                                        @RequestParam(value = ParamConsts.missionId,required = true) Integer missionId) {

        ReMission mission = missionService.getMissionById(missionId);

        List<ReMissionSort> sorts = sortService.getAllSort(request);

        Integer startTime = mission.getStartTime();
        Integer endTime = mission.getEndTime();
        String showStartTime = ElBase.fmtTime((long)startTime*1000L);
        String showEndTime = ElBase.fmtTime((long)endTime*1000L);

        request.setAttribute("mission",mission);
        request.setAttribute("sorts",sorts);
        request.setAttribute("start",showStartTime);
        request.setAttribute("end",showEndTime);

        return "update_mission";

    }

    /**
     * 修改 或者 添加 任务
     * @param request
     * @param mission
     * @param showStartTime
     * @param showEndTime
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateMission")
    @ResponseBody
    public String saveOrUpdateMission (HttpServletRequest request,ReMission mission,
                                       @RequestParam(value = ParamConsts.showStartTime,required = true) String showStartTime,
                                       @RequestParam(value = ParamConsts.showEndTime,required = true) String showEndTime) throws Exception {

        return missionService.doSaveOrUpdateMission (request,mission,showStartTime,showEndTime);
    }

    /**
     * 添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/addMissionPage")
    public String gotoAddMissionPage (HttpServletRequest request) {

        List<ReMissionSort> sorts = sortService.getAllSort(request);
        request.setAttribute("sorts",sorts);

        return "add_mission";
    }

    /**
     * 任务详情
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/missionDetailPage")
    public String gotoMissionDetailPage (HttpServletRequest request,
                                         @RequestParam(value = ParamConsts.missionId,required = true) Integer missionId) {

        ReMission mission = missionService.getMissionById(missionId);

        List<ReMissionSort> sorts = sortService.getAllSort(request);

        Integer startTime = mission.getStartTime();
        Integer endTime = mission.getEndTime();
        String showStartTime = ElBase.fmtTime((long)startTime*1000L);
        String showEndTime = ElBase.fmtTime((long)endTime*1000L);

        request.setAttribute("mission",mission);
        request.setAttribute("sorts",sorts);
        request.setAttribute("start",showStartTime);
        request.setAttribute("end",showEndTime);

        return "mission_detail";
    }

    /**
     *
     * @param request
     * @param missionIds
     * @return
     */
    @RequestMapping(value = "/deleteMission")
    @ResponseBody
    public String deleteMission(HttpServletRequest request,
                                @RequestParam(value = ParamConsts.missionIds,required = true)String missionIds ) {

        return missionService.doDeleteMissions(request,missionIds);
    }

}
