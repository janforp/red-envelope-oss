package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.model.ReCustomer;
import com.envolope.oss.model.vo.ReCustomerListVo;
import com.envolope.oss.service.ConsumerService;
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
 * Created by Jan on 16/7/13.
 *
 * 客户列表
 */
@Controller
@RequestMapping(value = "/page/console/auth/Customer", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    /**
     * 查找 所有的客户 列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/customerList")
    public String gotoCustomePage (HttpServletRequest request) {


        return "custom_list";
    }

    @RequestMapping(value = "/developModeList")
    public String gotoDevelopModeListPage (HttpServletRequest request) {


        return "develop_mode_list";
    }







    /**
     * 查找 所有的客户 列表
     * @param request
     * @param customerName  公众号名称
     * @param customerWx    微信号
     * @return
     */
    @RequestMapping(value = "/getData")
    @ResponseBody
    public String gotoCustomePageWithData (HttpServletRequest request,
                                           @RequestParam(value = ParamConsts.customerName,required = false)String customerName,
                                           @RequestParam(value = ParamConsts.customerWx,required = false)String customerWx,
                                           @RequestParam(value = ParamConsts.pageNum,required = false)Integer pageNum,
                                           @RequestParam(value = ParamConsts.type,required = true)Integer type) {

        List<ReCustomerListVo> list = consumerService.getAllCustomers(request,customerName,customerWx,pageNum,type);

        //考虑分页
        Integer total = consumerService.getAllCustomersNum(request,customerName,customerWx,type);
        if (total == null){

            total=0;
        }

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
     * 查找 开发者模式的 公众号 列表
     * @param request
     * @param customerName  公众号名称
     * @param customerWx    微信号
     * @return
     */
    @RequestMapping(value = "/developModeListByPageSize")
    @ResponseBody
    public String getDevelopModeList (HttpServletRequest request,
                                   @RequestParam(value = ParamConsts.customerName,required = false)String customerName,
                                   @RequestParam(value = ParamConsts.customerWx,required = false)String customerWx,
                                   @RequestParam(value = ParamConsts.pageNum,defaultValue = "1")Integer pageNum,
                                   @RequestParam(value = ParamConsts.pageSize,defaultValue = "15")Integer pageSize,
                                   @RequestParam(value = ParamConsts.type,defaultValue = "1")Integer type,
                                   @RequestParam(value = ParamConsts.mode,defaultValue = "1")Integer mode) {

        List<ReCustomerListVo> list = consumerService.getDevelopModeCustomers(request,customerName,customerWx,pageNum,pageSize,type,mode);

        //考虑分页
        Integer total = consumerService.getDevelopModeCustomersNum(request,customerName,customerWx,type,mode);
        if (total == null){

            total=0;
        }

        Integer totalPage = 1;
        if(total % pageSize == 0){
            totalPage = total / pageSize;
        }else {
            totalPage = total / pageSize + 1;
        }

        Map<String,Object> map = new HashMap<>(2);
        map.put("list",list);
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);
    }


    /**
     * 添加客户
     * @param request
     * @param customer
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String saveCustom (HttpServletRequest request,ReCustomer customer){

        return  consumerService.doSaveCustom(request,customer);
    }

    /**
     * 获取要修改的客户的纪录
     * @param request
     * @param customerId
     * @return
     */
    @RequestMapping("/getUpdateData")
    @ResponseBody
    public String  getUpdateData(HttpServletRequest request,Integer customerId) {


        ReCustomerListVo vo = consumerService.doGetUpdateData (request,customerId);

        Map<String ,Object> map = new HashMap<>(1);
        map.put("vo",vo);

        return JsonUtil.buildData(map);

    }


    /**
     * 修改客户
     * @param request
     * @param customer
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateCustom (HttpServletRequest request,ReCustomer customer) {

        return  consumerService.doUpdateCustom(request,customer);
    }

    /**
     * 删除多个微信号
     * @param request
     * @param wxs
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String deleteCustoms (HttpServletRequest request,
                                 @RequestParam(value = ParamConsts.customerIds,required = true)String wxs){
        return consumerService.doDeleteCustoms(request,wxs);
    }

}
