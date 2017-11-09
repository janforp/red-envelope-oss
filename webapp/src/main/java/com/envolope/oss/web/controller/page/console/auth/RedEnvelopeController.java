package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.vo.AddRedPageShowVo;
import com.envolope.oss.model.vo.UnlockRedPageShowVo;
import com.envolope.oss.service.RedEnvelopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jan on 16/7/15.
 * 红包
 */
@RequestMapping(value = "/page/console/auth/red", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class RedEnvelopeController {

    @Autowired
    private RedEnvelopeService redEnvelopeService;


    /**
     * 添加红包页面
     * @param request
     * @param id    推荐id
     * @return
     */
    @RequestMapping(value = "/addRedPage")
    public String addRedPageWithData (HttpServletRequest request,
                                      @RequestParam(value = ParamConsts.id,required = true)Integer id) {

        AddRedPageShowVo vo =  redEnvelopeService.getAddRedCustomData(request,id);

        request.setAttribute("vo",vo);

        return "add_red";
    }


    /**
     * 添加红包
     * @param request
     * @param id  推广id
     * @param bigEnvelope   大额红包字符串
     * @param num           随机红包数
     * @return
     */
    @RequestMapping(value = "/addRed")
    @ResponseBody
    public String addRed (HttpServletRequest request,
                          @RequestParam(value = ParamConsts.id,required = true)Integer id,
                          @RequestParam(value = ParamConsts.num,required = true)Integer num,
                          @RequestParam(value = ParamConsts.bigEnvelope,defaultValue = "")String bigEnvelope) {

        return redEnvelopeService.doAddRed(request,id,num,bigEnvelope);
    }


    /**
     * 解锁红包页面
     * @param request
     * @param id    推荐id
     * @return
     */
    @RequestMapping(value = "/unlockRedPage")
    public String unlickRedPageWithData (HttpServletRequest request,
                                         @RequestParam(value = ParamConsts.id,required = true)Integer id) {

        UnlockRedPageShowVo vo =  redEnvelopeService.getUnlickRedCustomData(request,id);

        request.setAttribute("vo",vo);

        return "unlock_red";
    }

    /**
     * 解锁红包
     * @param request
     * @param id            推广id
     * @param num           解锁红包数
     * @return
     */
    @RequestMapping(value = "/unlockRed")
    @ResponseBody
    public String addRed (HttpServletRequest request,
                          @RequestParam(value = ParamConsts.id,required = true)Integer id,
                          @RequestParam(value = ParamConsts.num,required = true)Integer num) {

        return redEnvelopeService.doUnlockRed(request,id,num);
    }
}
