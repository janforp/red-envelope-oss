package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.ReCodeRed;
import com.envolope.oss.model.para.CodeRedSelectParamVo;
import com.envolope.oss.model.vo.CodeRedVo;
import com.envolope.oss.service.CodeRedService;
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
 * Created by Jan on 16/8/24.
 * 兑换码红包
 */
@Controller
@RequestMapping(value = "/page/console/auth/red", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class CodeRedController {

    @Autowired
    private CodeRedService codeRedService ;


    /**
     * 兑换码红包页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/codeRedList")
    public String gotoRedListPage(HttpServletRequest request){
        return "code_red_list";
    }


    /**
     * 兑换码红包数据
     * @param request
     * @param paramVo
     * @return
     */
    @RequestMapping(value = "/listCodeRed")
    @ResponseBody
    public String getRedList(HttpServletRequest request, CodeRedSelectParamVo paramVo) throws Exception {

        Integer pageSize = paramVo.getPageSize();

        if (StringUtils.isEmpty(pageSize)){
            pageSize = 10;
        }
        Integer pageNum = paramVo.getPageNum();
        if (StringUtils.isEmpty(pageNum)) {
            pageNum = 1;
        }

        Integer total = codeRedService.getRedsListNum(paramVo);

        Integer totalPage = total / pageSize ;

        if (total % pageSize > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }

        List<ReCodeRed> reds = codeRedService.getRedsList (paramVo);

        Map<String,Object> map = new HashMap<>(3);
        map.put("reds",reds);
        map.put("pageNow",pageNum);
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);
    }


    /**
     * 跳转到修改页面
     * @param request
     * @param codeId
     * @return
     */
    @RequestMapping(value = "/updateCodeRedPage")
    public String gotoUpdateRedPage(HttpServletRequest request,
                                    @RequestParam(value = ParamConsts.codeId,required = true) Integer codeId) {

        ReCodeRed red = codeRedService.getCodeRedById(codeId);
        request.setAttribute("red",red);

        Long updateTime = red.getUpdateTime();
        String showUpdateTime = ElBase.fmtTime(updateTime);
        request.setAttribute("showUpdateTime",showUpdateTime);

        Long createTime = red.getCreateTime();
        String showCreateTime = ElBase.fmtTime(createTime);
        request.setAttribute("showCreateTime",showCreateTime);

        return "update_code_red";

    }


    /**
     * 添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/addCodeRedPage")
    public String gotoAddRedPage(HttpServletRequest request) {
        return "add_code_red";
    }


    /**
     * 修改
     * @param request
     * @param red
     * @return
     */
    @RequestMapping(value = "/updateCodeRed")
    @ResponseBody
    public String updateRed(HttpServletRequest request, CodeRedVo red) throws Exception {
        return codeRedService.doUpdateCodeRed (request,red);
    }


    /**
     * 新增兑换码红包
     * @param request
     * @param red
     * @return
     */
    @RequestMapping(value = "/addCodeRed")
    @ResponseBody
    public String addCodeRed(HttpServletRequest request,CodeRedVo red) throws Exception {
        return codeRedService.doAddCodeRed(request,red);
    }


    /**
     * 详情
     * @param request
     * @param codeId
     * @return
     */
    @RequestMapping(value = "/codeRedDetailPage")
    public String gotoRedDetailPage(HttpServletRequest request,
                                    @RequestParam(value = ParamConsts.codeId,required = true) Integer codeId){

        ReCodeRed red = codeRedService.getCodeRedById(codeId);

        Long updateTime = red.getUpdateTime();
        String showUpdateTime = ElBase.fmtTime(updateTime);

        Long createTime = red.getCreateTime();
        String showCreateTime = ElBase.fmtTime(createTime);

        request.setAttribute("red",red);
        request.setAttribute("showUpdateTime",showUpdateTime);
        request.setAttribute("showCreateTime",showCreateTime);

        return "code_red_detail";
    }


    /**
     * 针对某个 兑换码红包 增加他的 红包总数 页面
     * @param request
     * @param codeId
     * @return
     */
    @RequestMapping(value = "/addCodeRedNumPage")
    public String gotoAddRedNumPage(HttpServletRequest request,
                                    @RequestParam(value = ParamConsts.codeId,required = true) Integer codeId) {
        ReCodeRed red = codeRedService.getCodeRedById(codeId) ;
        request.setAttribute("red",red);
        return "add_code_red_num";
    }


    /**
     * 针对某个 兑换码红包 增加他的 红包总数
     * @param request
     * @param codeId
     * @return
     */
    @RequestMapping(value = "/addCodeRedNum")
    @ResponseBody
    public String addRedNum(HttpServletRequest request,
                            @RequestParam(value = ParamConsts.codeId,required = true) Integer codeId,
                            @RequestParam(value = ParamConsts.bigEnvelope,required = true) String bigEnvelope,
                            @RequestParam(value = ParamConsts.num,required = true) Integer num,
                            @RequestParam(value = ParamConsts.max,required = true) String max,
                            @RequestParam(value = ParamConsts.min,required = true) String min) {
        return codeRedService.doAddRedNum(request,codeId,bigEnvelope,num,max,min);
    }


    /**
     * 跳转到 解锁 红包页面
     * @param request
     * @param codeId
     * @return
     */
    @RequestMapping(value = "/unlockCodeRedNumPage")
    public String gotoUnlockRedPage(HttpServletRequest request,
                                    @RequestParam(value = ParamConsts.codeId,required = true) Integer codeId) {

        ReCodeRed red = codeRedService.getCodeRedById(codeId);

        request.setAttribute("red",red);

        return "unlock_code_red";
    }

    /**
     * 解锁红包
     * @param request
     * @param codeId
     * @param num
     * @return
     */
    @RequestMapping(value = "/unlockCodeRed")
    @ResponseBody
    public String UnlockCodeRed(HttpServletRequest request,
                                @RequestParam(value = ParamConsts.codeId, required = true) Integer codeId,
                                @RequestParam(value = ParamConsts.num, required = true) int num){

        return codeRedService.doUnlockCodeRed(request, codeId, num);
    }


}
