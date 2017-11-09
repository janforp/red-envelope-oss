package com.envolope.oss.service;

import com.envolope.oss.dao.ReFixedRedDAO;
import com.envolope.oss.dao.ReWithdrawSortDAO;
import com.envolope.oss.model.ReFixedRed;
import com.envolope.oss.model.ReWithdrawSort;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jan on 16/8/18.
 *
 * 支付分类
 */
@Service
public class WithdrawSortService {

    @Autowired
    private ReFixedRedDAO reFixedRedDAO;
    @Autowired
    private ReWithdrawSortDAO reWithdrawSortDAO;

    /**
     * 获取列表
     * @param request
     * @return
     */
    public List<ReWithdrawSort> getAllWithdraws (HttpServletRequest request){

        return reWithdrawSortDAO.getAllWithdraws();
    }

    /**
     * delete navigation by id1&id2&...
     * @param request
     * @param withdrawIds
     * @return
     */
    public String doDeleteWithdraws(HttpServletRequest request,String withdrawIds) {


        int n = 0 ;

        if (!StringUtils.isEmpty(withdrawIds)) {
            String[] withdraws = withdrawIds.split("&");
            List<String> withdrawList = Arrays.asList(withdraws);

            for (String withdraw : withdrawList) {
                Integer withdrawId = Integer.valueOf(withdraw);
                ReWithdrawSort reWithdrawSort = reWithdrawSortDAO.selectByPrimaryKey(withdrawId);
                if (reWithdrawSort != null) {
                    reWithdrawSortDAO.deleteByPrimaryKey(withdrawId);
                    n ++ ;
                }
            }
        }

        return JsonUtil.buildSuccess("成功删除"+n+"条纪录");
    }

    /**
     *
     * @param withdrawId
     * @return
     */
    public ReWithdrawSort getWithdrawById(Integer withdrawId) {

        return reWithdrawSortDAO.selectByPrimaryKey(withdrawId);
    }


    /**
     * 修改 或者 添加 导航
     * @param request
     * @param sort
     * @return
     * @throws Exception
     */
    public String doSaveOrUpdateWithdraw (HttpServletRequest request, ReWithdrawSort sort){

        Integer withdrawSortId = sort.getWithdrawId();

        if (StringUtils.isEmpty(withdrawSortId)) { //ID空,则为添加

            reWithdrawSortDAO.insertSelective(sort);

            return JsonUtil.buildSuccess();

        }else { //有ID,则为修改

            reWithdrawSortDAO.updateByPrimaryKeySelective(sort);
            return JsonUtil.buildSuccess();
        }
    }
}
