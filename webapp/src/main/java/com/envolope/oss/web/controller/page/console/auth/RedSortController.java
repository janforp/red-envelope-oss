package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.ReSort;
import com.envolope.oss.model.ReStartAd;
import com.envolope.oss.service.RedSortService;
import com.envolope.oss.service.StartAdService;
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
 * 红包分类
 */
@Controller
@RequestMapping(value = "/page/console/auth/redSort", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class RedSortController {

    @Autowired
    private RedSortService redSortService;


    /**
     * 跳转到任务列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/redSortList")
    public String gotoRedSortListPage(HttpServletRequest request) {

        List<ReSort> sorts = redSortService.getAllReSorts (request);

        request.setAttribute("sorts",sorts);

        return "red_sort_list";
    }

    /**
     *
     * @param request
     * @param sortIds
     * @return
     */
    @RequestMapping(value = "/deleteRedSort")
    @ResponseBody
    public String deleteStartAd(HttpServletRequest request,
                                @RequestParam(value = ParamConsts.sortIds,required = true)String sortIds ) {

        return redSortService.doDeleteRedSorts(request,sortIds);
    }


    /**
     * 跳转到修改页面
     * @param request
     * @param sortId
     * @return
     */
    @RequestMapping(value = "/updateRedSortPage")
    public String gotoUpdateStartAdPage(HttpServletRequest request,
                                        @RequestParam(value = ParamConsts.sortId,required = true) Integer sortId) {

        ReSort sort = redSortService.getRedSortById(sortId);
        request.setAttribute("sort",sort);

        return "update_or_add_red_sort";

    }

    /**
     * 添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/addRedSortPage")
    public String gotoAddStartAdPage (HttpServletRequest request) {

        return "update_or_add_red_sort";
    }

    /**
     * 修改 或者 添加
     * @param request
     * @param sort
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateRedSort")
    @ResponseBody
    public String saveOrUpdateRedSort (HttpServletRequest request,ReSort sort) throws Exception {

        return redSortService.doSaveOrUpdateRedSort (request,sort);
    }

}



