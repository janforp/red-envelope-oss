package com.envolope.oss.web.controller.page.console.auth.mission_manager;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReAppKeywordsDAO;
import com.envolope.oss.model.*;
import com.envolope.oss.model.para.DemoSelectParam;
import com.envolope.oss.model.vo.mission.AppKeywordList;
import com.envolope.oss.model.vo.mission.KeywordListVo;
import com.envolope.oss.model.vo.mission.ParseData;
import com.envolope.oss.service.integralWall.IntegralWallService;
import com.envolope.oss.service.integralWall.LabelService;
import com.envolope.oss.service.mission.DemoMissionService;
import com.envolope.oss.service.mission.ParseHtmlService;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.pager.PagerHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 2016/11/22.
 * 试玩任务
 */
@RequestMapping(value = "/page/console/auth/demo", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class DemoMissionController {


    @Autowired
    private DemoMissionService demoMissionService;
    @Autowired
    private IntegralWallService integralWallService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private ParseHtmlService parseHtmlService;

    @RequestMapping(value = "/demoPage")
    public String gotoPage(HttpServletRequest request){

        List<ReAppMarket> markets = integralWallService.getMarketList();
        request.setAttribute("markets", markets);

        return "/console/mission-management/keyword/keyword-list";
    }

    /**
     * 用户资金明细数据
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/demoData")
    @ResponseBody
    public String moneyData(HttpServletRequest request, DemoSelectParam param) {

        param = demoMissionService.handleParam(param);
        // 资金明细记录数
        int total = demoMissionService.getNum(request,param);
        int totalPage = total / ValueConsts.PAGE_SIZE;
        if (total % ValueConsts.PAGE_SIZE > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }
        // 资金明细记录
        List<KeywordListVo> details = demoMissionService.getList(request, param);

        // 分页控件
        String page = PagerHtml.buildHtml(totalPage, param.getPageNum());

        Map<String,Object> map = new HashMap<>(3);
        map.put("details", details);
        map.put("page", page);

        return JsonUtil.buildData(map);
    }

    /**
     * 添加任务页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/toAddPage")
    public String gotoUpdatePage(HttpServletRequest request){

        request.setAttribute("add",1);
        List<ReAppMarket> markets = integralWallService.getMarketList();

        List<ReTaskLabel> labels = labelService.getAll();
        request.setAttribute("labels",labels);

        request.setAttribute("markets", markets);
        return "/console/mission-management/keyword/add-keyword";
    }


    /**
     * 解析地址
     * @param request
     * @param url
     * @param marketId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/pareUrl")
    @ResponseBody
    public String parseUrlToGetData(HttpServletRequest request,
                                    @RequestParam(value = ParamConsts.url,required = true)String url,
                                    @RequestParam(value = "marketId",required = true)Integer marketId) throws IOException {


        ParseData mission = parseHtmlService.parseHtmlToGetData(request,url,marketId);

        mission = demoMissionService.handleNullData(mission);

        Map<String,Object> map = new HashMap<>(1);

        map.put("mission",mission);

        return JsonUtil.buildData(map);

    }

    /**
     * 添加或修改关键词任务
     * @param request
     * @param data
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request,ParseData data){

        if (data.getKeywordId() == 0){
            data.setKeywordId(null);
        }
        return demoMissionService.save(request,data);
    }

    /**
     * 获得要修改到关键词任务到数据，带到页面
     * @param request
     * @param keywordId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/modifyData")
    public String modify(HttpServletRequest request,
                         @RequestParam(value = "keywordId",required = true)Long keywordId) throws IOException {

        ParseData data = demoMissionService.getModifyData(keywordId);

        if (data != null){
            request.setAttribute("word",data);

        }
        request.setAttribute("update",1);

        List<ReAppMarket> markets = integralWallService.getMarketList();
        request.setAttribute("markets", markets);

        List<ReTaskLabel> labels = labelService.getAll();
        request.setAttribute("labels",labels);

        return "/console/mission-management/keyword/add-keyword";

    }

    /**
     * 获得要修改到关键词任务到数据，带到页面
     * @param request
     * @param keywordId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/clone")
    public String clone(HttpServletRequest request,
                         @RequestParam(value = "keywordId",required = true)Long keywordId) throws IOException {

        ParseData data = demoMissionService.getModifyData(keywordId);

        if (data != null){
            request.setAttribute("word",data);

        }
        request.setAttribute("clone",1);

        List<ReAppMarket> markets = integralWallService.getMarketList();
        request.setAttribute("markets", markets);

        List<ReTaskLabel> labels = labelService.getAll();
        request.setAttribute("labels",labels);

        return "/console/mission-management/keyword/add-keyword";

    }
}
