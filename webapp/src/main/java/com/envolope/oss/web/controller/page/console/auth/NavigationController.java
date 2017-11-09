package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.SizeConsts;
import com.envolope.oss.dao.RePackageChannelDAO;
import com.envolope.oss.model.ReChannel;
import com.envolope.oss.model.ReNavigation;
import com.envolope.oss.model.RePackage;
import com.envolope.oss.model.RePackageChannel;
import com.envolope.oss.model.dto.PageDto;
import com.envolope.oss.service.ChannelAndPackageService;
import com.envolope.oss.service.NavigationService;
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
 * Created by Jan on 16/8/15.
 * 导航 app上的
 */
@Controller
@RequestMapping(value = "/page/console/auth/navigation", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class NavigationController {

    @Autowired
    private NavigationService navigationService;
    @Autowired
    private ChannelAndPackageService channelAndPackageService;

    /**
     * 导航管理页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/navigationList")
    public String list(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.pageNo, required = false) Integer pageNo) {

        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        PageDto pageDto = navigationService.selectByPage(pageNo, SizeConsts.PAGE_SIZE_DEFAULT);
        request.setAttribute("pg", pageDto);

        List<String> channels = channelAndPackageService.getDistinctChannel();

        request.setAttribute("channels",channels);

        List<String > packages = channelAndPackageService.getDistinctPackage();

        request.setAttribute("packages",packages);

        List<RePackageChannel> packageChannels = channelAndPackageService.getPackageChannelList();

        request.setAttribute("packageChannels",packageChannels);

        return "console/deploy/navigation-list";
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
        ReNavigation navigation = navigationService.selectNavigation(id);
        if (navigation != null) {
            return JsonUtil.buildData(navigation);
        }
        return JsonUtil.buildError(requestContext.getMessage("记录不存在"));
    }


    /**
     * 保存导航
     * @param request
     * @param navigation
     * @return
     */
    @RequestMapping(value = "/saveNavigation")
    @ResponseBody
    public String saveNavigation(HttpServletRequest request, ReNavigation navigation) throws Exception {
        return navigationService.save(request, navigation);
    }


    /**
     * 删除导航
     * @param request
     * @param navigationIds
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public String remove(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.ids, required = false) List<Integer> navigationIds) {
        RequestContext requestContext = new RequestContext(request);
        if (navigationIds == null || navigationIds.isEmpty()) {
            return JsonUtil.buildError(requestContext.getMessage("common.error.required-select", new Object[]{"记录"}));
        }
        if (navigationService.delete(navigationIds)) {
            return JsonUtil.buildSuccess(requestContext.getMessage("msg.success"));
        }
        return JsonUtil.buildError(requestContext.getMessage("msg.fail"));
    }

}



