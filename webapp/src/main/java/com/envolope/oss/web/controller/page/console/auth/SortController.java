package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.ReMissionSort;
import com.envolope.oss.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/8/11.
 * 分类管理
 */
@Controller
@RequestMapping(value = "/page/console/auth/sort", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class SortController {

    @Autowired
    private SortService sortService;


    @RequestMapping(value = "/sortList")
    public String gotoMissionSortPage(HttpServletRequest request){

        List<ReMissionSort> sorts = sortService.getAllSort(request);

        request.setAttribute("sorts",sorts);

        return "mission_sort";
    }

    /**
     * 跳转到修改分类页面
     * @param request
     * @param sortId
     * @return
     */
    @RequestMapping(value = "/updateSortPage")
    public String gotoUpdateSortPage (HttpServletRequest request,
                                      @RequestParam(value = ParamConsts.sortId,required = true)Integer sortId) {

        ReMissionSort sort = sortService.getSortById(sortId);
        request.setAttribute("sort",sort);

        return "update_sort";
    }

    /**
     * 保存修改
     * @param request
     * @param sort
     * @return
     */
    @RequestMapping(value = "/updateOrSaveSort")
    @ResponseBody
    public String updateSort(HttpServletRequest request,ReMissionSort sort) {

        return sortService.doUpdateSort(sort);
    }

    /**
     * 添加分类
     * @param request
     * @return
     */
    @RequestMapping(value = "/addSort")
    public String addSort(HttpServletRequest request) {

        return "add_sort";
    }

    @RequestMapping(value = "/deleteSort")
    @ResponseBody
    public String deleteSort(HttpServletRequest request,
                             @RequestParam(value = ParamConsts.sortIds,defaultValue = "")String sortIds ){

        return sortService.doDeleteSort(sortIds);
    }


}
