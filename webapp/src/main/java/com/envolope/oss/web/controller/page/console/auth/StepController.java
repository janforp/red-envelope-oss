package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.ReCustomerExtendStep;
import com.envolope.oss.model.vo.ReCustomerExtendListVo;
import com.envolope.oss.service.ExtentService;
import com.envolope.oss.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/7/15.
 *
 * 步骤
 */
@RequestMapping(value = "/page/console/auth/step", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class StepController {

    @Autowired
    private StepService stepService;
    @Autowired
    private ExtentService extentService;

    /**
     * 推广id
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/stepPage")
    public String gotoStepPage (HttpServletRequest request,
                                @RequestParam(value = ParamConsts.id,required = false)Integer id) {

        List<ReCustomerExtendStep> steps = stepService.getStepsByExtendId(request,id);
        ReCustomerExtendListVo vo = extentService.getDetailById(request,id);

        request.setAttribute("name",vo.getCustomerName());
        request.setAttribute("id",id);
        request.setAttribute("list",steps);
        return "step";
    }

    /**
     * 保存或者添加步骤
     * @param request
     * @param stepsContent  各步骤的内容,个步骤用&分开
     * @param id            推广id
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String saveStep (HttpServletRequest request,
                            @RequestParam(value = ParamConsts.stepsContent,required = false)String stepsContent,
                            @RequestParam(value = ParamConsts.id,required = false)Integer id){

        return stepService.saveSteps (request,stepsContent,id);
    }
}
