package com.envolope.oss.service;

import com.alibaba.fastjson.JSON;
import com.envolope.oss.api.JuheMessage;
import com.envolope.oss.api.JuheMobile;
import com.envolope.oss.config.Config;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReAccountDetailDAO;
import com.envolope.oss.dao.ReUserDAO;
import com.envolope.oss.dao.ReWithdrawBindZfbDAO;
import com.envolope.oss.dao.ReWithdrawDetailDAO;
import com.envolope.oss.enums.MissionSubtype;
import com.envolope.oss.enums.MissionType;
import com.envolope.oss.model.ReAccountDetail;
import com.envolope.oss.model.ReUser;
import com.envolope.oss.model.ReWithdrawBindZfb;
import com.envolope.oss.model.ReWithdrawDetail;
import com.envolope.oss.model.para.WithdrawSelectParamVo;
import com.envolope.oss.model.vo.JuheBack;
import com.envolope.oss.model.vo.JuheMsgBack;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.RandomUtil;
import com.envolope.oss.util.RequestUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import com.wujie.common.sdk.weixin.pay.AutoRetryWeixinPayClient;
import com.wujie.common.sdk.weixin.pay.enums.TransferCheckType;
import com.wujie.common.sdk.weixin.pay.request.mmpaymkttransfers.TransferRequest;
import com.wujie.common.sdk.weixin.pay.response.mmpaymkttransfers.TransferResponse;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jan on 16/8/19.
 * 提现过程
 */
@Service
public class WithdrawService {

    @Autowired
    private ReWithdrawDetailDAO reWithdrawDetailDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReWithdrawBindZfbDAO reWithdrawBindZfbDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired(required = false)
    private AutoRetryWeixinPayClient autoRetryWeixinPayClient;

