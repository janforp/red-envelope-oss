package com.envolope.oss.service;

import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReCustomerDAO;
import com.envolope.oss.dao.ReCustomerExtendDAO;
import com.envolope.oss.model.ReCustomer;
import com.envolope.oss.model.ReCustomerExtend;
import com.envolope.oss.model.vo.NewExtentVo;
import com.envolope.oss.model.vo.ReCustomerExtendListVo;
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
 * Created by Jan on 16/7/14.
 * 推广
 */
@Service
public class ExtentService {

    @Autowired
    private ReCustomerExtendDAO reCustomerExtendDAO;
    @Autowired
    private CreateRedEnvelopeService createRedEnvelopeService;
    @Autowired
    private ReCustomerDAO reCustomerDAO;


    /**
     * 获取 推广 列表
     * @param request
     * @param customerName
     * @param customerWx
     * @param status
     * @param pageNum
     * @param type      类型
     * @return
     */
    public List<ReCustomerExtendListVo> getAllExtends (HttpServletRequest request,
                                                              String customerName,
                                                              String customerWx,
                                                              Integer status,
                                                              Integer pageNum,
                                                              Integer type) {

        if (StringUtil.isEmpty(customerName)){
            customerName = null;
        }
        if (StringUtil.isEmpty(customerWx)){
            customerWx = null;
        }
        if (StringUtil.isEmpty(status)){
            status = null;
        }
        if (StringUtil.isEmpty(type)) {
            type = null;
        }

        int offSet = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offSet, ValueConsts.PAGE_SIZE);
        List<ReCustomerExtend> exts = reCustomerExtendDAO.getAllExtends(customerName,customerWx,status,type,bounds);
        List<ReCustomerExtendListVo> vos = new ArrayList<>(exts.size());

