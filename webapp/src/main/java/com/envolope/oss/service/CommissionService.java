package com.envolope.oss.service;

import com.envolope.oss.dao.*;
import com.envolope.oss.enums.MissionSubtype;
import com.envolope.oss.enums.MissionType;
import com.envolope.oss.model.*;
import com.envolope.oss.model.vo.CommissionListVo;
import com.envolope.oss.model.vo.CommissionSelectParamVo;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/9/27.
 * 佣金提现
 */
@Service
public class CommissionService {

    @Autowired
    private ReUserCommissionWithdrawDAO reUserCommissionWithdrawDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReUserCommissionDetailDAO reUserCommissionDetailDAO;


    public List<CommissionListVo> getCommissionList (CommissionSelectParamVo paramVo){

        if (StringUtils.isEmpty(paramVo.getStatus())){
            paramVo.setStatus(null);
        }
        if (StringUtils.isEmpty(paramVo.getCellphone())){
            paramVo.setCellphone(null);
        }
        if (StringUtils.isEmpty(paramVo.getStartTime())){
            paramVo.setStartTime(null);
        }
        if (StringUtils.isEmpty(paramVo.getEndTime())){
            paramVo.setEndTime(null);
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

        List<ReUserCommissionWithdraw> details = reUserCommissionWithdrawDAO.getAllCommissions(paramVo,bounds);
        List<CommissionListVo> vos = new ArrayList<>(details.size());

        for (ReUserCommissionWithdraw commission : details) {

            CommissionListVo vo = turnVo(commission) ;
            if (vo != null) {
                vos.add(vo);
            }
        }
        return vos;
    }

    /**
     * 实体类转换
     * @param commission
     * @return
     */
    public CommissionListVo turnVo(ReUserCommissionWithdraw commission){

        CommissionListVo vo = null ;
        if (commission != null) {
            vo = new CommissionListVo();
            vo.setId(commission.getId());
            vo.setUserId(commission.getUserId());
            vo.setWithdrawStatus(commission.getWithdrawStatus());
            vo.setApplyMoney(commission.getApplyMoney());
            vo.setApplyTime(commission.getApplyTime());
            String withdrawTime = commission.getWithdrawTime();
            if (StringUtil.isEmpty(withdrawTime)){
                withdrawTime = "";
            }
            vo.setWithdrawTime(withdrawTime);

            if (!StringUtil.isEmpty(commission.getUserId())){
                ReUser user = reUserDAO.selectByPrimaryKey(commission.getUserId());
                String name = user.getNickname();
                if (StringUtil.isEmpty(name)){
                    name ="";
                }
                String phone = user.getMobile();
                if (StringUtil.isEmpty(phone)){
                    phone = "";
                }
                vo.setName(name);
                vo.setPhone(phone);
            }
        }
        return vo;
    }

    /**
     *
     * @param paramVo
     * @return
     * @throws Exception
     */
    public Integer getCommissionNum(CommissionSelectParamVo paramVo) {

        if (StringUtils.isEmpty(paramVo.getStatus())){
            paramVo.setStatus(null);
        }
        if (StringUtils.isEmpty(paramVo.getCellphone())){
            paramVo.setCellphone(null);
        }
        if (StringUtils.isEmpty(paramVo.getStartTime())){
            paramVo.setStartTime(null);
        }
        if (StringUtils.isEmpty(paramVo.getEndTime())){
            paramVo.setEndTime(null);
        }

        return reUserCommissionWithdrawDAO.getCommissionNum(paramVo);

    }

    /**
     * 提现拥挤到用户余额
     * @param request
     * @param id
     * @return
     */
    public String doWithdrawCommission(HttpServletRequest request, Long id){

        Long now = System.currentTimeMillis();

        ReUserCommissionWithdraw commission = reUserCommissionWithdrawDAO.selectByPrimaryKey(id);

        if (commission == null) {
            return JsonUtil.buildError("记录不存在");
        }
        Integer status = commission.getWithdrawStatus();
        if (status == 1){   //已处理
            return JsonUtil.buildError("此记录已提现过,不能重复提现");
        }else if (status == 2){ //已作废
            return JsonUtil.buildError("已作废");
        }else { //可提现

            Integer userId = commission.getUserId();
            if (userId == null) {
                return JsonUtil.buildError("用户不存在");
            }
            ReUser user = reUserDAO.selectLockByUserId(userId);
            if (user == null){
                return JsonUtil.buildError("用户不存在");
            }
            BigDecimal applyMoney = commission.getApplyMoney();
            if (applyMoney == null) {
                return JsonUtil.buildError("提现金额异常");
            }
            BigDecimal userMoney = user.getUserMoney();

            commission.setWithdrawStatus(1);
            commission.setWithdrawTime(ElBase.fmtTime(now));
            //修改提现表,记录状态变为已提现
            reUserCommissionWithdrawDAO.updateByPrimaryKeySelective(commission);

            //修改用户表,把余额加上提现的金额
            user.setUserMoney(userMoney.add(applyMoney));
            user.setUpdateTime(now);
            reUserDAO.updateByPrimaryKeySelective(user);

            //修改用户账户表,刚才提现的金额
            ReAccountDetail accountDetail = new ReAccountDetail();
            accountDetail.setUserId(userId);
            accountDetail.setAppId(user.getAppId());
            accountDetail.setAccountMoney(applyMoney);
            accountDetail.setDetailType(1);
            accountDetail.setMissionType(MissionType.other_mission.val);
            accountDetail.setMissionSubtype(MissionSubtype.commission.val);
            accountDetail.setDetailContent("佣金提现");
            accountDetail.setDetailTime(ElBase.fmtTime(now));
            reAccountDetailDAO.insert(accountDetail);

            //修改用户拥挤明细表,把这个提现记录加入
            ReUserCommissionDetail commissionDetail = new ReUserCommissionDetail();
            commissionDetail.setUserId(userId);
            commissionDetail.setAccountMoney(applyMoney);
            commissionDetail.setDetailContent("佣金提现");
            commissionDetail.setDetailType(0);
            commissionDetail.setDetailTime(ElBase.fmtTime(now));
            reUserCommissionDetailDAO.insert(commissionDetail);


            return JsonUtil.buildSuccess("提现"+applyMoney+"元成功");
        }
    }

    /**
     * 作废
     * @param request
     * @param id
     * @return
     */
    public String doInvalid(HttpServletRequest request,Long id){

        Long now = System.currentTimeMillis();

        ReUserCommissionWithdraw commission = reUserCommissionWithdrawDAO.selectByPrimaryKey(id);

        if (commission == null) {
            return JsonUtil.buildError("记录不存在");
        }
        Integer status = commission.getWithdrawStatus();
        if (status == 1){   //已处理
            return JsonUtil.buildError("此记录已提现过,不能作废");
        }else if (status == 2){ //已作废
            return JsonUtil.buildError("此记录早已是作废记录");
        }else {//可作废

            commission.setWithdrawStatus(2);
            commission.setWithdrawTime(ElBase.fmtTime(now));

            reUserCommissionWithdrawDAO.updateByPrimaryKeySelective(commission);

            return JsonUtil.buildSuccess("作废成功");
        }
    }
}
