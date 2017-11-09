package com.envolope.oss.service.userManager;

import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReAccountDetailDAO;
import com.envolope.oss.dao.ReUserDAO;
import com.envolope.oss.dao.ReWithdrawDetailDAO;
import com.envolope.oss.model.ReAccountDetail;
import com.envolope.oss.model.ReUser;
import com.envolope.oss.model.vo.user.UserMoneyVo;
import com.envolope.oss.util.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jan on 16/10/13.
 * 现金记录
 */
@Service
public class MoneyRecordsService {

    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO ;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReWithdrawDetailDAO reWithdrawDetailDAO;

    /**
     * 总数,分页用
     * @param userId
     * @return
     */
    public Integer getNum(HttpServletRequest request, Integer userId){
        return reAccountDetailDAO.getNum(userId);
    }

    /**
     * 列表查询
     * @param request
     * @param userId
     * @return
     */
    public List<ReAccountDetail> getList(HttpServletRequest request, Integer pageNum, Integer userId){
        int offset = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offset,ValueConsts.PAGE_SIZE);
        List<ReAccountDetail> details = reAccountDetailDAO.getList(userId, bounds);
        return details;
    }

    /**
     * 获取个人金钱记录上面的数据
     * @param userId
     * @return
     */
    public UserMoneyVo getMoneyVo(Integer userId){

        UserMoneyVo vo = new UserMoneyVo();
        vo.setUserId(userId);

        ReUser user = reUserDAO.selectByPrimaryKey(userId);

        BigDecimal money = user.getUserMoney();
        if (StringUtil.isEmpty(money)){
            money = new BigDecimal("0.00");
        }
        vo.setMoney(money);

        //累积提现金额 提现状态; 0:未处理 ,1:已处理,此处使提现成功的,即状态为1的记录
        BigDecimal withdrawMoney = reWithdrawDetailDAO.getWithdrawMoneyByUserIdAndStatus(1,userId);
        if (StringUtil.isEmpty(withdrawMoney)){
            withdrawMoney = new BigDecimal("0.00");
        }
        vo.setWithdrawMoney(withdrawMoney);

        //累积获得的金额 类型;0:支出,1:收入,此处是收入
        BigDecimal totalGetMoney = reAccountDetailDAO.getTotalMoneyByUserIdAndType(1,userId);
        if (StringUtil.isEmpty(totalGetMoney)){
            totalGetMoney = new BigDecimal("0.00");
        }
        vo.setTotalGetMoney(totalGetMoney);

        //提现审核 提现状态; 0:未处理 ,1:已处理,此处是提审核的,即状态为0的记录
        BigDecimal withdrawVerifyMoney = reWithdrawDetailDAO.getWithdrawMoneyByUserIdAndStatus(0,userId);
        if (StringUtil.isEmpty(withdrawVerifyMoney)){
            withdrawVerifyMoney = new BigDecimal("0.00");
        }
        vo.setWithdrawVerifyMoney(withdrawVerifyMoney);

        return vo;
    }

}