        for (ReCustomerExtend extend : exts) {

            ReCustomerExtendListVo vo = turnExtendToReCustomerExtendListVo(extend);

            vos.add(vo);
        }
        return vos;
    }

    /**
     * 获取 推广 列表 总数 用于分页
     * @param request
     * @param customerName
     * @param customerWx
     * @param status
     * @return
     */
    public Integer getAllExtendsNum (HttpServletRequest request,
                                     String customerName,
                                     String customerWx,
                                     Integer status,
                                     Integer type){

        if (StringUtil.isEmpty(customerName)){
            customerName = null;
        }
        if (StringUtil.isEmpty(customerWx)){
            customerWx = null;
        }
        if (StringUtil.isEmpty(status)){
            status = null;
        }

        return reCustomerExtendDAO.getAllExtendsNum(customerName,customerWx,status,type);
    }

    /**
     * 查看某个推广详情
     * @param request
     * @param id
     * @return
     */
    public ReCustomerExtendListVo getDetailById(HttpServletRequest request,Integer id){

        ReCustomerExtend extend = reCustomerExtendDAO.selectByPrimaryKey(id);

        return turnExtendToReCustomerExtendListVo(extend);
    }

    /**
     * 两个实体类之间的转换
     * @param extend
     * @return
     */
    public ReCustomerExtendListVo turnExtendToReCustomerExtendListVo (ReCustomerExtend extend) {

        ReCustomerExtendListVo vo = new ReCustomerExtendListVo();
        Integer customerId = extend.getCustomerId();
        if (customerId!=null){

            ReCustomer customer = reCustomerDAO.selectByPrimaryKey(customerId);
            if (customer != null) {
                String  customerName = customer.getCustomerName();
                if (StringUtil.isEmpty(customerName)){
                    customerName ="";
                }
                vo.setCustomerName(customerName);
            }
        }


        vo.setId(extend.getId());
        vo.setCustomerId(customerId);

        Integer isRedirect = extend.getIsRedirect();
        vo.setIsRedirect(isRedirect);
        if (isRedirect == 0) {
            vo.setIsRedirectShow("不跳转");
        }
        if (isRedirect == 1) {
            vo.setIsRedirectShow("跳转");
        }
        String redirectUrl = extend.getRedirectUrl();
        if (StringUtil.isEmpty(redirectUrl)) {
            redirectUrl = "";
        }
        vo.setRedirectUrl(redirectUrl);
        Integer waitTime = extend.getWaitTime();
        if (StringUtil.isEmpty(waitTime)) {
            waitTime = 0 ;
        }
        vo.setWaitTime(waitTime);
        String  extendDesc = extend.getExtendDesc();
        if (StringUtil.isEmpty(extendDesc)) {
            extendDesc = "";
        }
        vo.setExtendDesc(extendDesc);
        String customerBanner = extend.getCustomerBanner();
        if (StringUtil.isEmpty(customerBanner)) {
            customerBanner = "";
        }
        vo.setCustomerBanner(customerBanner);

        String customerBannerUrl = extend.getCustomerBannerUrl();
        if (StringUtil.isEmpty(customerBannerUrl)) {
            customerBannerUrl = "";
        }
        vo.setCustomerBannerUrl(customerBannerUrl);

        String customerAdvert = extend.getCustomerAdvert();
        if (StringUtil.isEmpty(customerAdvert)) {
            customerAdvert = "";
        }
        vo.setCustomerAdvert(customerAdvert);

        String customerAdvertUrl = extend.getCustomerAdvertUrl();
        if (StringUtil.isEmpty(customerAdvertUrl)) {
            customerAdvertUrl = "";
        }
        vo.setCustomerAdvertUrl(customerAdvertUrl);

        Integer redNumTotal = extend.getRedNumTotal();
        if (redNumTotal == null) {
            redNumTotal = 0;
        }
        vo.setRedNumTotal(redNumTotal);

        Integer redNumLeft = extend.getRedNumLeft();
        if (redNumLeft == null) {
            redNumLeft = 0;
        }
        vo.setRedNumLeft(redNumLeft);

        Integer redNumDayTotal = extend.getRedNumDayTotal();
        if (redNumDayTotal == null) {
            redNumDayTotal = 0;
        }
        vo.setRedNumDayTotal(redNumDayTotal);

        Integer redNumDayLeft = extend.getRedNumDayLeft();
        if (redNumDayLeft == null) {
            redNumDayLeft = 0;
        }
        vo.setRedNumDayLeft(redNumDayLeft);

        Integer customStatus = extend.getCustomerStatus();
        vo.setCustomerStatus(customStatus);
        if (customStatus == 0) {
            vo.setCustomStatusDesc("已结束");
        }
        if (customStatus == 1) {
            vo.setCustomStatusDesc("进行中");
        }

        Integer startTime = extend.getStartTime();
        String showStartTime = ElBase.fmt10Time(startTime);
        vo.setStartTime(startTime);
        vo.setShowStartTime(showStartTime);

        Integer endTime = extend.getEndTime();
        String showEndTime = ElBase.fmt10Time(endTime);
        vo.setEndTime(endTime);
        vo.setShowEndTime(showEndTime);

        Integer isHot = extend.getIsHot();
        vo.setIsHot(isHot);
        if (isHot == 0) {
            vo.setIsHotDesc("否");
        }
        if (isHot == 1) {
            vo.setIsHotDesc("是");
        }
        vo.setRedChance(extend.getRedChance());

        String stepRule = extend.getStepRule();
        if (StringUtil.isEmpty(stepRule)){
            stepRule ="";
        }
        vo.setStepRule(stepRule);

        return vo;
    }

    /**
     * 新增 推荐
     * @param request
     * @return
     */
    public String doSaveExtend (HttpServletRequest request, NewExtentVo vo) throws Exception {

        Integer num = vo.getNum();
        String big = vo.getBig();
        Integer customerId = vo.getCustomerId();
        Integer redNum = createRedEnvelopeService.createEnvelope(request,customerId,num,big);

        ReCustomerExtend extend = new ReCustomerExtend();

        List<Integer> customersIng = reCustomerExtendDAO.getCustomersWhichStatusIsIng();
        for (Integer  customId : customersIng) {
            if (vo.getCustomerId()== customId) {
                return JsonUtil.buildError("此微信号有进行中的推广,不能再次新增推广");
            }
        }
        extend.setCustomerId(vo.getCustomerId());
        extend.setIsRedirect(vo.getIsRedirect());
        String redirecUlr = vo.getRedirectUrl();
        if (StringUtil.isEmpty(redirecUlr)) {
            redirecUlr = null;
        }
        extend.setRedirectUrl(redirecUlr);

        Integer waitTime = vo.getWaitTime();
        if (StringUtil.isEmpty(waitTime)) {
            waitTime = 0;
        }
        extend.setWaitTime(waitTime);

        String desc = vo.getExtendDesc();
        if (StringUtil.isEmpty(desc)) {
            desc = null;
        }
        extend.setExtendDesc(desc);

        String bannerImg = vo.getBannerImg();
        if (StringUtil.isEmpty(bannerImg)){
            bannerImg = null;
        }
        extend.setCustomerBanner(bannerImg);

        String bannerUrl = vo.getBannerUrl();
        if (StringUtil.isEmpty(bannerUrl)){
            bannerUrl = null;
        }
        extend.setCustomerBannerUrl(bannerUrl);

        String adverstImg = vo.getAdvertImg();
        if (StringUtil.isEmpty(adverstImg)){
            adverstImg = null;
        }
        extend.setCustomerAdvert(adverstImg);

        String adverstUrl = vo.getAdverstUrl();
        if (StringUtil.isEmpty(adverstUrl)){
            adverstUrl = null;
        }
        extend.setCustomerAdvertUrl(adverstUrl);

        if (redNum == null){
            redNum = 0;
        }
        extend.setRedNumTotal(redNum);
        extend.setRedNumLeft(redNum);
        extend.setRedNumDayTotal(0);
        extend.setRedNumDayLeft(0);
        extend.setCustomerStatus(vo.getStatus());
        extend.setIsHot(vo.getIsHot());

        String start = vo.getStartTime();
        String end = vo.getEndTime();
        if (StringUtil.isEmpty(start)) {
            return JsonUtil.buildError("开始时间不能为空");
        }
        if (StringUtil.isEmpty(end)) {
            return JsonUtil.buildError("结束时间不能为空");
        }
        start = ElBase.get13Timestamp(start);
        end = ElBase.get13Timestamp(end);

        Integer startTime = (int)(Long.valueOf(start)/1000);
        Integer endTime = (int)(Long.valueOf(end)/1000);

        extend.setStartTime(startTime);
        extend.setEndTime(endTime);

        extend.setStepRule(vo.getStepRule());

        reCustomerExtendDAO.insertSelective(extend);

        Integer extendId = extend.getId();

        return JsonUtil.buildSuccess("操作成功,并生成"+redNum+"个红包",extendId);
    }

    /**
     * 删除推广纪录
     * @param request
     * @param idsStr    要删除的推广纪录的多个id的用那个&链接的字符串
     * @return
     */
    public String doDeleteExtend (HttpServletRequest request,String idsStr){

        if (idsStr == null || idsStr.trim().length() == 0) {
            return JsonUtil.buildError("没有要删除的ID");
        }
        String [] ids = idsStr.split("&");
        int i = 0;
        for (String str : ids) {
            Integer id = Integer.valueOf(str.trim());
            Integer row = reCustomerExtendDAO.deleteByPrimaryKey(id);
            if (row != null && row != 0) {
                i ++ ;
            }
        }

        return JsonUtil.buildSuccess("删除了"+i+"条推广");
    }

    /**
     * 修改 推广
     * @param request
     * @param vo
     * @return
     */
    public String doUpdateExtend (HttpServletRequest request,NewExtentVo vo) throws Exception {

        Integer extendId = vo.getExtendId();
        ReCustomerExtend extend = reCustomerExtendDAO.selectByPrimaryKey(extendId);
        if (extend == null) {
            return JsonUtil.buildError("推广不存在");
        }

        extend.setIsRedirect(vo.getIsRedirect());
        extend.setRedirectUrl(vo.getRedirectUrl());
        extend.setWaitTime(vo.getWaitTime());
        extend.setExtendDesc(vo.getExtendDesc());

        extend.setCustomerBanner(vo.getBannerImg());
        extend.setCustomerBannerUrl(vo.getBannerUrl());
        extend.setCustomerAdvert(vo.getAdvertImg());
        extend.setCustomerAdvertUrl(vo.getAdverstUrl());
        extend.setCustomerStatus(vo.getStatus());

        String start = vo.getStartTime();
        start = ElBase.get10Timestamp(start);
        String end = vo.getEndTime();
        end = ElBase.get10Timestamp(end);

        extend.setStartTime(Integer.valueOf(start));
        extend.setEndTime(Integer.valueOf(end));

        extend.setIsHot(vo.getIsHot());
        extend.setRedChance(vo.getRedChance());
        extend.setStepRule(vo.getStepRule());

        reCustomerExtendDAO.updateByPrimaryKey(extend);

        return JsonUtil.buildSuccess(extendId.toString());

    }

    /**
     * 实体类转换
     * @param vo
     * @return
     * @throws Exception
     */
    public ReCustomerExtend turnNewExtentVoToReCustomerExtend (NewExtentVo vo) throws Exception {

        ReCustomerExtend extend = new ReCustomerExtend();

        extend.setId(vo.getExtendId());
        extend.setCustomerId(vo.getCustomerId());
        extend.setIsRedirect(vo.getIsRedirect());
        extend.setRedirectUrl(vo.getRedirectUrl());
        extend.setWaitTime(vo.getWaitTime());
        extend.setExtendDesc(vo.getExtendDesc());

        extend.setCustomerBanner(vo.getBannerImg());
        extend.setCustomerBannerUrl(vo.getBannerUrl());
        extend.setCustomerAdvert(vo.getAdvertImg());
        extend.setCustomerAdvertUrl(vo.getAdverstUrl());
        extend.setCustomerStatus(vo.getStatus());

        String start = vo.getStartTime();
        start = ElBase.get10Timestamp(start);
        String end = vo.getEndTime();
        end = ElBase.get10Timestamp(end);

        extend.setStartTime(Integer.valueOf(start));
        extend.setEndTime(Integer.valueOf(end));

        extend.setIsHot(vo.getIsHot());
        extend.setRedChance(vo.getRedChance());


        return extend;

    }
}
