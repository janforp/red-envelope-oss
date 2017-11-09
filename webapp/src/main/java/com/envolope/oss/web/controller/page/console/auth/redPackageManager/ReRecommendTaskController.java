package com.envolope.oss.web.controller.page.console.auth.redPackageManager;

import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.ReRecommendMission;
import com.envolope.oss.model.para.RecommendTaskSelectParamVo;
import com.envolope.oss.model.vo.recommend.TaskListVo;
import com.envolope.oss.model.vo.recommend.VerifyPageVo;
import com.envolope.oss.service.excel.ExcelService;
import com.envolope.oss.service.redPackageManager.ReRecommendTaskService;
import com.envolope.oss.service.redPackageManager.RecommendService;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/11/7.
 * 高额任务
 */
@Controller
@RequestMapping(value = "/page/console/auth/recommendTask", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class ReRecommendTaskController {

    @Autowired
    private ReRecommendTaskService reRecommendTaskService;
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private ExcelService excelService;

    /**
     * 跳转到任务列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/recommendTaskList")
    public String gotoMissionList(HttpServletRequest request,
                                  @RequestParam(value = "missionId",required = true)Long missionId) {

        ReRecommendMission mission = recommendService.getMissionById(missionId);

        request.setAttribute("mission",mission);

        return "/console/redPackageManager/recommend_task";
    }

    /**
     * 获得任务列表 数据
     * @param request
     * @param paramVo   查询参数对象
     * @return
     */
    @RequestMapping(value = "/listByPageSize")
    @ResponseBody
    public String getList(HttpServletRequest request,RecommendTaskSelectParamVo paramVo) {

        paramVo = reRecommendTaskService.handleParamVo(paramVo);
        List<TaskListVo> tasks = reRecommendTaskService.doGetList(request, paramVo);
        Integer total = reRecommendTaskService.getListNum(request,paramVo);
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
        map.put("tasks",tasks);
        map.put("pageNow",pageNum);

        return JsonUtil.buildData(map);
    }

    /**
     * 删除任务
     * @param request
     * @param taskIds
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(HttpServletRequest request,
                         @RequestParam(value = "taskIds",required = true)String taskIds ) {

        return reRecommendTaskService.doDelete(request,taskIds);
    }

    /**
     * 跳转到审核页面
     * @param request
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/verifyPage")
    public String gotoVerifyPage(HttpServletRequest request,
                                 @RequestParam(value = "taskId",required = true)Long taskId){

        request.getSession().setAttribute("taskId",taskId);

        VerifyPageVo detail = reRecommendTaskService.getVerifyDetail(request,taskId);
        request.setAttribute("detail",detail);

        return "/console/redPackageManager/recommend_verify_page";

    }

    /**
     * 审核通过
     * @return
     */
    @RequestMapping(value = "/success")
    @ResponseBody
    public String verifySuccess(HttpServletRequest request){

        return reRecommendTaskService.verifySuccess(request);

    }

    /**
     * 批量审核未通过
     * @return
     */
    @RequestMapping(value = "/batchNot")
    @ResponseBody
    public String batchVerifyFail(HttpServletRequest request,
                                  @RequestParam(value = "taskIds",required = true)String taskIds,
                                  @RequestParam(value = "remarks",required = true)String remarks){

        return reRecommendTaskService.doNotBatchVerify(request,taskIds,remarks);
    }


    /**
     * 批量审核通过
     * @param request
     * @param taskIds       用&链接的id
     * @return
     */
    @RequestMapping(value = "/batchVerify")
    @ResponseBody
    public String batchVerify(HttpServletRequest request,
                              @RequestParam(value = "taskIds",required = true)String taskIds,
                              @RequestParam(value = "money",required = true)String money){

        return reRecommendTaskService.doBatchVerify(request,taskIds, new BigDecimal(money));
    }

    /**
     * 审核未通过
     * @return
     */
    @RequestMapping(value = "/fail")
    @ResponseBody
    public String verifyFail(HttpServletRequest request,
                             @RequestParam(value = "remarks",required = true)String remarks){

        return reRecommendTaskService.verifyFail(request,remarks);
    }



    /**
     * 把该任务的所有提交过的task导入excel
     * @param request
     * @param missionId
     * @param status 任务状态
     * @return
     */
    @RequestMapping(value = "/excel")
    @ResponseBody
    public String exportToExcel(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam(value = "missionId",required = true)Long missionId,
                                @RequestParam(value = "startDate",required = false)String startDate,
                                @RequestParam(value = "endDate",required = false)String endDate,
                                @RequestParam(value = "status",required = false)Integer status) throws WriteException, IOException, InstantiationException, IllegalAccessException {

        if (StringUtil.isEmpty(startDate)){
            startDate = null;
        }
        if (StringUtil.isEmpty(endDate)){
            endDate =null;
        }
        if (StringUtil.isEmpty(status)){
            status = null;
        }
        if (status == 5){//打印全部
            status = null;
        }
        return excelService.exportToExcel(request,response,missionId,startDate,endDate,status);
    }
}
