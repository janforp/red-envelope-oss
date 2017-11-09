package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.ReWithdrawSort;
import com.envolope.oss.service.WithdrawSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/8/18.
 *
 * 支付分类
 */
@Controller
@RequestMapping(value = "/page/console/auth/withdraw", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class WithdrawSortController {

    @Autowired
    private WithdrawSortService withdrawSortService;


    /**
     * 跳转到任务列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/withdrawSortList")
    public String gotoFixedRedListPage(HttpServletRequest request) {

        List<ReWithdrawSort> sorts = withdrawSortService.getAllWithdraws (request);

        request.setAttribute("sorts",sorts);

        return "withdraw_sort_list";
    }

    /**
     *
     * @param request
     * @param withdrawIds
     * @return
     */
    @RequestMapping(value = "/deleteWithdrawSort")
    @ResponseBody
    public String deleteFixedRed(HttpServletRequest request,
                                @RequestParam(value = ParamConsts.withdrawIds,required = true)String withdrawIds ) {

        return withdrawSortService.doDeleteWithdraws(request,withdrawIds);
    }


    /**
     * 跳转到修改页面
     * @param request
     * @param withdrawId
     * @return
     */
    @RequestMapping(value = "/updateWithdrawSortPage")
    public String gotoUpdateFixedRedPage(HttpServletRequest request,
                                        @RequestParam(value = ParamConsts.withdrawId,required = true) Integer withdrawId) {

        ReWithdrawSort sort = withdrawSortService.getWithdrawById(withdrawId);
        request.setAttribute("sort",sort);

        return "update_or_add_withdraw_sort";

    }

    /**
     * 添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/addWithdrawSortPage")
    public String gotoFixedRedAdPage (HttpServletRequest request) {

        return "update_or_add_withdraw_sort";
    }

    /**
     * 修改 或者 添加
     * @param request
     * @param sort
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateWithdrawSort")
    @ResponseBody
    public String saveOrUpdateFixedRed (HttpServletRequest request,ReWithdrawSort sort) throws Exception {

        return withdrawSortService.doSaveOrUpdateWithdraw (request,sort);
    }

}



