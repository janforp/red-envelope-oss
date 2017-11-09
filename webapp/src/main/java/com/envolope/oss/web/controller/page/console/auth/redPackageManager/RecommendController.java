package com.envolope.oss.web.controller.page.console.auth.redPackageManager;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.*;
import com.envolope.oss.model.para.RecommendSelectParamVo;
import com.envolope.oss.model.vo.recommend.RecommendStep;
import com.envolope.oss.service.integralWall.LabelService;
import com.envolope.oss.service.redPackageManager.RecommendService;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
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
 * Created by Jan on 16/10/31.
 * 推荐任务
 */
@Controller
@RequestMapping(value = "/page/console/auth/recommend", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class RecommendController {

    @Autowired
    private RecommendService recommendService ;
    @Autowired
    private LabelService labelService ;


    /**
     * 跳转到任务列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/recommendList")
    public String gotoMissionList(HttpServletRequest request) {
        return "/console/redPackageManager/recommend_mission";
    }


    /**
     * 获得任务列表 数据
     * @param request
     * @param paramVo   查询参数对象
     * @return
     */
    @RequestMapping(value = "/listByPageSize")
    @ResponseBody
    public String getList(HttpServletRequest request, RecommendSelectParamVo paramVo) {

        paramVo = recommendService.handleParamVo(paramVo);

        List<ReRecommendMission> missions = recommendService.doGetList(paramVo);

        Integer total = recommendService.getListNum(paramVo);

        Integer totalPage = total / paramVo.getPageSize() ;

        if (total % paramVo.getPageSize() > 0) {
            totalPage = totalPage +1 ;
        }
        Integer pageNum = paramVo.getPageNum() ;
        if (total == 0) {
            totalPage = 0 ;
            pageNum = 0 ;
        }

        Map<String,Object> map = new HashMap<>(3);
        map.put("totalPage", totalPage);
        map.put("missions", missions);
        map.put("pageNow", pageNum);

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

        ReRecommendMission mission = recommendService.getMissionById(missionId);

        List<ReTaskLabel> labels = labelService.getAll();

        List<String> missionImgs = recommendService.getMissionImgs(missionId);

        request.setAttribute("missionImgs",missionImgs);
        request.setAttribute("mission",mission);
        request.setAttribute("labels",labels);
        request.setAttribute("update",1);

        List<ReRecommendMissionRequire> requires = recommendService.getAllRequires();
        request.setAttribute("requires",requires);

        //还要单独传:审核要求
        List<ReRecommendMissionRequire> oldRequires = recommendService.getAlreadyRequire(missionId);
        request.setAttribute("oldRequires",oldRequires);

        return "/console/redPackageManager/add_or_update_recommend_mission";

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

        List<ReRecommendMissionRequire> requires = recommendService.getAllRequires();
        request.setAttribute("requires",requires);

        return "/console/redPackageManager/add_or_update_recommend_mission";
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
        return recommendService.doSaveOrUpdateMission(mission);
    }






    /*新版的高额任务的任务添加／修改 start*/
    /**
     * 去添加页面
     * @param request
     * @return
     */
    @RequestMapping("/toAddPage")
    public String addPage(HttpServletRequest request){

        request.setAttribute("add",1);
        List<ReRecommendMissionType> types = recommendService.getTypes();
        request.setAttribute("types",types);
        return "/console/mission-management/high-reward-mission/edit-high-reward-mission";
    }

    /**
     * 修改
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping("/toUpdatePage")
    public String editPage(HttpServletRequest request,
                           @RequestParam(value = ParamConsts.missionId,required = true) Long missionId){

        request.setAttribute("update",1);
        request.setAttribute("missionId",missionId);
        ReRecommendMission object = recommendService.getMissionById(missionId);
        request.setAttribute("object",object);
        List<ReRecommendMissionType> types = recommendService.getTypes();
        request.setAttribute("types",types);
        return "/console/mission-management/high-reward-mission/edit-high-reward-mission";
    }

    /**
     * 修改 或者 添加 任务
     *
     * @param mission
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save (ReRecommendMission mission) throws Exception {
        return recommendService.doSaveOrUpdateMission(mission);
    }

    /**
     * 获取所有的类型
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAllFatherTypes")
    @ResponseBody
    public String getType(HttpServletRequest request){

        List<ReRecommendMissionType> types = recommendService.getTypes();
        Map<String,Object> map = new HashMap<>(3);
        map.put("types", types);
        return JsonUtil.buildData(map);
    }

    /**
     * 获取所有的类型
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSubTypeById")
    @ResponseBody
    public String getSubTypeById(HttpServletRequest request,
                                 @RequestParam(value = "id")Integer id){

        String subType = recommendService.getSubTypeById(id);
        return JsonUtil.buildData(subType);
    }

    /**
     * 获取高额任务可选的标签
     * @return
     */
    @RequestMapping(value = "/label")
    @ResponseBody
    public String getAllLabels(){

        List<ReTaskLabel> labels = labelService.getAll();
        Map<String ,Object> map = new HashMap<>(1);
        map.put("labels", labels);
        return JsonUtil.buildData(map);
    }

    /**
     * 获取审核需要提交的项目
     * @return
     */
    @RequestMapping(value = "/require")
    @ResponseBody
    public String getRequire(){

        List<ReRecommendMissionRequire> requires = recommendService.getAllRequires();
        Map<String ,Object> map = new HashMap<>(1);
        map.put("requires", requires);
        return JsonUtil.buildData(map);
    }

    /**
     * 设置要修改的项目的标签
     * @return
     */
    @RequestMapping(value = "/setLabel")
    @ResponseBody
    public String setLabel(@RequestParam(value = ParamConsts.missionId,required = true) Long missionId){

        String alreadyLabel = recommendService.getAlreadyLabel(missionId);
        List<ReTaskLabel> labels = labelService.getAll();

        Map<String ,Object> map = new HashMap<>(2);
        map.put("old",alreadyLabel);
        map.put("all",labels);
        return JsonUtil.buildData(map);
    }
    /**
     * 设置要修改的项目的审核要求
     * @return
     */
    @RequestMapping(value = "/setRequire")
    @ResponseBody
    public String setRequire(@RequestParam(value = ParamConsts.missionId,required = true) Long missionId){

        List<ReRecommendMissionRequire> alreadyRequire = recommendService.getAlreadyRequire(missionId);
        List<ReRecommendMissionRequire> allRequires = recommendService.getAllRequires();
        Map<String ,Object> map = new HashMap<>(2);
        map.put("old", alreadyRequire);
        map.put("all",allRequires);
        return JsonUtil.buildData(map);
    }

    /**
     * 设置要修改的项目的审核要求
     * @return
     */
    @RequestMapping(value = "/setType")
    @ResponseBody
    public String setType(@RequestParam(value = ParamConsts.missionId,required = true) Long missionId){

        ReRecommendMission mission = recommendService.getMissionById(missionId);

        String old = mission.getSubTypeName();
        String all = recommendService.getSubTypeById(mission.getTypeId());
        Map<String ,Object> map = new HashMap<>(2);
        if (!StringUtil.isEmpty(old)){
            map.put("all",all);
        }else {
            map.put("all","");
        }
        if (!StringUtil.isEmpty(old)){
            map.put("old", old);
        }else {
            map.put("old", "");
        }
        return JsonUtil.buildData(map);
    }



     /*新版的高额任务的任务添加／修改 over*/


    /**
     * 任务详情
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/missionDetailPage")
    public String gotoDetailPage (HttpServletRequest request,
                                  @RequestParam(value = ParamConsts.missionId,required = true) Long missionId) {

        ReRecommendMission mission = recommendService.getMissionById(missionId);
        request.setAttribute("detail",1);
        request.setAttribute("mission",mission);
        List<String> missionImgs = recommendService.getMissionImgs(missionId);
        request.setAttribute("missionImgs",missionImgs);
        List<ReTaskLabel> labels = labelService.getAll();
        request.setAttribute("labels",labels);

        return "/console/redPackageManager/add_or_update_recommend_mission";
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
        return recommendService.doDeleteMissions(missionIds);
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
        return recommendService.doAddRed(missionId, num);
    }

    /**
     * 添加步骤页面
     * @return
     */
    @RequestMapping(value = "/addStepPage")
    public String addStepPage(HttpServletRequest request, Long missionId){

        request.getSession().setAttribute("missionId",missionId);

        ReRecommendMission mission = recommendService.getMissionById(missionId);

        request.setAttribute("title",mission.getMissionTitle());

        List<RecommendStep> steps = recommendService.getSteps(missionId);

        int stepStatus = 0;
        for (RecommendStep step : steps) {
            stepStatus = step.getStepStatus();
        }

        request.setAttribute("steps",steps);
        request.setAttribute("stepStatus", stepStatus);
        request.setAttribute("missionType",mission.getMissionType());

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
        return recommendService.doAddStep(request,totalStep);
    }
}
