package com.envolope.oss.web.controller.page.console.auth.data_statistic;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.enums.ModuleType;
import com.envolope.oss.model.vo.data_statistics.ModelData;
import com.envolope.oss.model.vo.data_statistics.ModuleDetail;
import com.envolope.oss.service.data_statistic.MissionStatisticsTaskService;
import com.envolope.oss.service.data_statistic.ModuleDetailService;
import com.envolope.oss.service.data_statistic.ModuleStatisticsService;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.el.ElBase;
import com.envolope.oss.util.pager.PagerHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 2016/11/25.
 * 模块数据统计列表页
 */
@Controller
@RequestMapping(value = "/page/console/auth/data")
public class ModelDataController {

    @Autowired
    private ModuleStatisticsService modelDataService;
    @Autowired
    private ModuleDetailService moduleDetailService;
    @Autowired
    private MissionStatisticsTaskService missionStatisticsTaskService;

    /**
     * 模块数据统计按钮列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/modelList")
    public String modelListPage(HttpServletRequest request){

        long now = System.currentTimeMillis();
        String today = ElBase.fmtDay(now);
        String weekAgo = ElBase.fmtDay(now - 7* ValueConsts.DAY_MILLISECOND);
        request.setAttribute("today",today);
        request.setAttribute("weekAgo",weekAgo);

        return "/console/data_statistics/model-list";
    }

    /**
     * 根据模块ID查询数据
     * @param request
     * @param module
     * @return
     */
    @RequestMapping(value = "/model")
    public String searchDataByModel(HttpServletRequest request,
                                    @RequestParam(value = "module")Integer module,
                                    @RequestParam(value = "startDate")String startDate,
                                    @RequestParam(value = "endDate")String endDate) throws Exception {

        ModelData data = modelDataService.getData(module,startDate,endDate);

        request.setAttribute("vos",data);

        return "/console/data_statistics/data_time_scope";
    }

    /**
     * 查询<code>model</code>模块的<code>oneDate</code>的详情数据
     * @param request
     * @param module
     * @param oneDate
     * @return
     */
    @RequestMapping(value = "/dayDetail")
    public String searchDayDetail(HttpServletRequest request,
                                  @RequestParam(value = "module")Integer module,
                                  @RequestParam(value = "oneDate")String oneDate,
                                  @RequestParam(value = ParamConsts.pageNum, defaultValue = "1")int pageNum){

        List<ModuleDetail> details = moduleDetailService.getModuleDetails(module,oneDate,pageNum);

        request.setAttribute("title", ModuleType.getMissionName(module));
        request.setAttribute("date",oneDate);
        request.setAttribute("module",module);
        request.setAttribute("details",details);

        // 资金明细记录数
        int total = moduleDetailService.getNum(module,oneDate);

        int totalPage = total / ValueConsts.PAGE_SIZE;
        if (total % ValueConsts.PAGE_SIZE > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }

        // 分页控件
        String page = PagerHtml.buildHtml(totalPage, pageNum);

        request.setAttribute("page",page);

        return "/console/data_statistics/data_time_detail";
    }


    /**
     *
     * @param request
     * @param module
     * @param oneDate
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/dayDetailPage")
    @ResponseBody
    public String searchByPage(HttpServletRequest request,
                               @RequestParam(value = "module")Integer module,
                               @RequestParam(value = "oneDate")String oneDate,
                               @RequestParam(value = ParamConsts.pageNum, defaultValue = "1")int pageNum){

        List<ModuleDetail> details = moduleDetailService.getModuleDetails(module,oneDate,pageNum);
        int total = moduleDetailService.getNum(module,oneDate);

        int totalPage = total / ValueConsts.PAGE_SIZE;
        if (total % ValueConsts.PAGE_SIZE > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }
        String page = PagerHtml.buildHtml(totalPage, pageNum);
        Map<String,Object> map = new HashMap<>(3);
        map.put("details", details);
        map.put("page",page);

        return JsonUtil.buildData(map);
    }

    /**
     *
     * 手动统计某个日期的数据
     * @param oneDate
     */
    @RequestMapping(value = "/execute")
    @ResponseBody
    public void executeYesterday(HttpServletRequest request ,
                                   @RequestParam(value = "oneDate",defaultValue = "")String oneDate){

       missionStatisticsTaskService.executeYesterday(request,oneDate);
    }
}
