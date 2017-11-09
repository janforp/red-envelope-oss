package com.envolope.oss.service.userManager;

import com.envolope.oss.dao.*;
import com.envolope.oss.model.ReUser;
import com.envolope.oss.model.vo.user.UserListDetailVo;
import com.envolope.oss.model.vo.user.UserSelectParamVo;
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
 * Created by Jan on 16/10/13.
 * 用户列表
 */
@Service
public class UserListService {

    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReScoreDetailDAO reScoreDetailDAO ;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO ;


    /**
     * 处理查询参数
     * @return
     */
    public UserSelectParamVo handleParamVo(UserSelectParamVo paramVo){

        if (StringUtils.isEmpty(paramVo.getPageSize()) || paramVo.getPageSize() == 0){
            paramVo.setPageSize(10);
        }
        if (StringUtils.isEmpty(paramVo.getPageNum()) || paramVo.getPageNum() == 0) {
            paramVo.setPageNum(1);
        }
        String nickname = paramVo.getNickname();
        if (StringUtil.isEmpty(nickname)){
            paramVo.setNickname(null);
        }
        String  phone = paramVo.getPhone();
        if (StringUtil.isEmpty(phone)){
            paramVo.setPhone(null);
        }
        String start = paramVo.getStartDate();
        if (StringUtil.isEmpty(start)){
            paramVo.setStartDate(null);
        }
        String end = paramVo.getEndDate();
        if (StringUtil.isEmpty(end)){
            paramVo.setEndDate(null);
        }

        return paramVo;
    }



    /**
     * 总数,分页用
     * @param paramVo
     * @return
     */
    public Integer getNum(HttpServletRequest request, UserSelectParamVo paramVo){
        paramVo = handleParamVo(paramVo);
        return reUserDAO.getNum(paramVo);
    }

    /**
     * 列表查询
     * @param request
     * @param paramVo
     * @return
     */
    public List<UserListDetailVo> getList(HttpServletRequest request, UserSelectParamVo paramVo){

        paramVo = handleParamVo(paramVo);

        int offset = (paramVo.getPageNum() - 1) * paramVo.getPageSize() ;
        RowBounds bounds = new RowBounds(offset,paramVo.getPageSize());

        List<ReUser> details = reUserDAO.getList(paramVo,bounds);

        List<UserListDetailVo> vos = new ArrayList<>(details.size());

        for (ReUser detail : details) {

            UserListDetailVo vo = turnVo(detail,paramVo);

            vos.add(vo);
        }

        return vos;
    }

    /**
     * 实体类转换
     * @param user
     * @return
     */
    public UserListDetailVo turnVo(ReUser user, UserSelectParamVo paramVo){

        UserListDetailVo vo = null ;

        if (user != null){

            vo = new UserListDetailVo();

            vo.setUserId(user.getUserId());

            String nickname = user.getNickname();
            if (StringUtil.isEmpty(nickname)){
                nickname = "";
            }
            vo.setNickname(nickname);

            String  phone = user.getMobile();
            if (StringUtil.isEmpty(phone)){
                phone = "";
            }
            vo.setMobile(phone);

            Long createTime = user.getCreateTime();
            if (! StringUtil.isEmpty(createTime)){

                String registerTime = ElBase.fmtDay(createTime);
                vo.setRegisterTime(registerTime);
                vo.setCreateTime(createTime);
            }

            Integer score = user.getUserScore();
            if (score == null) {
                score = 0 ;
            }
            vo.setUserScore(score);

            List<Integer> userId = new ArrayList<>(1);
            userId.add(user.getUserId());

            String today =ElBase.fmtDay(System.currentTimeMillis());

            //此用户今天获取的积分
            Integer userGetScore = reScoreDetailDAO.getTotalScoresByUserIds(userId,1,today,today) ;
            if (userGetScore == null) {
                userGetScore = 0;
            }
            vo.setUserGetScore(userGetScore);


            BigDecimal money = user.getUserMoney();
            if (money == null){
                money = new BigDecimal("0.00") ;
            }
            vo.setUserMoney(money);

            BigDecimal getMoney = reAccountDetailDAO.getTotalMoneyByUserIds(userId,1,today,today) ;
            if (getMoney == null){
                getMoney = new BigDecimal("0.00");
            }
            vo.setUserGetMoney(getMoney);

        }
        return vo;
    }

    /**
     * 拉黑
     * @param request
     * @param id
     * @return
     */
    public String doPullBlack(HttpServletRequest request, Integer id){

        if (id != null) {

            ReUser user = reUserDAO.selectLockByUserId(id);

            if (user == null){
                return JsonUtil.buildError("用户不存在");
            }

            if (user.getUserStatus() == 0){
                return JsonUtil.buildError("该用户早已在黑名单");
            }

            user.setUserStatus(0);
            user.setUpdateTime(System.currentTimeMillis());

            reUserDAO.updateByPrimaryKeySelective(user);

            return JsonUtil.buildSuccess("已成功将此用户拉黑");
        }

        return JsonUtil.buildError("请选择用户");

    }



}
