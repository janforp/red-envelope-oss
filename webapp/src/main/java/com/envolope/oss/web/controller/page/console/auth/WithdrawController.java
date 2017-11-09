package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.model.ReAccountDetail;
import com.envolope.oss.model.ReWithdrawDetail;
import com.envolope.oss.model.para.WithdrawSelectParamVo;
import com.envolope.oss.service.WithdrawService;
import com.envolope.oss.service.userManager.MoneyRecordsService;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.pager.PagerHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/8/19.
 * 提现
 */
@Controller
@RequestMapping(value = "/page/console/auth/withdraw", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService;
    @Autowired
    private MoneyRecordsService moneyRecordsService;

    /**
     * 提现列表 页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/withdrawList")
    public String withdrawList(HttpServletRequest request){
        return "console/withdraw-management/withdraw-list";
    }

    /**
     * 提现列表数据
     * @param request
     * @param paramVo
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String getWithdrawList(HttpServletRequest request, WithdrawSelectParamVo paramVo) {

        Integer pageSize = paramVo.getPageSize();
        if (StringUtils.isEmpty(pageSize)){
            pageSize = 10;
        }

        Integer pageNum = paramVo.getPageNum();
        if (StringUtils.isEmpty(pageNum)) {
            pageNum = 1;
        }

        Integer total = withdrawService.getWithdrawNum(paramVo);
        Integer totalPage = total / pageSize ;
        if (total % pageSize > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }

        List<ReWithdrawDetail> details = withdrawService.getWithdrawList(paramVo);

        // 分页控件
        String page = PagerHtml.buildHtml(totalPage, pageNum);

        Map<String,Object> map = new HashMap<>(2);
        map.put("details", details);
        map.put("page", page);

        return JsonUtil.buildData(map);
    }


    /**
     * 用户资金明细 页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/moneyList")
    public String moneyList(HttpServletRequest request,
                            @RequestParam(value = ParamConsts.userId, required = true)Integer userId){
        request.setAttribute("userId", userId);
        return "console/withdraw-management/money-list";
    }


    /**
     * 用户资金明细数据
     *
     * @param request
     * @param pageNum
     * @param userId
     * @return
     */
    @RequestMapping(value = "/moneyData")
    @ResponseBody
    public String moneyData(HttpServletRequest request,
                            @RequestParam(value = ParamConsts.pageNum, required = true)int pageNum,
                            @RequestParam(value = ParamConsts.userId, required = true)Integer userId) {

        // 资金明细记录数
        int total = moneyRecordsService.getNum(request, userId);
        int totalPage = total / ValueConsts.PAGE_SIZE;
        if (total % ValueConsts.PAGE_SIZE > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }

        // 分页控件
        String page = PagerHtml.buildHtml(totalPage, pageNum);

        // 资金明细记录
        List<ReAccountDetail> details = moneyRecordsService.getList(request, pageNum, userId);

        Map<String,Object> map = new HashMap<>(3);
        map.put("details", details);
        map.put("page", page);

        return JsonUtil.buildData(map);
    }


    /**
     * 作废
     * @param request
     * @param detailId
     * @return
     */
    @RequestMapping(value = "/invalid")
    @ResponseBody
    public String invalid(HttpServletRequest request,
                          @RequestParam(value = ParamConsts.detailId,required = true)Long detailId,
                          @RequestParam(value = "remarks")String remarks) {
        return withdrawService.doInvalid(detailId, remarks);
    }

    /**
     * 提现
     * @param request
     * @param detailId
     * @return
     */
    @RequestMapping(value = "/withdraw")
    @ResponseBody
    public String withdraw(HttpServletRequest request,
                           @RequestParam(value = ParamConsts.detailId,required = true)Long detailId ) throws Exception {
        return withdrawService.doWithdraw(request,detailId);
    }
}



