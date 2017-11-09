package com.envolope.oss.service;

import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReCustomerDAO;
import com.envolope.oss.model.ReCustomer;
import com.envolope.oss.model.vo.ReCustomerListVo;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/7/13.
 *  * 客户列表
 */
@Service
public class ConsumerService {

    @Autowired
    private ReCustomerDAO reCustomerDAO;

    /**
     * 查找 所有的客户 列表
     * @param request
     * @param customerName  公众号名称
     * @param customerWx    微信号
     * @param pageNum       页码
     * @return
     */
    public List<ReCustomerListVo> getAllCustomers (HttpServletRequest request,String customerName, String customerWx,Integer pageNum,Integer type) {

        if (StringUtil.isEmpty(customerName)){
            customerName = null;
        }
        if (StringUtil.isEmpty(customerWx)){
            customerWx = null;
        }

        int offSet = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offSet, ValueConsts.PAGE_SIZE);
        List<ReCustomer> listRe =  reCustomerDAO.getAllCostumers(customerName,customerWx,type,bounds);
        List<ReCustomerListVo>  list = new ArrayList<>(listRe.size());

        for (ReCustomer customer : listRe) {

            ReCustomerListVo vo = turnReCustomerToReCustomerListVo(customer,type);

            list.add(vo);
        }


