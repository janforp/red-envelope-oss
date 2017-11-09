package com.envolope.oss.web.controller.page.console.auth.mission_manager;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.model.ReRecommendMission;
import com.envolope.oss.model.ReRecommendMissionRequire;
import com.envolope.oss.model.ReTaskLabel;
import com.envolope.oss.model.vo.recommend.RecommendStep;
import com.envolope.oss.service.integralWall.LabelService;
import com.envolope.oss.service.mission.RegisterMissionService;
import com.envolope.oss.service.redPackageManager.RecommendService;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.pager.PagerHtml;
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
 * Created by Jan on 16/11/21.
 * 高额任务表中的注册任务
 */
@RequestMapping(value = "/page/console/auth/registerMission", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class RegisterMissionController {

    @Autowired
    private RegisterMissionService registerMissionService;
    @Autowired
    private LabelService labelService;


    @RequestMapping(value = "/registerMissionPage")
    public String gotoAppSharePage(){

        return "/console/mission-management/register-mission-list";
    }

    /**
     * 注册任务
     * @param request
     * @param pageNum
     * @param title 任务名称
     * @return
     */
    @RequestMapping(value = "/registerData")
    @ResponseBody
    public String moneyData(HttpServletRequest request,
                            @RequestParam(value = ParamConsts.pageNum, required = true)int pageNum,
                            @RequestParam(value ="title" , defaultValue = "")String title) {

        // 资金明细记录数
        int total = registerMissionService.getNum(request,title);
        int totalPage = total / ValueConsts.PAGE_SIZE;
        if (total % ValueConsts.PAGE_SIZE > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }
        List<ReRecommendMission> details = registerMissionService.getList(request, pageNum,title);

        String page = PagerHtml.buildHtml(totalPage, pageNum);

        Map<String,Object> map = new HashMap<>(3);
        map.put("details", details);
        map.put("page", page);

        return JsonUtil.buildData(map);
    }

    /**
     * 跳转到修改页面
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/updateMissionPage")
    public String gotoUpdatePage(HttpServletRequest request,
                                 @RequestParam(value = ParamConsts.missionId,required = true) Long missionId) {

        ReRecommendMission mission = registerMissionService.getMissionById(missionId);

        List<ReTaskLabel> labels = labelService.getAll();

        List<String> missionImgs = registerMissionService.getMissionImgs(missionId);

        request.setAttribute("missionImgs",missionImgs);
        request.setAttribute("mission",mission);
        request.setAttribute("labels",labels);
        request.setAttribute("update",1);

        return "/console/redPackageManager/add_or_update_register_mission";

    }

    /**
     * 修改 或者 添加 任务
     *
     * @param mission
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateMission")
    @ResponseBody
    public String saveOrUpdate (ReRecommendMission mission) throws Exception {
        return registerMissionService.doSaveOrUpdateMission(mission);
    }

    /**
     * 添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/addMissionPage")
    public String gotoAddPage (HttpServletRequest request) {

        request.setAttribute("add",1);
        List<ReTaskLabel> labels = labelService.getAll();
        request.setAttribute("labels",labels);

        return "/console/redPackageManager/add_or_update_register_mission";
    }

    /**
     * 任务详情
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/missionDetailPage")
    public String gotoDetailPage (HttpServletRequest request,
                                  @RequestParam(value = ParamConsts.missionId,required = true) Long missionId) {

        ReRecommendMission mission = registerMissionService.getMissionById(missionId);
        request.setAttribute("detail",1);
        request.setAttribute("mission",mission);
        List<String> missionImgs = registerMissionService.getMissionImgs(missionId);
        request.setAttribute("missionImgs",missionImgs);
        List<ReTaskLabel> labels = labelService.getAll();
        request.setAttribute("labels",labels);

        return "/console/redPackageManager/add_or_update_register_mission";
    }

    /**
     * 删除任务
     * @param request
     * @param missionIds
     * @return
     */
    @RequestMapping(value = "/deleteMission")
    @ResponseBody
    public String delete(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.missionIds,required = true)String missionIds ) {
        return registerMissionService.doDeleteMissions(missionIds);
    }

    /**
     * 推荐任务数量
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/addRed")
    @ResponseBody
    public String addRed(HttpServletRequest request, Long missionId, Integer num){
        return registerMissionService.doAddRed(missionId, num);
    }

    /**
     * 添加步骤页面
     * @return
     */
    @RequestMapping(value = "/addStepPage")
    public String addStepPage(HttpServletRequest request, Long missionId){

        request.getSession().setAttribute("missionId",missionId);

        ReRecommendMission mission = registerMissionService.getMissionById(missionId);

        request.setAttribute("title",mission.getMissionTitle());

        List<RecommendStep> steps = registerMissionService.getSteps(missionId);

        int stepStatus = 0;
        for (RecommendStep step : steps) {
            stepStatus = step.getStepStatus();
        }

        request.setAttribute("steps",steps);
        request.setAttribute("stepStatus", stepStatus);
        request.setAttribute("missionType",2);

        return "/console/redPackageManager/add_recommend_step";
    }

    /**
     * 添加步骤
     * @param request
     * @param totalStep
     * @return
     */
    @RequestMapping(value = "/addStep")
    @ResponseBody
    public String addStep(HttpServletRequest request,String totalStep){

        return registerMissionService.doAddStep(request,totalStep);
    }
}
