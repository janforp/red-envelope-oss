package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.service.DevelopeModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Jan on 16/8/4.
 *
 * 开发者模型
 */
@RequestMapping(value = "/page/console/auth/develop", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class DevelopeModeController {

    @Autowired
    private DevelopeModeService developeModeService;

    /**
     * 设置 公众号 菜单
     * @param request
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/setMenu")
    @ResponseBody
    public String setMenu(HttpServletRequest request,
                          @RequestParam(value = ParamConsts.customerId,required = false)Integer customerId) throws IOException {

        return developeModeService.doSetMenu(customerId);

    }


    /**
     * 设置 公众号 自动回复配置
     * @param request
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/setAutoReply")
    @ResponseBody
    public String setAutoReply(HttpServletRequest request,
                               @RequestParam(value = ParamConsts.customerId,required = false)Integer customerId) throws IOException {

        return developeModeService.doSaveAutoReply(customerId);

    }



    /**
     * 清空 公众号 自动回复配置
     * @param request
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/clearAutoReply")
    @ResponseBody
    public String clearAutoReply(HttpServletRequest request,
                                 @RequestParam(value = ParamConsts.customerId,required = false)Integer customerId) throws IOException {

        return developeModeService.clearAutoReply(customerId);

    }


    /**
     * 重置 自动回复配置 缓存
     * @param request
     * @return
     */
    @RequestMapping(value = "/rebuildAutoReply")
    @ResponseBody
    public String rebuildAutoReply(HttpServletRequest request) throws IOException {

        return developeModeService.rebuildAutoReply();

    }


    /**
     * 重新初始化微信公众号配置缓存Map
     * @param request
     * @return
     */
    @RequestMapping(value = "/rebuildWxCache")
    @ResponseBody
    public String rebuildWxCache(HttpServletRequest request) throws IOException {

        return developeModeService.rebuildWxCache();

    }



}
