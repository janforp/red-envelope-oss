package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.ReFixedRed;
import com.envolope.oss.service.FixedRedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/8/16.
 * 定时红包
 */
@Controller
@RequestMapping(value = "/page/console/auth/red", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class FixedRedController {

    @Autowired
    private FixedRedService fixedRedService;


    /**
     * 跳转到任务列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/fixedRedList")
    public String gotoFixedRedListPage(HttpServletRequest request) {

        List<ReFixedRed> reds = fixedRedService.getAllFixedReds (request);

        request.setAttribute("reds",reds);

        return "fixed_red_list";
    }

    /**
     *
     * @param request
     * @param fixIds
     * @return
     */
    @RequestMapping(value = "/deleteFixedRed")
    @ResponseBody
    public String deleteFixedRed(HttpServletRequest request,
                                @RequestParam(value = ParamConsts.fixIds,required = true)String fixIds ) {

        return fixedRedService.doDeleteFixedReds(request,fixIds);
    }


    /**
     * 跳转到修改页面
     * @param request
     * @param fixId
     * @return
     */
    @RequestMapping(value = "/updateFixedRedPage")
    public String gotoUpdateFixedRedPage(HttpServletRequest request,
                                        @RequestParam(value = ParamConsts.fixId,required = true) Integer fixId) {

        ReFixedRed red = fixedRedService.getFixedRedById(fixId);
        request.setAttribute("red",red);

        return "update_or_add_fixed_red";

    }

    /**
     * 添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/addFixedRedPage")
    public String gotoFixedRedAdPage (HttpServletRequest request) {

        return "update_or_add_fixed_red";
    }

    /**
     * 修改 或者 添加
     * @param request
     * @param red
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateFixedRed")
    @ResponseBody
    public String saveOrUpdateFixedRed (HttpServletRequest request,ReFixedRed red) throws Exception {

        return fixedRedService.doSaveOrUpdateFixedRed (request,red);
    }

}



