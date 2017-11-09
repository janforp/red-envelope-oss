package com.envolope.oss.service;

import com.envolope.oss.dao.ReCodeRedDAO;
import com.envolope.oss.dao.ReSortRedDAO;
import com.envolope.oss.model.ReCodeRed;
import com.envolope.oss.model.ReSortRed;
import com.envolope.oss.model.para.CodeRedSelectParamVo;
import com.envolope.oss.model.vo.CodeRedVo;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jan on 16/8/24.
 * 兑换码红包
 */
@Service
public class CodeRedService {

    @Autowired
    private ReCodeRedDAO reCodeRedDAO ;

    @Autowired
    private ReSortRedDAO reSortRedDAO;

    @Autowired
    private CreateRedEnvelopeService createRedEnvelopeService;


    /**
     * 分页查询
     * @param paramVo
     * @return
     * @throws Exception
     */
    public List<ReCodeRed> getRedsList (CodeRedSelectParamVo paramVo) throws Exception {

        if (StringUtils.isEmpty(paramVo.getCustomerName())){
            paramVo.setCustomerName(null);
        }
        if (StringUtils.isEmpty(paramVo.getCodeStatus())){
            paramVo.setCodeStatus(null);
        }

        Integer pageSize = paramVo.getPageSize();
        if (pageSize == null || pageSize == 0){
            pageSize = 10 ;
        }
        Integer pageNum = paramVo.getPageNum();
        if (pageNum == null || pageNum == 0){
            pageNum = 1 ;
        }

        int offset = (pageNum - 1) * pageSize ;
        RowBounds bounds = new RowBounds(offset,pageSize);

        return reCodeRedDAO.getAllCodeReds(paramVo,bounds);
    }

    /**
     *
     * @param paramVo
     * @return
     * @throws Exception
     */
    public Integer getRedsListNum(CodeRedSelectParamVo paramVo) throws Exception {

        if (StringUtils.isEmpty(paramVo.getCustomerName())){
            paramVo.setCustomerName(null);
        }
        if (StringUtils.isEmpty(paramVo.getCodeStatus())){
            paramVo.setCodeStatus(null);
        }

        return reCodeRedDAO.getCodeRedListNum(paramVo);

    }
//    /**
//     * delete navigation by id1&id2&...
//     * @param request
//     * @param redIds
//     * @return
//     */
//    public String doDeleteSortReds(HttpServletRequest request, String redIds) {
//
//
//        int n = 0 ;
//
//        if (!StringUtils.isEmpty(redIds)) {
//            String[] reds = redIds.split("&");
//            List<String> redList = Arrays.asList(reds);
//
//            for (String red : redList) {
//                Integer redId = Integer.valueOf(red);
//                ReSortRed reSortRed = reSortRedDAO.selectByPrimaryKey(redId);
//                if (reSortRed != null) {
//                    reSortRedDAO.deleteByPrimaryKey(redId);
//                    n ++ ;
//                }
//            }
//        }
//
//        return JsonUtil.buildSuccess("成功删除"+n+"条纪录");
//    }

    /**
     *
     * @param codeId
     * @return
     */
    public ReCodeRed getCodeRedById(Integer codeId) {

        return reCodeRedDAO.selectByPrimaryKey(codeId);
    }


    /**
     * 修改 或者 添加 导航
     * @param request
     * @param vo
     * @return
     * @throws Exception
     */
    public String doUpdateCodeRed (HttpServletRequest request, CodeRedVo vo) throws Exception {

        Integer codeId = vo.getCodeId();

        ReCodeRed reCodeRed = turnCodeRedVoToReCodeRed(vo) ;

        ReCodeRed red = reCodeRedDAO.selectByPrimaryKey(codeId) ;

        if (red == null) {
            return JsonUtil.buildError("数据不存在");
        }

        red.setRedCode(reCodeRed.getRedCode());
        red.setCustomerName(reCodeRed.getCustomerName());
        red.setCustomerImg(reCodeRed.getCustomerImg());
        red.setCustomerDesc(reCodeRed.getCustomerDesc());
        red.setAwardDesc(reCodeRed.getAwardDesc());
        red.setCustomerBanner(reCodeRed.getCustomerBanner());
        red.setCustomerBannerUrl(reCodeRed.getCustomerBannerUrl());
        red.setRedMax(reCodeRed.getRedMax());
        red.setRedDesc(reCodeRed.getRedDesc());
        red.setCodeStatus(reCodeRed.getCodeStatus());

        red.setUpdateTime(System.currentTimeMillis());

        reCodeRedDAO.updateByPrimaryKeySelective(red);

        return JsonUtil.buildSuccess(codeId.toString());
    }

    /**
     * 新增 兑换码 红包
     * @param request
     * @param vo
     * @return
     */
    public String doAddCodeRed(HttpServletRequest request,CodeRedVo vo) throws Exception {

        ReCodeRed red = turnCodeRedVoToReCodeRed(vo);

        red.setRedNumTotal(0);
        red.setRedNumLeft(0);
        red.setRedNumDayTotal(0);
        red.setRedNumDayLeft(0);
        red.setCreateTime(System.currentTimeMillis());
        red.setUpdateTime(System.currentTimeMillis());

        reCodeRedDAO.insertSelective(red);

        Integer totalRedNum = createRedEnvelopeService.createCodeRedEnvelope(request,vo.getMax(),vo.getMin(),red.getCodeId(),vo.getRedNumTotal(),vo.getBigRed());

        red.setRedNumTotal(totalRedNum);

        red.setRedNumLeft(totalRedNum);

        reCodeRedDAO.updateByPrimaryKeySelective(red);

        Integer codeId = red.getCodeId();

        return JsonUtil.buildSuccess(codeId.toString());
    }

