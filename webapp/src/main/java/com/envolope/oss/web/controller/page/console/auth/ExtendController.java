package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.model.ReCustomer;
import com.envolope.oss.model.ReCustomerExtendStep;
import com.envolope.oss.model.vo.NewExtentVo;
import com.envolope.oss.model.vo.ReCustomerExtendListVo;
import com.envolope.oss.service.ConsumerService;
import com.envolope.oss.service.ExtentService;
import com.envolope.oss.service.StepService;
import com.envolope.oss.util.JsonUtil;
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
 * Created by Jan on 16/7/14.
 * 推广
 */
@RequestMapping(value = "/page/console/auth/extend", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class ExtendController {


    @Autowired
    private ExtentService extentService;
    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private StepService stepService;
    /**
     * 推广 页
     * @param request
     * @return
     */
    @RequestMapping(value = "/extendPage")
    public String  gotoExtentPage (HttpServletRequest request) {

        return "extent";
    }

    /**
     * 获取 推广 列表
     * @param request
     * @param customerName
     * @param customerWx
     * @param status
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/getData")
    @ResponseBody
    public String gotoExtentListWithData (HttpServletRequest request,
                                          @RequestParam(value = ParamConsts.customerName,required = false)String customerName,
                                          @RequestParam(value = ParamConsts.type,required = false)Integer type,
                                          @RequestParam(value = ParamConsts.customerWx,required = false)String customerWx,
                                          @RequestParam(value = ParamConsts.status,required = false)Integer status,
                                          @RequestParam(value = ParamConsts.pageNum,required = false)Integer pageNum) {

        List<ReCustomerExtendListVo> list = extentService.getAllExtends(request,customerName,customerWx,status,pageNum,type);

        Integer total = extentService.getAllExtendsNum(request,customerName,customerWx,status,type);

        Integer totalPage = 1;
        if(total % ValueConsts.PAGE_SIZE == 0){
            totalPage = total / ValueConsts.PAGE_SIZE;
        }else {
            totalPage = total / ValueConsts.PAGE_SIZE + 1;
        }

        Map<String,Object> map = new HashMap<>(2);
        map.put("list",list);
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);
    }

    /**
     * 查看某个推广详情
     * @param request
     * @param id            推广id
     * @return
     */
    @RequestMapping(value = "/detail")
    public String gotoDetailPageWithData (HttpServletRequest request,
                                          @RequestParam(value = ParamConsts.id,required = true)Integer id){

        ReCustomerExtendListVo detail = extentService.getDetailById (request,id);
        List<ReCustomerExtendStep> steps = stepService.getStepsByExtendId(request,id);

        request.setAttribute("steps",steps);
        request.setAttribute("detail",detail);

        return "detail";
    }

    /**
     * 跳转到 新增推广 页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/addExtend")
    public String addNewExtend (HttpServletRequest request) {


        //只有已经添加的客户才能添加推广,且此客户的已有的推广都已经结束
        List<ReCustomer> customWxs = consumerService.getCustomWxsByStatus(request);
        request.setAttribute("customs",customWxs);

        return "addExtend";
    }

    /**
     * 新增推广
     * @param request
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String saveExtend (HttpServletRequest request, NewExtentVo vo) throws Exception {

        return  extentService.doSaveExtend(request,vo);
    }

    /**
     *
     * @param request
     * @param idsStr        要删除的推广的id 用&链接
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String deleteExtend (HttpServletRequest request,
                                @RequestParam(value = ParamConsts.idsStr,required = false)String  idsStr) {

        return extentService.doDeleteExtend (request,idsStr);
    }

    /**
     * 带着数据 跳转到 修改页面
     * @param request
     * @param id        推广id
     * @return
     */
    @RequestMapping("/updateExtendPage")
    public String updateExtendPage (HttpServletRequest request,
                                @RequestParam(value = ParamConsts.id,required = false)Integer id) {


        ReCustomerExtendListVo detail = extentService.getDetailById (request,id);

        request.setAttribute("detail",detail);

        return "update_extend";
    }

    /**
     * 修改 推广
     * @param request
     * @param vo
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateExtend (HttpServletRequest request,NewExtentVo vo) throws Exception {

        return extentService.doUpdateExtend(request,vo);
    }

}
