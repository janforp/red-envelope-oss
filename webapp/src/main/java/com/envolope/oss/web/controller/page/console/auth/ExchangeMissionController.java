package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.config.Config;
import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.SizeConsts;
import com.envolope.oss.model.ReRecommendMission;
import com.envolope.oss.model.ReTaskLabel;
import com.envolope.oss.model.vo.recommend.RecommendStep;
import com.envolope.oss.service.ExchangeMissionService;
import com.envolope.oss.service.integralWall.LabelService;
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
 * 兑换码任务
 *
 * Created by Summer on 2016/11/8.
 */
@Controller
@RequestMapping(value = "/page/console/auth/exchange", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class ExchangeMissionController {

    @Autowired
    private LabelService labelService;
    @Autowired
    private ExchangeMissionService exchangeMissionService;
    @Autowired
    private RecommendService recommendService;

    /**
     * 任务列表 页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/exchangeList")
    public String exchangeList(HttpServletRequest request) {
        return "/console/mission-management/exchange-mission/mission-list";
    }

    /**
     * 获得任务列表 数据
     *
     * @param request
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/listByPageNum")
    @ResponseBody
    public String listByPageNum(HttpServletRequest request,
                                @RequestParam(value = ParamConsts.pageNum, required = false) Integer pageNo) {

        // 兑换码任务列表
        List<ReRecommendMission> details = exchangeMissionService.selectExchangeMissionList(pageNo);

        // 兑换码任务数
        int total = exchangeMissionService.selectExchangeMissionCount();
        int totalPage = total / SizeConsts.PAGE_SIZE_DEFAULT;
        if (total % SizeConsts.PAGE_SIZE_DEFAULT > 0) {
            totalPage = totalPage + 1;
        }
        int pageNum = pageNo;
        if (total == 0) {
            totalPage = 0 ;
            pageNum = 0 ;
        }

        // 兑换网页
        String exchangeUrl = Config.getRedBaseUrl() + "/c/p/code/page/";

        // 分页控件
        String page = PagerHtml.buildHtml(totalPage, pageNum);

        Map<String,Object> map = new HashMap<>(3);
        map.put("details", details);
        map.put("page", page);
        map.put("exchangeUrl", exchangeUrl);

        return JsonUtil.buildData(map);
    }

    /**
     * 添加任务 页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/addMissionPage")
    public String addMissionPage(HttpServletRequest request) {
        // 标签列表
        List<ReTaskLabel> labels = labelService.getAll();
        request.setAttribute("add", 1);
        request.setAttribute("labels",labels);
        return "/console/mission-management/exchange-mission/add-or-update-mission";
    }

    /**
     * 修改任务 页面
     *
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/updateMissionPage")
    public String updateMissionPage(HttpServletRequest request,
                                    @RequestParam(value = ParamConsts.missionId,required = true) Long missionId) {

        // 任务详情
        ReRecommendMission mission = exchangeMissionService.selectMissionById(missionId);
        // 标签列表
        List<ReTaskLabel> labels = labelService.getAll();
        request.setAttribute("mission", mission);
        request.setAttribute("labels", labels);
        request.setAttribute("update", 1);
        return "/console/mission-management/exchange-mission/add-or-update-mission";
    }


    /**
     * 保存任务
     *
     * @param request
     * @param mission
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request, ReRecommendMission mission) throws Exception {
        return exchangeMissionService.save(mission);
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
        return exchangeMissionService.deleteMissions(missionIds);
    }


    /**
     * 任务详情 页面
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/missionDetailPage")
    public String missionDetailPage(HttpServletRequest request,
                                    @RequestParam(value = ParamConsts.missionId,required = true) Long missionId) {
        // 任务详情
        ReRecommendMission mission = exchangeMissionService.selectMissionById(missionId);
        // 标签列表
        List<ReTaskLabel> labels = labelService.getAll();
        request.setAttribute("detail",1);
        request.setAttribute("mission", mission);
        request.setAttribute("labels",labels);
        return "/console/mission-management/exchange-mission/add-or-update-mission";
    }


    /**
     * 添加任务数量
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/addNum")
    @ResponseBody
    public String addNum(HttpServletRequest request, Long missionId, Integer num){
        return recommendService.doAddRed(missionId, num);
    }


    /**
     * 添加步骤 页面
     * @return
     */
    @RequestMapping(value = "/addStepPage")
    public String addStepPage(HttpServletRequest request ,Long missionId){
        request.getSession().setAttribute("missionId", missionId);
        ReRecommendMission mission = recommendService.getMissionById(missionId);
        List<RecommendStep> steps = recommendService.getSteps(missionId);
        int stepStatus = 0;
        for (RecommendStep step : steps) {
            stepStatus = step.getStepStatus();
        }
        request.setAttribute("title", mission.getMissionTitle());
        request.setAttribute("missionType", mission.getMissionType());
        request.setAttribute("steps", steps);
        request.setAttribute("stepStatus", stepStatus);
        return "/console/redPackageManager/add_recommend_step";
    }


    /**
     * 保存步骤
     * @param request
     * @param totalStep
     * @return
     */
    @RequestMapping(value = "/addStep")
    @ResponseBody
    public String addStep(HttpServletRequest request,String totalStep){
        return recommendService.doAddStep(request, totalStep);
    }

}
