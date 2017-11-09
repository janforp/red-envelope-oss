package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.model.vo.UserListVo;
import com.envolope.oss.service.ClientService;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/7/15.
 *
 * 客户操作
 */
@Controller
@RequestMapping(value = "/page/console/auth/client", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class ClientController {

    @Autowired
    private ClientService clientService;

    /**
     * 跳转到关注人员 列表
     * @param request
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/extendDetail")
    public String gotoGetRedInfo (HttpServletRequest request,
                                  @RequestParam(value = ParamConsts.pageNum,defaultValue = "1") Integer pageNum) {

        String  name = clientService.getLoginNameFromRequest(request);

        List<UserListVo> records =clientService.getUserInfoOfLoginName(request,name,pageNum);

        Integer total = clientService.getUserInfoOfLoginNameNum(request,name);

        Integer totalPage = 1;
        if (total % ValueConsts.PAGE_SIZE == 0) {
            totalPage = total / ValueConsts.PAGE_SIZE ;
        }
        if (total % ValueConsts.PAGE_SIZE != 0) {
            totalPage = total / ValueConsts.PAGE_SIZE +1 ;
        }
        request.setAttribute("customerName",name);
        request.setAttribute("totalPage",totalPage);
        request.setAttribute("userManager",records);

        return "extendDetail";
    }

    /**
     * 翻页
     * @param request
     * @param pageNum
     * @param customerName
     * @return
     */
    @RequestMapping(value = "/next")
    @ResponseBody
    public String gotoGetRedInfo (HttpServletRequest request,
                                  @RequestParam(value = ParamConsts.pageNum,defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = ParamConsts.customerName,required = true) String customerName) {

        List<UserListVo> records =clientService.getUserInfoOfLoginName(request,customerName,pageNum);
        request.setAttribute("wx",customerName);
        request.setAttribute("userManager",records);

        Map<String,Object> map = new HashMap<>(2);
        map.put("wx",customerName);
        map.put("userManager",records);

        return JsonUtil.buildData(map);
    }


    /**
     * 查看今天的 列表
     * @param request
     * @param pageNum
     * @param customerName
     * @return
     */
    @RequestMapping(value = "/today")
    @ResponseBody
    public String getUserInfoToday (HttpServletRequest request,
                                  @RequestParam(value = ParamConsts.pageNum,defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = ParamConsts.customerName,required = true) String customerName) {

        List<UserListVo> records =clientService.getUserInfoTodayOfLoginName(request,customerName,pageNum);

        Integer total = clientService.getUserInfoTodayOfLoginName(request,customerName);
        Integer totalPage = 1;
        if (total % ValueConsts.PAGE_SIZE == 0) {
            totalPage = total / ValueConsts.PAGE_SIZE ;
        }
        if (total % ValueConsts.PAGE_SIZE != 0) {
            totalPage = total / ValueConsts.PAGE_SIZE +1 ;
        }

        Map<String,Object> map = new HashMap<>(3);
        map.put("customerName",customerName);
        map.put("userManager",records);
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);
    }

}