    public ReCodeRed turnCodeRedVoToReCodeRed (CodeRedVo vo) throws Exception {

        ReCodeRed red = new ReCodeRed();

        Integer codeId = vo.getCodeId();
        if (StringUtils.isEmpty(codeId)){
            codeId = null;
        }
        red.setCodeId(codeId);

        String redCode = vo.getRedCode();
        if (StringUtils.isEmpty(redCode)){
            redCode = null;
        }
        red.setRedCode(redCode);

        String customerName = vo.getCustomerName();
        if (StringUtils.isEmpty(customerName)){
            customerName = null;
        }
        red.setCustomerName(customerName);

        String customerImg = vo.getCustomerImg();
        if (StringUtils.isEmpty(customerImg)){
            customerImg = null;
        }
        red.setCustomerImg(customerImg);

        String customerDesc = vo.getCustomerDesc();
        if (StringUtils.isEmpty(customerDesc)){
            customerDesc = null;
        }
        red.setCustomerDesc(customerDesc);

        String awardDesc = vo.getAwardDesc();
        if (StringUtils.isEmpty(awardDesc)){
            awardDesc = null;
        }
        red.setAwardDesc(awardDesc);

        String customerBanner = vo.getCustomerBanner();
        if (StringUtils.isEmpty(customerBanner)){
            customerBanner = null ;
        }
        red.setCustomerBanner(customerBanner);

        String customerBannerUrl = vo.getCustomerBannerUrl();
        if (StringUtils.isEmpty(customerBannerUrl)){
            customerBannerUrl = null;
        }
        red.setCustomerBannerUrl(customerBannerUrl);

        String redMax = vo.getRedMax();
        if (StringUtils.isEmpty(redMax)){
            redMax = null;
        }
        red.setRedMax(redMax);

        String redDesc = vo.getRedDesc();
        if (StringUtils.isEmpty(redDesc)){
            redDesc = null;
        }
        red.setRedDesc(redDesc);

        Integer redNumTotal = vo.getRedNumTotal();
        if (StringUtils.isEmpty(redNumTotal)){
            redNumTotal = 0;
        }
        red.setRedNumTotal(redNumTotal);

        Integer redNumLeft = vo.getRedNumLeft();
        if (StringUtils.isEmpty(redNumLeft)){
            redNumLeft = 0;
        }
        red.setRedNumLeft(redNumLeft);

        Integer redNumDayTotal = vo.getRedNumDayTotal();
        if (StringUtils.isEmpty(redNumDayTotal)){
            redNumDayTotal = 0;
        }
        red.setRedNumDayTotal(redNumDayTotal);

        Integer redNumDayLeft = vo.getRedNumDayLeft();
        if (StringUtils.isEmpty(redNumDayLeft)){
            redNumDayLeft = 0;
        }
        red.setRedNumDayLeft(redNumDayLeft);

        red.setCodeStatus(vo.getCodeStatus());

        String showUpdateTime = vo.getShowUpdateTime();
        if (StringUtils.isEmpty(showUpdateTime)){
            red.setUpdateTime(null);
        }else {
            Long updateTime =Long.valueOf(ElBase.get13Timestamp(showUpdateTime)) ;
            red.setUpdateTime(updateTime);
        }

        String showCreateTime = vo.getShowCreateTime();
        if (StringUtils.isEmpty(showCreateTime)){
            red.setCreateTime(null);
        }else {
            Long createTime =Long.valueOf(ElBase.get13Timestamp(showCreateTime)) ;
            red.setCreateTime(createTime);
        }
        return red;
    }

    /**
     * 针对某个兑换码红包 增加他的 红包总数
     * @param codeId
     * @param bigEnvelope
     * @param num
     * @return
     */
    public String doAddRedNum (HttpServletRequest request ,Integer codeId, String bigEnvelope, Integer num, String max, String min) {

        ReCodeRed red = reCodeRedDAO.selectLockCodeRedById(codeId);
        if (red == null) {
            return JsonUtil.buildError("该兑换红包不存在");
        }

        Integer addNum = createRedEnvelopeService.createCodeRedEnvelope(request,max,min,codeId,num,bigEnvelope);

        Integer totalReDNum = red.getRedNumTotal();
        Integer totalLeft = red.getRedNumLeft();

        if (addNum != null && totalReDNum != null) {
            totalReDNum = totalReDNum + addNum;
            totalLeft = totalLeft + addNum;
        }
        red.setRedNumTotal(totalReDNum);
        red.setRedNumLeft(totalLeft);
        //修改推广的红包总数
        reCodeRedDAO.updateByPrimaryKey(red);

        return JsonUtil.buildSuccess("生成"+addNum+"个红包");
    }

    /**
     * 解锁 红包
     * @param request
     * @param codeId
     * @param num
     * @return
     */
    public String doUnlockCodeRed(HttpServletRequest request, Integer codeId, int num){

        ReCodeRed red = reCodeRedDAO.selectLockCodeRedById(codeId);
        if (red == null) {
            return JsonUtil.buildError("红包不存在");
        }

        int left = red.getRedNumLeft();
        if (left <= 0) {
            return JsonUtil.buildError("剩余红包数量为0");
        }

        int dayRedNum = red.getRedNumDayTotal();
        int dayRedLeft = red.getRedNumDayLeft();

        if (left < num) {
            // 则只能解锁left个
            dayRedNum = dayRedNum + left;
            dayRedLeft = dayRedLeft + left;
        }else {
            dayRedNum = dayRedNum + num;
            dayRedLeft = dayRedLeft +num;
        }

        red.setRedNumDayLeft(dayRedLeft);
        red.setRedNumDayTotal(dayRedNum);
        reCodeRedDAO.updateByPrimaryKey(red);

        return JsonUtil.buildError("解锁了"+num+"个红包");

    }

}
