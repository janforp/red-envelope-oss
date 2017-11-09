package com.envolope.oss.service;

import com.envolope.oss.consts.AttributeConsts;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReCustomerDAO;
import com.envolope.oss.dao.ReUserRecordDAO;
import com.envolope.oss.model.ReCustomer;
import com.envolope.oss.model.ReUserRecord;
import com.envolope.oss.model.vo.LoginAdminInfo;
import com.envolope.oss.model.vo.UserListVo;
import com.envolope.oss.util.TimeStampUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/7/15.
 *
 * 客户操作
 */
@Service
public class ClientService {


    @Autowired
    private ReUserRecordDAO reUserRecordDAO;
    @Autowired
    private ReCustomerDAO reCustomerDAO;


    /**
     * 获取登陆名
     * @param request
     * @return
     */
    public String  getLoginNameFromRequest (HttpServletRequest request) {

        HttpSession session = request.getSession();
        LoginAdminInfo o = (LoginAdminInfo) session.getAttribute(AttributeConsts.SESSION_ADMIN);
        return o.getLoginName();
    }


    /**
     * 取到 某个 微信号 推广用户的 列表
     *
     * @param request
     * @param name
     * @return
     */
    public List<UserListVo> getUserInfoOfLoginName(HttpServletRequest request, String name, Integer pageNum) {


        ReCustomer customer = reCustomerDAO.selectByName (name);
        if (customer != null){
            Integer customerId = customer.getCustomerId();
            int offSet = (pageNum - 1) * ValueConsts.PAGE_SIZE;
            RowBounds bounds = new RowBounds(offSet, ValueConsts.PAGE_SIZE);

            List<ReUserRecord> list = reUserRecordDAO.getAllAttentionMemberOfCustomerId(customerId, bounds);

            List<UserListVo> vos = turnReUserRecordToUserListVo(list);

            return vos;
        }
        return null;
    }

    /**
     * 取到 某个 用户名 推广用户的 列表 总数 用于分页
     *
     * @param request
     * @param name
     * @return
     */
    public Integer getUserInfoOfLoginNameNum(HttpServletRequest request, String name) {

        ReCustomer customer = reCustomerDAO.selectByName (name);

        if (customer != null){
            Integer customerId = customer.getCustomerId();
            return reUserRecordDAO.getAllAttentionMemberOfCustomerIdNum(customerId);
        }else {
            throw new RuntimeException("客户不存在");
        }
    }

    /**
     * 把 ReUserRecord 转换 为 UserListVo
     * @param list
     * @return
     */
    public List<UserListVo> turnReUserRecordToUserListVo (List<ReUserRecord> list) {

        List<UserListVo> vos = new ArrayList<>(list.size());

        for (ReUserRecord re : list) {

            UserListVo vo = new UserListVo();
            vo.setUserNickname(re.getUserNickname());
            String img = re.getUserImg();
            if (img == null) {
                img = "";
            }
            vo.setUserImg(img);
            Integer time = re.getCreateTime();

            String showTime = ElBase.fmt10Time(time);
            vo.setShowTime(showTime);

            vos.add(vo);
        }

        return vos;
    }

    /**
     * 获取某个 微信号 今天关注的 人员 列表
     *
     * @param request
     * @param pageNum
     * @return
     */
    public List<UserListVo> getUserInfoTodayOfLoginName(HttpServletRequest request, String customerName, Integer pageNum) {


        ReCustomer customer = reCustomerDAO.selectByName (customerName);

        Integer customerId = customer.getCustomerId();

        int offSet = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offSet, ValueConsts.PAGE_SIZE);



        List<Integer> timeScope = getTimeStampToday();

        List<ReUserRecord> list = reUserRecordDAO.getAllAttentionMemberTodayOfCustomerId(customerId, timeScope, bounds);

        List<UserListVo> vos = turnReUserRecordToUserListVo(list);

        return vos;
    }

    /**
     * 获取某个 微信号 今天关注的 人员 列表 总数
     *
     * @param request
     * @param customerName
     * @return
     */
    public Integer getUserInfoTodayOfLoginName(HttpServletRequest request, String customerName) {

        List<Integer> timeScope = getTimeStampToday();


        ReCustomer customer = reCustomerDAO.selectByName (customerName);

        Integer customerId = customer.getCustomerId();

        return reUserRecordDAO.getAllAttentionMemberTodayOfCustomerIdNum(customerId, timeScope);

    }

    /**
     * 获取今天0 点到 明天0 点之间的 10位时间戳
     *
     * @return
     */
    public List<Integer> getTimeStampToday() {

        Integer begin = TimeStampUtil.getTimesmorning();
        Integer end = TimeStampUtil.getTimesnight();
        List<Integer> timeScope = new ArrayList<>(2);
        timeScope.add(begin);
        timeScope.add(end);

        return timeScope;
    }
}
