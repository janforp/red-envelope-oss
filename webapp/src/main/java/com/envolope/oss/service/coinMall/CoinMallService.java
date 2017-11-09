package com.envolope.oss.service.coinMall;

import com.envolope.oss.dao.ReAddressDAO;
import com.envolope.oss.dao.ReCoinItemDAO;
import com.envolope.oss.dao.ReExchangeDetailDAO;
import com.envolope.oss.dao.ReUserDAO;
import com.envolope.oss.model.ReAddress;
import com.envolope.oss.model.ReCoinItem;
import com.envolope.oss.model.ReExchangeDetail;
import com.envolope.oss.model.ReUser;
import com.envolope.oss.model.vo.ExchangeListVo;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/9/9.
 */
@Service
public class CoinMallService {

    @Autowired
    private ReExchangeDetailDAO reExchangeDetailDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAddressDAO reAddressDAO;
    @Autowired
    private ReCoinItemDAO reCoinItemDAO;
    /**
     * 总数,分页用
     * @param status
     * @return
     */
    public Integer getExchangeNum(Integer status){

        return reExchangeDetailDAO.getExchangeNum(status);
    }

    /**
     * 列表查询
     * @param request
     * @param status
     * @param pageSize
     * @param pageNum
     * @return
     */
    public List<ExchangeListVo> getExchangeDetail(HttpServletRequest request, Integer status , Integer pageSize, Integer pageNum){

        if (StringUtils.isEmpty(status)){
            status = null;
        }
        if (pageSize == null || pageSize == 0){
            pageSize = 10 ;
        }
        if (pageNum == null || pageNum == 0){
            pageNum = 1 ;
        }

        int offset = (pageNum - 1) * pageSize ;
        RowBounds bounds = new RowBounds(offset,pageSize);

        List<ReExchangeDetail> details = reExchangeDetailDAO.getDetailByPageNum(status,bounds);

        List<ExchangeListVo> vos = new ArrayList<>(details.size());

        for (ReExchangeDetail detail : details) {

            ExchangeListVo vo = turnVo(detail);

            vos.add(vo);
        }

        return vos;
    }

    public ExchangeListVo getDetailByDetailId(Long id){

        ReExchangeDetail detail = reExchangeDetailDAO.selectByPrimaryKey(id);
        if (detail == null) {
            return  null ;
        }
        return turnVo(detail);
    }


    /**
     * 作废
     * @param request
     * @param id
     * @return
     */
    public String doInvalid(HttpServletRequest request,Long id){

        ReExchangeDetail detail = reExchangeDetailDAO.selectByPrimaryKey(id);

        detail.setExchangeStatus(2);

        detail.setSendTime(ElBase.fmtTime(System.currentTimeMillis()));

        reExchangeDetailDAO.updateByPrimaryKeySelective(detail);

        return JsonUtil.buildSuccess();
    }

    /**
     * 兑换
     * @param request
     * @param vo
     * @return
     */
    public String doExchange(HttpServletRequest request,ExchangeListVo vo){

        ReExchangeDetail detail = reExchangeDetailDAO.selectByPrimaryKey(vo.getId());

        if (detail.getExchangeStatus() == 2) {

            return JsonUtil.buildError("该兑换已作废");
        }

        if (detail.getExchangeStatus() == 1) {
            return JsonUtil.buildError("已经发货,不能重复发货");
        }

        detail.setExchangeStatus(1);
        detail.setCardId(vo.getCardId());
        detail.setCardPassword(vo.getCardPassword());
        detail.setExpressNumber(vo.getExpressNumber());
        detail.setSendTime(ElBase.fmtTime(System.currentTimeMillis()));
        detail.setInvalidTime(vo.getInvalidTime());

        reExchangeDetailDAO.updateByPrimaryKeySelective(detail);

        return JsonUtil.buildSuccess();

    }

    /**
     *
     * @param detail
     * @return
     */
    public ExchangeListVo turnVo(ReExchangeDetail detail) {

        ExchangeListVo vo = new ExchangeListVo();

        ReCoinItem item = reCoinItemDAO.selectByPrimaryKey(detail.getGoodsNum());

        vo.setType(item.getItemType());

        vo.setId(detail.getId());
        vo.setExchangeStatus(detail.getExchangeStatus());
        String goodsName = detail.getGoodsName();
        if (StringUtils.isEmpty(goodsName)){
            goodsName = "";
        }
        vo.setGoodsName(goodsName);
        vo.setGoodsNum(detail.getGoodsNum());
        vo.setExchangeTime(detail.getExchangeTime());

        String sendTime = detail.getSendTime();
        if (StringUtils.isEmpty(sendTime)) {
            sendTime = "";
        }
        vo.setSendTime(sendTime);
        Integer userId = detail.getUserId();
        vo.setUserId(userId);

        ReUser user = reUserDAO.selectByPrimaryKey(userId);
        ReAddress address = reAddressDAO.selectByPrimaryKey(userId);

        String userName = address.getRealName();
        if (StringUtils.isEmpty(userName)){
            userName = "";
        }
        vo.setUserName(userName);

        String detailAddress = address.getProvince()+address.getCity()+address.getDetailAddress();
        if (StringUtils.isEmpty(detailAddress)){
            detailAddress = "";
        }
        vo.setAddress(detailAddress);

        String phone = address.getMobile();
        if (StringUtils.isEmpty(phone)) {
            phone = user.getMobile();
        }
        if (StringUtils.isEmpty(phone)){
            phone = "";
        }
        vo.setPhone(phone);

        vo.setScore(detail.getScore());
        String cardId = detail.getCardId() ;
        if (StringUtils.isEmpty(cardId)){
            cardId = "";
        }
        vo.setCardId(cardId);

        String  cardPw = detail.getCardPassword();
        if (StringUtils.isEmpty(cardPw)){
            cardPw = "";
        }
        vo.setCardPassword(cardPw);

        String expressNum = detail.getExpressNumber();
        if (StringUtils.isEmpty(expressNum)){
            expressNum = "";
        }
        vo.setExpressNumber(expressNum);
        String invalidTime = detail.getInvalidTime();
        if (StringUtil.isEmpty(invalidTime)){
            invalidTime="";
        }
        vo.setInvalidTime(invalidTime);

        return vo;
    }

}
