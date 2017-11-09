package com.envolope.oss.web.controller.page.console.auth.mission_manager;

import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.model.ReWelfare;
import com.envolope.oss.model.ReWelfareType;
import com.envolope.oss.model.para.WelfareSelectParamVo;
import com.envolope.oss.service.mission.WelfareService;
import com.envolope.oss.util.EntityUtil;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.pager.PagerHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Janita on 2016/12/12.
 * 福利
 */
@RequestMapping(value = "/page/console/auth/welfare", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class WelfareController {

    @Autowired
    private WelfareService welfareService;

    /**
     * 文章列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/welfarePage")
    public String goListPage(HttpServletRequest request){

        List<ReWelfareType> types = welfareService.getTypes();
        request.setAttribute("types",types);

        return "console/mission-management/welfare/welfare-list";
    }

    /**
     * 去添加文章页面
     * @param request
     * @return
     */
    @RequestMapping("/toAddPage")
    public String addPage(HttpServletRequest request){

        request.setAttribute("add",1);

        List<ReWelfareType> types = welfareService.getTypes();
        request.setAttribute("types",types);

        return "console/mission-management/welfare/edit-welfare";
    }

    /**
     * 修改
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/toUpdatePage")
    public String editPage(HttpServletRequest request,
                           @RequestParam(value = "id")Long id){

        ReWelfare welfare = welfareService.getWelfare(id);

        request.setAttribute("update",1);

        request.setAttribute("object",welfare);

        List<ReWelfareType> types = welfareService.getTypes();
        request.setAttribute("types",types);

        return "console/mission-management/welfare/edit-welfare";
    }


    /**
     * 列表查询
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request,WelfareSelectParamVo param){

        param = EntityUtil.emptyStringToNull(param);

        int total = welfareService.getNum(param);
        int totalPage = total / ValueConsts.PAGE_SIZE;
        if (total % ValueConsts.PAGE_SIZE > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }
        List<ReWelfare> welfare = welfareService.getList(param);
        // 分页控件
        String page = PagerHtml.buildHtml(totalPage, param.getPageNum());

        Map<String,Object> map = new HashMap<>(3);
        map.put("details", welfare);
        map.put("page", page);

        return JsonUtil.buildData(map);
    }
    /**
     * 保存修改／添加
     * @param request
     * @param welfare
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request, ReWelfare welfare){

        return welfareService.save(welfare);
    }

    /**
     * 获取所有的类型
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAllFatherTypes")
    @ResponseBody
    public String getType(HttpServletRequest request){

        List<ReWelfareType> types = welfareService.getTypes();
        Map<String,Object> map = new HashMap<>(3);
        map.put("types", types);
        return JsonUtil.buildData(map);
    }

    /**
     * 获取所有的类型
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSubTypeById")
    @ResponseBody
    public String getSubTypeById(HttpServletRequest request,
                                 @RequestParam(value = "id")Integer id){

        String subType = welfareService.getSubTypeById(id);
        return JsonUtil.buildData(subType);
    }

}
