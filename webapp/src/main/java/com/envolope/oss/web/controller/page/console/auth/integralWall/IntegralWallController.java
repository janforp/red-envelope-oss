package com.envolope.oss.web.controller.page.console.auth.integralWall;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.ReApp;
import com.envolope.oss.model.ReAppMarket;
import com.envolope.oss.model.vo.integralWall.AppListVo;
import com.envolope.oss.model.vo.integralWall.AppSelectParamVo;
import com.envolope.oss.service.integralWall.IntegralWallService;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/10/14.
 * 积分墙管理
 */
@Controller
@RequestMapping(value = "/page/console/auth/app", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class IntegralWallController {

    @Autowired
    private IntegralWallService integralWallService;

    /**
     * app列表
     * @return
     */
    @RequestMapping(value = "/appListPage")
    public String listPage(HttpServletRequest request){

        List<ReAppMarket> markets = integralWallService.getMarketList();

        request.setAttribute("markets", markets);

        return "/console/integralWall/app-list";
    }

    /**
     * 关键词列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String getList(HttpServletRequest request, AppSelectParamVo paramVo){

        paramVo = integralWallService.handleParamVo(paramVo);

        Integer total = integralWallService.getNum(request,paramVo);

        Integer totalPage = total / paramVo.getPageSize() ;

        if (total % paramVo.getPageSize() > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }

        List<AppListVo> details = integralWallService.getList(request,paramVo);

        Map<String,Object> map = new HashMap<>(3);
        map.put("details",details);
        map.put("pageNow",paramVo.getPageNum());
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);
    }


    /**
     * 查询
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public String edit(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.id, required = false) Long id){
        RequestContext requestContext = new RequestContext(request);
        if (id == null) {
            return JsonUtil.buildError(requestContext.getMessage("记录不存在"));
        }
        ReApp app = integralWallService.getAppById(id);
        if (app != null) {

            return JsonUtil.buildData(app);
        }
        return JsonUtil.buildError(requestContext.getMessage("记录不存在"));
    }


    /**
     * 保存
     * @param request
     * @param app
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request, ReApp app) throws Exception {
        return integralWallService.save(request, app);
    }


    /**
     * 删除
     * @param request
     * @param appIds
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public String remove(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.ids, required = false) List<Long> appIds) {
        RequestContext requestContext = new RequestContext(request);
        if (appIds == null || appIds.isEmpty()) {
            return JsonUtil.buildError(requestContext.getMessage("common.error.required-select", new Object[]{"记录"}));
        }
        if (integralWallService.delete(appIds)) {
            return JsonUtil.buildSuccess(requestContext.getMessage("msg.success"));
        }
        return JsonUtil.buildError(requestContext.getMessage("msg.fail"));
    }
}