package com.envolope.oss.web.controller.page.console.auth.user_manager;

import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.model.ReAccountDetail;
import com.envolope.oss.model.vo.user.UserMoneyVo;
import com.envolope.oss.service.userManager.MoneyRecordsService;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/10/13.
 * 现金记录
 */
@Controller
@RequestMapping(value = "/page/console/auth/money", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class MoneyRecordsController {

    @Autowired
    private MoneyRecordsService moneyRecordsService;


    /**
     * 用户列表
     * @param id 用户的id
     * @return
     */
    @RequestMapping(value = "/moneyList")
    public String listPage(HttpServletRequest request, Integer id){

        request.setAttribute("userId",id);

        UserMoneyVo vo = moneyRecordsService.getMoneyVo(id);

        request.setAttribute("vo",vo);

        return "/console/user-management/money_record";
    }

    /**
     * 兑换列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String getList(HttpServletRequest request,Integer userId,Integer pageNum){


        Integer total = moneyRecordsService.getNum(request,userId);

        Integer totalPage = total / ValueConsts.PAGE_SIZE;

        if (total % ValueConsts.PAGE_SIZE > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }

        List<ReAccountDetail> details = moneyRecordsService.getList(request,pageNum,userId);

        Map<String,Object> map = new HashMap<>(3);
        map.put("details",details);
        map.put("pageNow",pageNum);
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);
    }
}