        return list;

    }

    /**
     * 查找 所有的客户 列表
     * @param request
     * @param customerName  公众号名称
     * @param customerWx    微信号
     * @param pageNum       页码
     * @return
     */
    public List<ReCustomerListVo> getDevelopModeCustomers
                                (HttpServletRequest request,String customerName,
                                 String customerWx,Integer pageNum,Integer pageSize,
                                 Integer type,Integer mode) {

        if (StringUtil.isEmpty(customerName)){
            customerName = null;
        }
        if (StringUtil.isEmpty(customerWx)){
            customerWx = null;
        }

        int offSet = (pageNum - 1) * pageSize;
        RowBounds bounds = new RowBounds(offSet, pageSize);
        List<ReCustomer> listRe =  reCustomerDAO.getAllDevelopModeCostumers(customerName,customerWx,type,mode,bounds);
        List<ReCustomerListVo>  list = new ArrayList<>(listRe.size());

        for (ReCustomer customer : listRe) {

            ReCustomerListVo vo = turnReCustomerToReCustomerListVo(customer,type);

            list.add(vo);
        }

        return list;
    }

    public ReCustomerListVo turnReCustomerToReCustomerListVo (ReCustomer customer,Integer type) {
        ReCustomerListVo vo = new ReCustomerListVo();

        vo.setCustomerId(customer.getCustomerId());

        vo.setCustomerType(type);
        vo.setCustomerSecret(customer.getCustomerSecret());
        if (type == 1) {
            vo.setCustomerTypeShow("公众号");
        }
        if(type == 0) {
            vo.setCustomerTypeShow("普通商户");
        }
        String wx = customer.getCustomerWx();
        if (wx == null || wx.trim().length() == 0) {
            wx = "";
        }
        vo.setCustomerWx(wx);
        String name = customer.getCustomerName();
        if (name == null || name.trim().length() == 0) {
            name = "";
        }
        vo.setCustomerName(name);
        String img = customer.getCustomerImg();
        if (img == null || img.trim().length() == 0) {
            img = "";
        }
        vo.setCustomerImg(img);
        String appid = customer.getCustomerAppid();
        if (appid == null || appid.trim().length() == 0) {
            appid = "";
        }
        vo.setCustomerAppid(appid);
        String secret = customer.getCustomerAppsecret();
        if (secret == null || secret.trim().length() == 0) {
            secret = "";
        }
        vo.setCustomerAppsecret(secret);
        String token = customer.getCustomerToken();
        if (token == null || token.trim().length() == 0) {
            token = "";
        }
        vo.setCustomerToken(token);
        String aeskey = customer.getCustomerAeskey();
        if (aeskey == null || aeskey.trim().length() == 0) {
            aeskey = "";
        }
        vo.setCustomerAeskey(aeskey);

        vo.setCustomerSendtype(customer.getCustomerSendtype());
        vo.setCreateTime(customer.getCreateTime());

        String showTime = ElBase.fmt10Time(customer.getCreateTime());
        vo.setShowTime(showTime);


        return vo;
    }
    /**
     * 满足要求的 客户列表的总数用于分页
     * @param request
     * @param customerName
     * @param customerWx
     * @return
     */
    public Integer getAllCustomersNum (HttpServletRequest request,String customerName, String customerWx,Integer type) {

        if (StringUtil.isEmpty(customerName)){
            customerName = null;
        }
        if (StringUtil.isEmpty(customerWx)){
            customerWx = null;
        }

        return reCustomerDAO.getAllCostumersNum(customerName,customerWx,type);
    }

    /**
     * 满足要求的 开发者模式 客户列表的总数用于分页
     * @param request
     * @param customerName
     * @param customerWx
     * @return
     */
    public Integer getDevelopModeCustomersNum (HttpServletRequest request,String customerName, String customerWx,Integer type,Integer mode) {

        if (StringUtil.isEmpty(customerName)){
            customerName = null;
        }
        if (StringUtil.isEmpty(customerWx)){
            customerWx = null;
        }

        return reCustomerDAO.getDevelopModeCostumersNum(customerName,customerWx,type,mode);
    }

    /**
     * 添加客户
     * @param request
     * @param customer
     * @return
     */
    public String doSaveCustom (HttpServletRequest request,ReCustomer customer) {

        Integer type = customer.getCustomerType();
        if (type == 0) {        //普通商户

            String name = customer.getCustomerName();
            if (StringUtil.isEmpty(name)) {
                return JsonUtil.buildError("商户名不能空");
            }

        }else if (type == 1){   //公众号

            //先判断此公众号是否已经添加过
            String wx = customer.getCustomerWx();
            if (wx != null){
                //
                ReCustomer alCust = reCustomerDAO.selectByWx(wx);
                if (alCust != null) {
                    return JsonUtil.buildError("此公众号已存在");
                }
            }


            String name = customer.getCustomerName();
            if (StringUtil.isEmpty(name)) {
                name = null;
            }
            customer.setCustomerName(name);

            String appid = customer.getCustomerAppid();
            if (StringUtil.isEmpty(appid)) {
                appid = null;
            }
            customer.setCustomerAppid(appid);

            String appsecret = customer.getCustomerAppsecret();
            if (StringUtil.isEmpty(appsecret)) {
                appsecret = null;
            }
            customer.setCustomerAppsecret(appsecret);

            String token = customer.getCustomerToken();
            if (StringUtil.isEmpty(token)) {
                token = null;
            }
            customer.setCustomerToken(token);

            String aeskey = customer.getCustomerAeskey();
            if (StringUtil.isEmpty(aeskey)) {
                aeskey = null;
            }
            customer.setCustomerAeskey(aeskey);
        }


        Integer createTime = (int)(System.currentTimeMillis()/1000);
        customer.setCreateTime(createTime);


        String img = customer.getCustomerImg();
        if (StringUtil.isEmpty(img)) {
            img = null;
        }
        customer.setCustomerImg(img);

        String secret = customer.getCustomerSecret();
        if (StringUtil.isEmpty(secret)) {
            secret = null;
        }
        customer.setCustomerSecret(secret);

        reCustomerDAO.insertSelective(customer);


        return JsonUtil.buildSuccess();
    }

    /**
     * 获取要修改的客户的纪录
     * @param request
     * @param customerId
     * @return
     */
    public ReCustomerListVo doGetUpdateData (HttpServletRequest request,Integer customerId) {

        ReCustomer customer = reCustomerDAO.selectByPrimaryKey(customerId);
        ReCustomerListVo vo = new ReCustomerListVo();

        if (customer.getCustomerType() == 0) { //普通商户

            vo.setCustomerId(customerId);
            vo.setCustomerType(customer.getCustomerType());
            vo.setCustomerSecret(customer.getCustomerSecret());
            vo.setCustomerName(customer.getCustomerName());
            vo.setCustomerImg(customer.getCustomerImg());

        }
        if (customer.getCustomerType() == 1) { //公众号

            vo.setCustomerId(customer.getCustomerId());

            vo.setCustomerType(customer.getCustomerType());
            vo.setCustomerSecret(customer.getCustomerSecret());

            String wx = customer.getCustomerWx();
            if (wx == null || wx.trim().length() == 0) {
                wx = "";
            }
            vo.setCustomerWx(wx);
            String name = customer.getCustomerName();
            if (name == null || name.trim().length() == 0) {
                name = "";
            }
            vo.setCustomerName(name);
            String img = customer.getCustomerImg();
            if (img == null || img.trim().length() == 0) {
                img = "";
            }
            vo.setCustomerImg(img);
            String appid = customer.getCustomerAppid();
            if (appid == null || appid.trim().length() == 0) {
                appid = "";
            }
            vo.setCustomerAppid(appid);
            String secret = customer.getCustomerAppsecret();
            if (secret == null || secret.trim().length() == 0) {
                secret = "";
            }
            vo.setCustomerAppsecret(secret);
            String token = customer.getCustomerToken();
            if (token == null || token.trim().length() == 0) {
                token = "";
            }
            vo.setCustomerToken(token);
            String aeskey = customer.getCustomerAeskey();
            if (aeskey == null || aeskey.trim().length() == 0) {
                aeskey = "";
            }
            vo.setCustomerAeskey(aeskey);

            vo.setCustomerSendtype(customer.getCustomerSendtype());
            vo.setCreateTime(customer.getCreateTime());

            String showTime = ElBase.fmt10Time(customer.getCreateTime());
            vo.setShowTime(showTime);

            vo.setMode(customer.getMode());
        }

        return vo;
    }

    /**
     * 修改客户信息
     * @param request
     * @param customer
     * @return
     */
    public String doUpdateCustom(HttpServletRequest request,ReCustomer customer) {

        Integer type = customer.getCustomerType();
        Integer id = customer.getCustomerId();
        ReCustomer old = null;
        if (id != null){

            old = reCustomerDAO.selectByPrimaryKey(id);
            if (old == null) {
                return JsonUtil.buildError("客户不存在");
            }
        }
        if (type == 1) { //公众号

            //先判断是否存在
            String wx = customer.getCustomerWx();
            if (wx != null){

                int n = reCustomerDAO.updateByPrimaryKeySelective(customer);
                return  JsonUtil.buildSuccess("成功修改"+n+"条纪录");
            }
        }else { //普通用户

            old.setCustomerSecret(customer.getCustomerSecret());
            old.setCustomerName(customer.getCustomerName());
            old.setCustomerImg(customer.getCustomerImg());

            int n = reCustomerDAO.updateByPrimaryKeySelective(old);
            return  JsonUtil.buildSuccess("成功修改"+n+"条纪录");

        }

        return JsonUtil.buildError();
    }

    /**
     * 删除多个微信号
     * @param request
     * @param wxs
     * @return
     */
    public String doDeleteCustoms(HttpServletRequest request,String wxs) {

        if (wxs != null && !"".equals(wxs.trim())) {
            String[] wx = wxs.split("&");
            int n = 0;
            for (String customWx : wx) {
                int i = reCustomerDAO.deleteById(Integer.valueOf(customWx));
                if (i != 0){
                    n++;
                }
            }
            return JsonUtil.buildSuccess("成功删除"+n+"条纪录");
        }
        return JsonUtil.buildError("删除失败");
    }

    /**
     * 新增推广的时候,要选择客户的推广已经结束的微信豪添加
     * @param request
     * @return
     */
    public List<ReCustomer> getCustomWxsByStatus(HttpServletRequest request) {

        return reCustomerDAO.getAllWxWhichItsExtendsEnd ();
    }
}
