package com.envolope.oss.service;

import com.envolope.oss.dao.ReFixedRedDAO;
import com.envolope.oss.dao.ReSortRedDAO;
import com.envolope.oss.model.ReFixedRed;
import com.envolope.oss.model.ReSortRed;
import com.envolope.oss.model.para.RedSelectParamVo;
import com.envolope.oss.model.vo.RedVo;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jan on 16/8/17.
 * 分类 红包
 */
@Service
public class RedService {

    @Autowired
    private ReSortRedDAO reSortRedDAO;


    public List<ReSortRed> getRedsList (RedSelectParamVo paramVo) throws Exception {

        if (StringUtils.isEmpty(paramVo.getRedName())){
            paramVo.setRedName(null);
        }
        if (StringUtils.isEmpty(paramVo.getMerchantName())){
            paramVo.setMerchantName(null);
        }
        if (StringUtils.isEmpty(paramVo.getRedSort())){
            paramVo.setRedSort(null);
        }
        if (StringUtils.isEmpty(paramVo.getRedStatus())){
            paramVo.setRedStatus(null);
        }
        if (StringUtils.isEmpty(paramVo.getShowOrNot())){
            paramVo.setShowOrNot(null);
        }

        String startTime = paramVo.getStartTime();
        if (StringUtils.isEmpty(startTime)){
            paramVo.setStartTimeStamp(null);
        }else {
            paramVo.setStartTimeStamp(Long.valueOf(ElBase.get13Timestamp(startTime)));
        }
        String endTime = paramVo.getEndTime();
        if (StringUtils.isEmpty(endTime)){
            paramVo.setEndTimeStamp(null);
        }else {
            paramVo.setEndTimeStamp(Long.valueOf(ElBase.get13Timestamp(endTime)));
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

        return reSortRedDAO.getAllReds(paramVo,bounds);
    }

    /**
     *
     * @param paramVo
     * @return
     * @throws Exception
     */
    public Integer getRedsListNum(RedSelectParamVo paramVo) throws Exception {

        if (StringUtils.isEmpty(paramVo.getRedName())){
            paramVo.setRedName(null);
        }
        if (StringUtils.isEmpty(paramVo.getMerchantName())){
            paramVo.setMerchantName(null);
        }
        if (StringUtils.isEmpty(paramVo.getRedSort())){
            paramVo.setRedSort(null);
        }
        if (StringUtils.isEmpty(paramVo.getRedStatus())){
            paramVo.setRedStatus(null);
        }
        if (StringUtils.isEmpty(paramVo.getShowOrNot())){
            paramVo.setShowOrNot(null);
        }

        String startTime = paramVo.getStartTime();
        if (StringUtils.isEmpty(startTime)){
            paramVo.setStartTimeStamp(null);
        }else {
            paramVo.setStartTimeStamp(Long.valueOf(ElBase.get13Timestamp(startTime)));
        }
        String endTime = paramVo.getEndTime();
        if (StringUtils.isEmpty(endTime)){
            paramVo.setEndTimeStamp(null);
        }else {
            paramVo.setEndTimeStamp(Long.valueOf(ElBase.get13Timestamp(endTime)));
        }

        return reSortRedDAO.getRedsListNum(paramVo);

    }
    /**
     * delete navigation by id1&id2&...
     * @param request
     * @param redIds
     * @return
     */
    public String doDeleteSortReds(HttpServletRequest request,String redIds) {


        int n = 0 ;

        if (!StringUtils.isEmpty(redIds)) {
            String[] reds = redIds.split("&");
            List<String> redList = Arrays.asList(reds);

            for (String red : redList) {
                Integer redId = Integer.valueOf(red);
                ReSortRed reSortRed = reSortRedDAO.selectByPrimaryKey(redId);
                if (reSortRed != null) {
                    reSortRedDAO.deleteByPrimaryKey(redId);
                    n ++ ;
                }
            }
        }

        return JsonUtil.buildSuccess("成功删除"+n+"条纪录");
    }

    /**
     *
     * @param redId
     * @return
     */
    public ReSortRed getFixedRedById(Integer redId) {

        return reSortRedDAO.selectByPrimaryKey(redId);
    }


    /**
     * 修改 或者 添加 导航
     * @param request
     * @param vo
     * @return
     * @throws Exception
     */
    public String doSaveOrUpdateRed (HttpServletRequest request, RedVo vo) throws Exception {

        ReSortRed red = turnRedVoToReSortRed(vo);

        Integer redId = red.getRedId();

        if (StringUtils.isEmpty(redId)) { //ID空,则为添加

            reSortRedDAO.insertSelective(red);

            return JsonUtil.buildSuccess();

        }else { //有ID,则为修改

            reSortRedDAO.updateByPrimaryKeySelective(red);
            return JsonUtil.buildSuccess();
        }
    }

    public ReSortRed turnRedVoToReSortRed (RedVo vo) throws Exception {

        ReSortRed red = new ReSortRed();

        red.setRedId(vo.getRedId());
        String redName = vo.getRedName();
        if (StringUtils.isEmpty(redName)){
            redName = null;
        }
        red.setRedName(redName);

        String redImg = vo.getRedImg();
        if (StringUtils.isEmpty(redImg)){
            redImg = null;
        }
        red.setRedImg(redImg);

        String merchantName = vo.getMerchantName();
        if (StringUtils.isEmpty(merchantName)){
            merchantName = null;
        }
        red.setMerchantName(merchantName);

        String merchantDetail = vo.getMerchantDetail();
        if (StringUtils.isEmpty(merchantDetail)){
            merchantDetail = null;
        }
        red.setMerchantDetail(merchantDetail);

        String redRewardDesc = vo.getRedRewardDesc();
        if (StringUtils.isEmpty(redRewardDesc)){
            redRewardDesc = null;
        }
        red.setRedRewardDesc(redRewardDesc);

        Integer rewardMoney = vo.getRewardMoney();
        if (StringUtils.isEmpty(rewardMoney)){
            rewardMoney = 0 ;
        }
        red.setRewardMoney(rewardMoney);

        String extraRewardDesc = vo.getExtraRewardDesc();
        if (StringUtils.isEmpty(extraRewardDesc)){
            extraRewardDesc = null;
        }
        red.setExtraRewardDesc(extraRewardDesc);

        Integer extraMoney = vo.getExtraMoney();
        if (StringUtils.isEmpty(extraMoney)){
            extraMoney = 0;
        }
        red.setExtraMoney(extraMoney);

        red.setRedSort(vo.getRedSort());

        String redDesc = vo.getRedDesc();
        if (StringUtils.isEmpty(redDesc)){
            redDesc = null;
        }
        red.setRedDesc(redDesc);

        String detailUrl = vo.getDetailUrl();
        if (StringUtils.isEmpty(detailUrl)){
            detailUrl = null;
        }
        red.setDetailUrl(detailUrl);

        red.setDetailDeitor(vo.getDetailDeitor());
        red.setButtonDeitor(vo.getButtonDeitor());
        red.setRedOrder(vo.getRedOrder());
        red.setRedStatus(vo.getRedStatus());
        red.setShowOrNot(vo.getShowOrNot());

        String showStartTime = vo.getShowStartTime();
        if (StringUtils.isEmpty(showStartTime)){
            red.setStartTime(null);
        }else {
            Long startTime =Long.valueOf(ElBase.get13Timestamp(showStartTime)) ;
            red.setStartTime(startTime);
        }

        String showEndTime = vo.getShowEndTime();
        if (StringUtils.isEmpty(showEndTime)){
            red.setEndTime(null);
        }else {
            Long endTime =Long.valueOf(ElBase.get13Timestamp(showEndTime)) ;
            red.setEndTime(endTime);
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

}