    /**
     * 提现列表
     *
     * @param paramVo
     * @return
     * @throws Exception
     */
    public List<ReWithdrawDetail> getWithdrawList(WithdrawSelectParamVo paramVo) {

        if (StringUtils.isEmpty(paramVo.getWithdrawType())){
            paramVo.setWithdrawType(null);
        }
        if (StringUtils.isEmpty(paramVo.getWithdrawStatus())){
            paramVo.setWithdrawStatus(null);
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

        List<ReWithdrawDetail> details = reWithdrawDetailDAO.getAllWithdraws(paramVo,bounds);
        for (ReWithdrawDetail detail : details){
            if (detail.getWithdrawTime() == null){
                detail.setWithdrawTime("");
            }
            if (StringUtil.isEmpty(detail.getAccountName())){
                detail.setAccountName("");
            }
        }
        return details;
    }

    /**
     * 记录数
     *
     * @param paramVo
     * @return
     * @throws Exception
     */
    public Integer getWithdrawNum(WithdrawSelectParamVo paramVo) {

        if (StringUtils.isEmpty(paramVo.getWithdrawType())){
            paramVo.setWithdrawType(null);
        }
        if (StringUtils.isEmpty(paramVo.getWithdrawStatus())){
            paramVo.setWithdrawStatus(null);
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

        return reWithdrawDetailDAO.getWithdrawNum(paramVo);

    }

    /**
     * 作废
     *
     * @param detailId
     * @return
     */
    public String doInvalid(Long detailId, String remarks){

        ReWithdrawDetail detail = reWithdrawDetailDAO.selectByPrimaryKey(detailId);
        if (detail == null){
            return JsonUtil.buildError("提现数据不存在");
        }

        detail.setWithdrawStatus(2);

        detail.setRemarks(remarks);

        Long now = System.currentTimeMillis();

        String today = ElBase.fmtTime(now);

        detail.setWithdrawTime(today);

        reWithdrawDetailDAO.updateByPrimaryKey(detail);

        /**
         * 把提现金额返回到账户余额
         */
        BigDecimal money = detail.getApplyMoney();

        Integer userId = detail.getUserId() ;

        ReUser user = reUserDAO.selectLockByUserId(userId);

        user.setUserMoney(user.getUserMoney().add(money));

        user.setUpdateTime(System.currentTimeMillis());

        reUserDAO.updateByPrimaryKeySelective(user);

        //TODO :作弊时,说明情况下要封号
        return JsonUtil.buildSuccess();
    }


    /**
     * 提现
     * @param detailId
     * @return
     */
    public String doWithdraw(HttpServletRequest request, Long detailId) throws Exception {

        ReWithdrawDetail detail = reWithdrawDetailDAO.selectByPrimaryKey(detailId);
        if (detail == null){
            return JsonUtil.buildError("提现数据不存在");
        }
        if (detail.getWithdrawStatus() == 1){
            return JsonUtil.buildError("此提现已完成");
        }
        if (detail.getWithdrawStatus() == 2){
            return JsonUtil.buildError("此数据已作废");
        }

        ReUser reUser = reUserDAO.selectByPrimaryKey(detail.getUserId());
        String mobile = reUser.getMobile();

        /**
         * 申请提现的时候,没有操作此表
         *
         * 所以提现作废的时候也不用处理此表
         *
         * 只有提现成功的时候操作此表
         */
        ReAccountDetail accountDetail = new ReAccountDetail();
        accountDetail.setUserId(detail.getUserId());
        accountDetail.setAppId(reUser.getAppId());
        accountDetail.setAccountMoney(detail.getApplyMoney());
        accountDetail.setMissionType(MissionType.other_mission.val);

        String type = detail.getWithdrawType();
        if (type.contains("zhifubao")){ // 支付宝
            accountDetail.setMissionSubtype(MissionSubtype.zhifubao.val);
            accountDetail.setDetailContent("提现到支付宝");
        }else if (type.contains("weixin")){ // 微信
            accountDetail.setMissionSubtype(MissionSubtype.weixin.val);
            accountDetail.setDetailContent("提现到微信");
        }else if (type.contains("huafei")){ // 话费
            accountDetail.setMissionSubtype(MissionSubtype.huafei.val);
            accountDetail.setDetailContent("话费充值");
        }

        accountDetail.setDetailType(0);
        accountDetail.setDetailTime(ElBase.fmtTime(System.currentTimeMillis()));

        reAccountDetailDAO.insert(accountDetail);

        if (type.contains("zhifubao")){        //支付宝

            return zhifubaoWithdraw(request, detail, mobile);

        }else if (type.contains("weixin")){   //微信

            return weChatWithdraw(request, detail, mobile);

        }else if (type.contains("huafei")){   //话费

            return phoneWithdraw(request, detail, mobile);
        }

        return JsonUtil.buildError("错误的提现类型");

    }


    /**
     * 支付宝 提现
     * @param detail
     * @return
     */
    public String zhifubaoWithdraw (HttpServletRequest request, ReWithdrawDetail detail, String mobile){

        detail.setWithdrawStatus(1);
        detail.setWithdrawTime(ElBase.fmtTime(System.currentTimeMillis()));
        reWithdrawDetailDAO.updateByPrimaryKeySelective(detail);

        int userId = detail.getUserId();

        // 绑定支付宝
        ReWithdrawBindZfb reWithdrawBindZfb = reWithdrawBindZfbDAO.selectByPrimaryKey(userId);
        if(reWithdrawBindZfb == null) { // 未绑定
            reWithdrawBindZfb = new ReWithdrawBindZfb();
            reWithdrawBindZfb.setUserId(userId);
            reWithdrawBindZfb.setAlipayAccount(detail.getWithdrawAccount());
            reWithdrawBindZfb.setFullName(detail.getAccountName());
            reWithdrawBindZfbDAO.insert(reWithdrawBindZfb);
        }

        if(!Config.isDevModel()) { // 非开发模式
            // 提现成功发送短信通知
            try {
                JuheMessage.sendWithdrawMsg("支付宝", detail.getApplyMoney().toString(), mobile, ValueConsts.MSG_TPL_WX_RECHARGE);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return JsonUtil.buildSuccess("提现成功");
    }

    /**
     * 微信 提现
     * @param detail
     * @return
     */
    public String weChatWithdraw (HttpServletRequest request, ReWithdrawDetail detail, String mobile) {

        // 提现金额(元) 转换成 (分)
        double withdrawMoney = detail.getWithdrawMoney().doubleValue() * 100;
        int amount = (int) withdrawMoney;

        if(!Config.isDevModel()) { // 非开发模式

            // 微信企业付款
            TransferRequest transferRequest = new TransferRequest();
            transferRequest.setAmount(amount); // 单位分
            transferRequest.setCheckName(TransferCheckType.NO_CHECK);
            transferRequest.setDesc("余额提现");
            transferRequest.setSpbillCreateIp(RequestUtil.getClientIp(request));
            transferRequest.setPartnerTradeNo(System.currentTimeMillis() + RandomUtil.getRandomNumberString(10));
            transferRequest.setOpenid(detail.getWithdrawAccount());

            TransferResponse transferResponse = autoRetryWeixinPayClient.execute(transferRequest);

            if ("FAIL".equals(transferResponse.getResultCode())) {
                return JsonUtil.buildError(transferResponse.getErrCodeDes());
            }

            // 提现成功发送短信通知
            try {
                JuheMessage.sendWithdrawMsg("微信", detail.getApplyMoney().toString(), mobile, ValueConsts.MSG_TPL_WX_RECHARGE);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        detail.setWithdrawStatus(1);
        detail.setWithdrawTime(ElBase.fmtTime(System.currentTimeMillis()));
        reWithdrawDetailDAO.updateByPrimaryKeySelective(detail);

        return JsonUtil.buildSuccess();
    }

    /**
     * 话费 提现
     * @param detail
     * @return
     */
    public String phoneWithdraw (HttpServletRequest request, ReWithdrawDetail detail, String mobile) throws Exception {

        String phone = detail.getWithdrawAccount();
        BigDecimal money = detail.getWithdrawMoney();
        UUID uuid = UUID.randomUUID();
        String orderCode = uuid.toString().replace("-","");

        //先判断此手机能否充值
        int error_code = JuheMobile.telCheck(phone,money.intValue());

        JuheBack back = null;

        if (error_code == 0){

            if(!Config.isDevModel()) { // 非开发模式

                //若能充值,则立刻充值
                String result = JuheMobile.onlineOrder(phone, money.intValue(), orderCode);
                back = JSON.parseObject(result, JuheBack.class);

                if (back.getError_code() == 0 || back.getError_code() == 100014) {

                    //修改 提现数据 状态,及确认时间
                    detail.setWithdrawStatus(1);
                    detail.setWithdrawTime(ElBase.fmtTime(System.currentTimeMillis()));
                    reWithdrawDetailDAO.updateByPrimaryKeySelective(detail);

                    //充值成功之后要给用户发送一条短信
                    String msgBackStr = JuheMessage.sendMsg(money.toString(), mobile, ValueConsts.MSG_TPL_PHONE_RECHARGE);
                    JuheMsgBack msgBack = JSON.parseObject(msgBackStr, JuheMsgBack.class);
                    //发送短信后返回的描述
                    return JsonUtil.buildSuccess(back.getReason());
                }

            }else {

                return JsonUtil.buildSuccess();

            }

        }
        //返回充值失败的原因
        return JsonUtil.buildError(back.getReason());
    }
}
