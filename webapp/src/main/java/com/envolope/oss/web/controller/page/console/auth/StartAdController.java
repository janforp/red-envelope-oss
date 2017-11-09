package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.SizeConsts;
import com.envolope.oss.model.ReDiscover;
import com.envolope.oss.model.ReStartAd;
import com.envolope.oss.model.dto.PageDto;
import com.envolope.oss.service.StartAdService;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/8/16.
 * 启动app的广告
 */
@Controller
@RequestMapping(value = "/page/console/auth/startAd", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class StartAdController {

    @Autowired
    private StartAdService startAdService;



    /**
     * 跳转到任务列表页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/startAdList")
    public String gotoAdList(HttpServletRequest request,
                                   @RequestParam(value = ParamConsts.pageNo, required = false) Integer pageNo) {


        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        PageDto pageDto = startAdService.selectByPage(pageNo, SizeConsts.PAGE_SIZE_DEFAULT);

        request.setAttribute("pg", pageDto);


        return "console/deploy/start_ad-list";
    }


    /**
     * 查询导航信息
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public String edit(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.id, required = false) Integer id){
        RequestContext requestContext = new RequestContext(request);
        if (id == null) {
            return JsonUtil.buildError(requestContext.getMessage("记录不存在"));
        }
        ReStartAd ad = startAdService.getDiscoverById(id);
        if (ad != null) {

            return JsonUtil.buildData(ad);
        }
        return JsonUtil.buildError(requestContext.getMessage("记录不存在"));
    }


    /**
     * 保存
     * @param request
     * @param ad
     * @return
     */
    @RequestMapping(value = "/saveAd")
    @ResponseBody
    public String saveAd(HttpServletRequest request, ReStartAd ad) throws Exception {
        return startAdService.save(request, ad);
    }


    /**
     * 删除
     * @param request
     * @param adIds
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public String remove(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.ids, required = false) List<Integer> adIds) {
        RequestContext requestContext = new RequestContext(request);
        if (adIds == null || adIds.isEmpty()) {
            return JsonUtil.buildError(requestContext.getMessage("common.error.required-select", new Object[]{"记录"}));
        }
        if (startAdService.delete(adIds)) {
            return JsonUtil.buildSuccess(requestContext.getMessage("msg.success"));
        }
        return JsonUtil.buildError(requestContext.getMessage("msg.fail"));
    }

}



