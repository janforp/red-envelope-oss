package com.envolope.oss.web.controller.page.console.auth.entrance;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.SizeConsts;
import com.envolope.oss.model.ReIntegralWall;
import com.envolope.oss.model.RePackageChannel;
import com.envolope.oss.model.dto.PageDto;
import com.envolope.oss.service.ChannelAndPackageService;
import com.envolope.oss.service.entrance.IntegralService;
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
 * Created by Jan on 16/10/12.
 * 积分墙管理
 */
@Controller
@RequestMapping(value = "/page/console/auth/integral", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class IntegralController {

    @Autowired
    private IntegralService integralService;
    @Autowired
    private ChannelAndPackageService channelAndPackageService;


    /**
     * 跳转到列表页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/integralList")
    public String gotoList(HttpServletRequest request,
                             @RequestParam(value = ParamConsts.pageNo, required = false) Integer pageNo) {


        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        PageDto pageDto = integralService.selectByPage(pageNo, SizeConsts.PAGE_SIZE_DEFAULT);

        request.setAttribute("pg", pageDto);

        List<String> channels = channelAndPackageService.getDistinctChannel();

        request.setAttribute("channels",channels);

        List<String > packages = channelAndPackageService.getDistinctPackage();

        request.setAttribute("packages",packages);

        List<RePackageChannel> packageChannels = channelAndPackageService.getPackageChannelList();

        request.setAttribute("packageChannels",packageChannels);


        return "console/deploy/wall-list";
    }


    /**
     * 查询信息
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
        ReIntegralWall wall = integralService.getWallById(id);
        if (wall != null) {

            return JsonUtil.buildData(wall);
        }
        return JsonUtil.buildError(requestContext.getMessage("记录不存在"));
    }


    /**
     * 保存
     * @param request
     * @param wall
     * @return
     */
    @RequestMapping(value = "/saveWall")
    @ResponseBody
    public String saveWall(HttpServletRequest request, ReIntegralWall wall) throws Exception {
        return integralService.save(request, wall);
    }

    /**
     * 删除
     * @param request
     * @param wallIds
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public String remove(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.ids, required = false) List<Integer> wallIds) {
        RequestContext requestContext = new RequestContext(request);
        if (wallIds == null || wallIds.isEmpty()) {
            return JsonUtil.buildError(requestContext.getMessage("common.error.required-select", new Object[]{"记录"}));
        }
        if (integralService.delete(wallIds)) {
            return JsonUtil.buildSuccess(requestContext.getMessage("msg.success"));
        }
        return JsonUtil.buildError(requestContext.getMessage("msg.fail"));
    }


}

