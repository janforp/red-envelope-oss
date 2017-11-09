package com.envolope.oss.web.controller.page.console.auth.mission_manager;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.model.RePasswordRed;
import com.envolope.oss.service.mission.PasswordService;
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
 * Created by Jan on 16/11/21.
 * 口令红包
 */
@RequestMapping(value = "/page/console/auth/password", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class PasswordController {

    @Autowired
    private PasswordService passwordService;


    @RequestMapping(value = "/passwordPage")
    public String gotoAppSharePage(){

        return "/console/redPackageManager/password-mission-list";
    }

    /**
     * 用户资金明细数据
     *
     * @param request
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/passwordData")
    @ResponseBody
    public String moneyData(HttpServletRequest request,
                            @RequestParam(value = ParamConsts.pageNum, required = true)int pageNum) {

        // 资金明细记录数
        int total = passwordService.getNum(request);
        int totalPage = total / ValueConsts.PAGE_SIZE;
        if (total % ValueConsts.PAGE_SIZE > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }
        // 资金明细记录
        List<RePasswordRed> details = passwordService.getList(request, pageNum);

        // 分页控件
        String page = PagerHtml.buildHtml(totalPage, pageNum);

        Map<String,Object> map = new HashMap<>(3);
        map.put("details", details);
        map.put("page", page);

        return JsonUtil.buildData(map);
    }


    /**
     * 修改数据获取
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/modify")
    @ResponseBody
    public String modifyData(HttpServletRequest request,
                             @RequestParam(value = "missionId")Long missionId){
        System.out.println("****"+passwordService.modifyData(request,missionId));

        RePasswordRed mission = passwordService.modifyData(request,missionId);

        Map<String,Object> map = new HashMap<>(2);
        map.put("mission",mission);
        map.put("modify",1);

        System.out.println("***"+JsonUtil.buildData(map));
        return JsonUtil.buildData(map);
    }


    /**
     * 添加或修改
     * @param request
     * @param mission
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request,RePasswordRed mission){


        return passwordService.save(request,mission);
    }
}
