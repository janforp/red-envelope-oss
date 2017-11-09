package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.SizeConsts;
import com.envolope.oss.dao.RePackageChannelDAO;
import com.envolope.oss.model.*;
import com.envolope.oss.model.dto.PageDto;
import com.envolope.oss.service.BannerService;
import com.envolope.oss.service.ChannelAndPackageService;
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
 * Created by Jan on 16/8/12.
 * banner列表
 */
@Controller
@RequestMapping(value = "/page/console/auth/banner", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class BannerController {

    @Autowired
    private BannerService bannerService;
    @Autowired
    private ChannelAndPackageService channelAndPackageService;


    /**
     * 跳转到任务列表页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/bannerList")
    public String gotoAdList(HttpServletRequest request,
                             @RequestParam(value = ParamConsts.pageNo, required = false) Integer pageNo) {


        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        PageDto pageDto = bannerService.selectByPage(pageNo, SizeConsts.PAGE_SIZE_DEFAULT);

        request.setAttribute("pg", pageDto);

        List<String> channels = channelAndPackageService.getDistinctChannel();

        request.setAttribute("channels",channels);

        List<String > packages = channelAndPackageService.getDistinctPackage();

        request.setAttribute("packages",packages);

        List<RePackageChannel> packageChannels = channelAndPackageService.getPackageChannelList();

        request.setAttribute("packageChannels",packageChannels);


        return "console/deploy/banner-list";
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
        ReBanner banner = bannerService.getBannerById(id);
        if (banner != null) {

            return JsonUtil.buildData(banner);
        }
        return JsonUtil.buildError(requestContext.getMessage("记录不存在"));
    }


    /**
     * 保存
     * @param request
     * @param banner
     * @return
     */
    @RequestMapping(value = "/saveBanner")
    @ResponseBody
    public String saveBanner(HttpServletRequest request, ReBanner banner) throws Exception {
        return bannerService.save(request, banner);
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
        if (bannerService.delete(adIds)) {
            return JsonUtil.buildSuccess(requestContext.getMessage("msg.success"));
        }
        return JsonUtil.buildError(requestContext.getMessage("msg.fail"));
    }


}



