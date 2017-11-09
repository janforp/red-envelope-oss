package com.envolope.oss.web.controller.page.console.auth.user_manager;

import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.model.ReScoreDetail;
import com.envolope.oss.model.vo.user.UserScoreVo;
import com.envolope.oss.service.userManager.ScoreRecordsService;
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
 * 金币记录
 */
@Controller
@RequestMapping(value = "/page/console/auth/score", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class ScoreRecordsController {

    @Autowired
    private ScoreRecordsService scoreRecordsService;


    /**
     * 用户列表
     * @param id 用户的id
     * @return
     */
    @RequestMapping(value = "/scoreList")
    public String listPage(HttpServletRequest request, Integer id){

        request.setAttribute("userId",id);

        UserScoreVo vo = scoreRecordsService.getScoreVo(id);

        request.setAttribute("vo",vo);

        return "/console/user-management/score_record";
    }

    /**
     * 兑换列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String getList(HttpServletRequest request,Integer userId,Integer pageNum){


        Integer total = scoreRecordsService.getNum(request,userId);

        Integer totalPage = total / ValueConsts.PAGE_SIZE;

        if (total % ValueConsts.PAGE_SIZE > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }

        List<ReScoreDetail> details = scoreRecordsService.getList(request,pageNum,userId);

        Map<String,Object> map = new HashMap<>(3);
        map.put("details",details);
        map.put("pageNow",pageNum);
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);
    }
}
