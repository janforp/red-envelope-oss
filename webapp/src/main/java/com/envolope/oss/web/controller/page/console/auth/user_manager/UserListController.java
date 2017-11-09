package com.envolope.oss.web.controller.page.console.auth.user_manager;

import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.vo.user.UserListDetailVo;
import com.envolope.oss.model.vo.user.UserSelectParamVo;
import com.envolope.oss.service.userManager.UserListService;
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
 * 用户列表 入口
 */
@Controller
@RequestMapping(value = "/page/console/auth/user", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class UserListController {

    @Autowired
    private UserListService userListService;

    /**
     * 用户列表
     * @return
     */
    @RequestMapping(value = "/userList")
    public String listPage(){

        return "/console/user-management/user_list";
    }

    /**
     * 兑换列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String getList(HttpServletRequest request, UserSelectParamVo paramVo){

        paramVo = userListService.handleParamVo(paramVo);

        Integer total = userListService.getNum(request,paramVo);

        Integer totalPage = total / paramVo.getPageSize() ;

        if (total % paramVo.getPageSize() > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }

        List<UserListDetailVo> details = userListService.getList(request,paramVo);

        Map<String,Object> map = new HashMap<>(3);
        map.put("details",details);
        map.put("pageNow",paramVo.getPageNum());
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);
    }

    /**
     * 拉黑用户
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/pullBlack")
    @ResponseBody
    public String pullBlack(HttpServletRequest request,Integer id){

        return userListService.doPullBlack(request,id);
    }
}
