package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.vo.CommissionListVo;
import com.envolope.oss.model.vo.CommissionSelectParamVo;
import com.envolope.oss.service.CommissionService;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/9/27.
 * 佣金提现
 */
@Controller
@RequestMapping(value = "/page/console/auth/commission", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class CommissionController {

    @Autowired
    private CommissionService commissionService ;

    @RequestMapping(value = "/commissionWithdrawList")
    public String goCommissionListPage(HttpServletRequest request){

        return "commission_list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public String getCommissionList(HttpServletRequest request, CommissionSelectParamVo vo) {

        Integer pageSize = vo.getPageSize();

        if (StringUtils.isEmpty(pageSize)){
            pageSize = 10;
        }
        Integer pageNum = vo.getPageNum();
        if (StringUtils.isEmpty(pageNum)) {
            pageNum = 1;
        }

        Integer total = commissionService.getCommissionNum(vo);

        Integer totalPage = total / pageSize ;

        if (total % pageSize > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }

        List<CommissionListVo> details = commissionService.getCommissionList (vo);

        Map<String,Object> map = new HashMap<>(3);
        map.put("details",details);
        map.put("pageNow",pageNum);
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);
    }

    /**
     * 提现id
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/withdraw")
    @ResponseBody
    public String withdrawCommission(HttpServletRequest request,
                                     @RequestParam(value = "id")Long id) {

        return commissionService.doWithdrawCommission(request,id);
    }

    /**
     * 作废
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/invalid")
    @ResponseBody
    public String invalid(HttpServletRequest request,
                          @RequestParam(value = "id")Long id){

        return commissionService.doInvalid(request,id);
    }
}
