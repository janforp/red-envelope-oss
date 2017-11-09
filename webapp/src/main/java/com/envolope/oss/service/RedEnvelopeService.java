package com.envolope.oss.service;

import com.envolope.oss.dao.ReCustomerDAO;
import com.envolope.oss.dao.ReCustomerExtendDAO;
import com.envolope.oss.model.ReCustomer;
import com.envolope.oss.model.ReCustomerExtend;
import com.envolope.oss.model.vo.AddRedPageShowVo;
import com.envolope.oss.model.vo.UnlockRedPageShowVo;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jan on 16/7/15.
 * 红包
 */
@Service
public class RedEnvelopeService {

    @Autowired
    private CreateRedEnvelopeService createRedEnvelopeService;
    @Autowired
    private ReCustomerExtendDAO reCustomerExtendDAO;
    @Autowired
    private ReCustomerDAO reCustomerDAO;

    /**
     * 添加红包
     * @param request
     * @param id            推广id
     * @param bigEnvelope   大额红包字符串
     * @param num           随机红包数
     * @return
     */
    public String doAddRed (HttpServletRequest request,Integer id,Integer num,String bigEnvelope) {

        ReCustomerExtend extend = reCustomerExtendDAO.selectLockExtendById(id);
        if (extend == null) {
            return JsonUtil.buildError("推广不存在");
        }
        if (extend.getCustomerStatus() == 0) {
            return JsonUtil.buildError("推广已结束,不能添加红包");
        }
        Integer customerId = extend.getCustomerId();

        Integer redNum = createRedEnvelopeService.createEnvelope(request,customerId,num,bigEnvelope);

        Integer totalReDNum = extend.getRedNumTotal();
        Integer totalLeft = extend.getRedNumLeft();
        if (redNum != null && totalReDNum != null) {
            totalReDNum = totalReDNum +redNum;
            totalLeft = totalLeft + redNum;
        }
        extend.setRedNumTotal(totalReDNum);
        extend.setRedNumLeft(totalLeft);
        //修改推广的红包总数
        reCustomerExtendDAO.updateByPrimaryKey(extend);

        return JsonUtil.buildSuccess("生成"+redNum+"个红包");
    }

    /**
     * 添加红包 页面数据
     * @param request
     * @param id  推广id
     * @return
     */
    public AddRedPageShowVo getAddRedCustomData (HttpServletRequest request,Integer id) {

        ReCustomerExtend extend = reCustomerExtendDAO.selectByPrimaryKey(id);

        AddRedPageShowVo red = null;
        if (extend != null) {

            ReCustomer customer = reCustomerDAO.selectByPrimaryKey(extend.getCustomerId());
            if (customer != null) {
                red = new AddRedPageShowVo();

                red.setId(id);
                red.setWxName(customer.getCustomerName());
            }
        }
        return  red;
    }

    /**
     * 解锁红包 页面数据
     * @param request
     * @param id  推广id
     * @return
     */
    public UnlockRedPageShowVo getUnlickRedCustomData (HttpServletRequest request,Integer id) {

        ReCustomerExtend extend = reCustomerExtendDAO.selectByPrimaryKey(id);

        UnlockRedPageShowVo lock = null;
        if (extend != null) {

            ReCustomer customer = reCustomerDAO.selectByPrimaryKey(extend.getCustomerId());
            if (customer != null) {
                lock = new UnlockRedPageShowVo();

                lock.setId(id);
                lock.setCustomerId(extend.getCustomerId());
                lock.setWxName(customer.getCustomerName());
                lock.setLeft((extend.getRedNumLeft()));
            }
        }
        return  lock;
    }


    /**
     * 解锁红包
     * @param request
     * @param id            推广id
     * @param num           解锁红包数
     * @return
     */
    public String doUnlockRed (HttpServletRequest request,Integer id,Integer num) {

        if (num == null){
            return JsonUtil.buildError("请正确输入解锁的红包数");
        }

        ReCustomerExtend extend  = reCustomerExtendDAO.selectLockExtendById(id);
        if (extend == null) {
            return JsonUtil.buildError("推广不存在");
        }
        if (extend.getCustomerStatus() == 0) {
            return JsonUtil.buildError("推广已经结束");
        }

        ReCustomer customer = reCustomerDAO.selectByPrimaryKey(extend.getCustomerId());

        if (customer == null) {
            return JsonUtil.buildError("客户不存在");
        }

        Integer left = extend.getRedNumLeft();
        if (left <= 0) {
            return JsonUtil.buildError("剩余红包数量为0");
        }

        Integer dayRedNum = extend.getRedNumDayTotal();
        Integer dayRedLeft = extend.getRedNumDayLeft();
        if (left - dayRedLeft < num) {
            //则只能解锁 (left - dayRedLeft)个
            int unlockNum = (left - dayRedLeft);
            dayRedNum = dayRedNum +unlockNum;
            dayRedLeft = dayRedLeft +unlockNum;

            extend.setRedNumDayLeft(dayRedLeft);
            extend.setRedNumDayTotal(dayRedNum);
            reCustomerExtendDAO.updateByPrimaryKey(extend);

            return JsonUtil.buildError("剩余红包不足,解锁了"+unlockNum+"个红包");
        }
        if (left - dayRedLeft >= num) {
            //则解锁num个,且有剩余
            dayRedNum = dayRedNum + num;
            dayRedLeft = dayRedLeft +num;

            extend.setRedNumDayLeft(dayRedLeft);
            extend.setRedNumDayTotal(dayRedNum);
            reCustomerExtendDAO.updateByPrimaryKey(extend);

            return JsonUtil.buildError("解锁了"+num+"个红包");
        }

        return JsonUtil.buildError("操作失败");
    }
}
