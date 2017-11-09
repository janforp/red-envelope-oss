package com.envolope.oss.web.controller.page.console.auth.redPackageManager;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.WxShareMission;
import com.envolope.oss.model.para.ShareMissionSelectParamVo;
import com.envolope.oss.service.redPackageManager.WxShareService;
import com.envolope.oss.util.JsonUtil;
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
 * Created by Jan on 16/10/28.
 */
@Controller
@RequestMapping(value = "/page/console/auth/share", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class WxShareController {

    @Autowired
    private WxShareService wxShareService;

    /**
     * 跳转到任务列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/shareList")
    public String gotoMissionList(HttpServletRequest request) {

        return "/console/redPackageManager/share_mission";
    }

    /**
     * 获得任务列表 数据
     * @param request
     * @param paramVo   查询参数对象
     * @return
     */
    @RequestMapping(value = "/listByPageSize")
    @ResponseBody
    public String getList(HttpServletRequest request,ShareMissionSelectParamVo paramVo) {

        paramVo = wxShareService.handleParamVo(paramVo);

        List<WxShareMission> missions = wxShareService.doGetList(request, paramVo);

        Integer total = wxShareService.getListNum(request,paramVo);

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
    public String gotoUpdatePage(HttpServletRequest request,
                                        @RequestParam(value = ParamConsts.missionId,required = true) Long missionId) {

        WxShareMission mission = wxShareService.getMissionById(missionId);

        request.setAttribute("mission",mission);
        request.setAttribute("update",1);

        return "/console/redPackageManager/add_or_update_share_mission";

    }

    /**
     * 修改 或者 添加 任务
     * @param request
     * @param mission
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateMission")
    @ResponseBody
    public String saveOrUpdate (HttpServletRequest request,WxShareMission mission) throws Exception {

        return wxShareService.doSaveOrUpdateMission (request,mission);
    }

    /**
     * 添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/addMissionPage")
    public String gotoAddPage (HttpServletRequest request) {

        request.setAttribute("add",1);

        return "/console/redPackageManager/add_or_update_share_mission";
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

        WxShareMission mission = wxShareService.getMissionById(missionId);
        request.setAttribute("detail",1);
        request.setAttribute("mission",mission);

        return "/console/redPackageManager/add_or_update_share_mission";
    }

    /**
     *
     * @param request
     * @param missionIds
     * @return
     */
    @RequestMapping(value = "/deleteMission")
    @ResponseBody
    public String delete(HttpServletRequest request,
                                @RequestParam(value = ParamConsts.missionIds,required = true)String missionIds ) {

        return wxShareService.doDeleteMissions(request,missionIds);
    }

    /**
     * 针对某个任务添加红包
     * @return
     */
    @RequestMapping(value = "/addRed")
    @ResponseBody
    public String doAddRed(HttpServletRequest request,WxShareMission mission){

        return wxShareService.doAddRed(request,mission);
    }

    /**
     * 关闭任务
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/endMission")
    @ResponseBody
    public String closeMission(HttpServletRequest request,Long missionId){

        return wxShareService.endMission(request,missionId);
    }

    /**
     * 打开任务
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/openMission")
    @ResponseBody
    public String openMission(HttpServletRequest request,Long missionId){

        return wxShareService.openMission(request,missionId);
    }

}
