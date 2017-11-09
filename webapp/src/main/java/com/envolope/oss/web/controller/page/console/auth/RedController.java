package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.ReFixedRed;
import com.envolope.oss.model.ReSort;
import com.envolope.oss.model.ReSortRed;
import com.envolope.oss.model.para.RedSelectParamVo;
import com.envolope.oss.model.vo.RedVo;
import com.envolope.oss.service.RedService;
import com.envolope.oss.service.RedSortService;
import com.envolope.oss.service.SortService;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.el.ElBase;
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
 * Created by Jan on 16/8/17.
 * 分类 红包
 */
@Controller
@RequestMapping(value = "/page/console/auth/red", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class RedController {

    @Autowired
    private RedService redService;
    @Autowired
    private RedSortService redSortService;


    /**
     * 跳转到任务列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/redList")
    public String gotoRedListPage(HttpServletRequest request){

        List<ReSort> sorts = redSortService.getAllReSorts(request);

        request.setAttribute("sorts",sorts);

        return "red_list";
    }

    /**
     * 分类红包数据
     * @param request
     * @param paramVo
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String getRedList(HttpServletRequest request,RedSelectParamVo paramVo) throws Exception {

        Integer pageSize = paramVo.getPageSize();

        if (StringUtils.isEmpty(pageSize)){
            pageSize = 10;
        }
        Integer pageNum = paramVo.getPageNum();
        if (StringUtils.isEmpty(pageNum)) {
            pageNum = 1;
        }

        Integer total = redService.getRedsListNum(paramVo);

        Integer totalPage = total / pageSize ;

        if (total % pageSize > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }

        List<ReSortRed> reds = redService.getRedsList (paramVo);

        Map<String,Object> map = new HashMap<>(3);
        map.put("reds",reds);
        map.put("pageNow",pageNum);
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);
    }

    /**
     *
     * @param request
     * @param redIds
     * @return
     */
    @RequestMapping(value = "/deleteSortRed")
    @ResponseBody
    public String deleteRed(HttpServletRequest request,
                                @RequestParam(value = ParamConsts.redIds,required = true)String redIds ) {

        return redService.doDeleteSortReds(request,redIds);
    }


    /**
     * 跳转到修改页面
     * @param request
     * @param redId
     * @return
     */
    @RequestMapping(value = "/updateRedPage")
    public String gotoUpdateRedPage(HttpServletRequest request,
                                        @RequestParam(value = ParamConsts.redId,required = true) Integer redId) {

        ReSortRed red = redService.getFixedRedById(redId);
        Long startTime = red.getStartTime();
        Long endTime = red.getEndTime();
        Long createTime = red.getCreateTime();

        String showStartTime = ElBase.fmtTime(startTime);
        String showEndTime = ElBase.fmtTime(endTime);
        String showCreateTime = ElBase.fmtTime(createTime);

        List<ReSort> sorts = redSortService.getAllReSorts(request);

        request.setAttribute("sorts",sorts);

        request.setAttribute("red",red);
        request.setAttribute("showStartTime",showStartTime);
        request.setAttribute("showEndTime",showEndTime);
        request.setAttribute("showCreateTime",showCreateTime);

        return "update_or_add_red";

    }

    /**
     * 添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/addSortRedPage")
    public String gotoAddRedPage (HttpServletRequest request) {

        List<ReSort> sorts = redSortService.getAllReSorts(request);

        request.setAttribute("sorts",sorts);

        return "update_or_add_red";
    }

    /**
     * 修改 或者 添加
     * @param request
     * @param red
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateRed")
    @ResponseBody
    public String saveOrUpdateRed (HttpServletRequest request, RedVo red) throws Exception {

        return redService.doSaveOrUpdateRed (request,red);
    }

    /**
     * 详情
     * @param request
     * @param redId
     * @return
     */
    @RequestMapping(value = "/redDetailPage")
    public String gotoRedDetailPage(HttpServletRequest request,
                                    @RequestParam(value = ParamConsts.redId,required = true) Integer redId){

        ReSortRed red = redService.getFixedRedById(redId);
        Long startTime = red.getStartTime();
        Long endTime = red.getEndTime();
        Long createTime = red.getCreateTime();

        String showStartTime = ElBase.fmtTime(startTime);
        String showEndTime = ElBase.fmtTime(endTime);
        String showCreateTime = ElBase.fmtTime(createTime);

        List<ReSort> sorts = redSortService.getAllReSorts(request);

        request.setAttribute("sorts",sorts);

        request.setAttribute("red",red);
        request.setAttribute("showStartTime",showStartTime);
        request.setAttribute("showEndTime",showEndTime);
        request.setAttribute("showCreateTime",showCreateTime);

        return "red_detail";

    }

}



