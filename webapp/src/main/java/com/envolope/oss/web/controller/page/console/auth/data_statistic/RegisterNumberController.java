package com.envolope.oss.web.controller.page.console.auth.data_statistic;

import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.vo.user.statistics.StatisticVo;
import com.envolope.oss.service.data_statistic.RegisterNumberService;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jan on 16/11/11.
 * 用户注册统实时计(折线图)
 */
@Controller
@RequestMapping(value = "/page/console/auth/register", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class RegisterNumberController {
    @Autowired
    private RegisterNumberService registerNumberService;

    /**
     * 数据实时统计
     * @param request
     * @return
     */
    @RequestMapping(value = "/statistic")
    public String dataStatistic(HttpServletRequest request,
                                @RequestParam(value = "type")Integer type,
                                @RequestParam(value = "date")String date){

        StatisticVo vo = registerNumberService.getDataByDay(request,type,date);

        System.out.println("********"+vo.getJqPlotArray());
        request.setAttribute("data",vo);
        return "/console/user-management/data_statistics";
    }

    /**
     * 获取折线图数据
     * @param request
     * @param type      数据类型,1:注册数据,2:活跃数据。。。。
     * @param date      日期
     * @return
     */
    @RequestMapping(value = "/getChart")
    @ResponseBody
    public String getDataOfChart(HttpServletRequest request,
                                 @RequestParam(value = "type")Integer type,
                                 @RequestParam(value = "date")String date){

        StatisticVo vo = registerNumberService.getDataByDay(request,type,date);

        String result = JsonUtil.buildData(vo);
        System.out.println("#########"+result+"$$$$$$$$$$");

        return JsonUtil.buildData(vo);
    }
}
