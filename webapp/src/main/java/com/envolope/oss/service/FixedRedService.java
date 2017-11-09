package com.envolope.oss.service;

import com.envolope.oss.dao.ReFixedRedDAO;
import com.envolope.oss.model.ReFixedRed;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jan on 16/8/16.
 *
 * 定时红包
 */
@Service
public class FixedRedService {

    @Autowired
    private ReFixedRedDAO reFixedRedDAO;

    /**
     * 获取列表
     * @param request
     * @return
     */
    public List<ReFixedRed> getAllFixedReds (HttpServletRequest request){

        return reFixedRedDAO.getAllFixedReds();
    }

    /**
     * delete navigation by id1&id2&...
     * @param request
     * @param fixedIds
     * @return
     */
    public String doDeleteFixedReds(HttpServletRequest request,String fixedIds) {


        int n = 0 ;

        if (!StringUtils.isEmpty(fixedIds)) {
            String[] fixeds = fixedIds.split("&");
            List<String> fixList = Arrays.asList(fixeds);

            for (String fix : fixList) {
                Integer fixId = Integer.valueOf(fix);
                ReFixedRed reFixedRed = reFixedRedDAO.selectByPrimaryKey(fixId);
                if (reFixedRed != null) {
                    reFixedRedDAO.deleteByPrimaryKey(fixId);
                    n ++ ;
                }
            }
        }

        return JsonUtil.buildSuccess("成功删除"+n+"条纪录");
    }

    /**
     *
     * @param fixId
     * @return
     */
    public ReFixedRed getFixedRedById(Integer fixId) {

        return reFixedRedDAO.selectByPrimaryKey(fixId);
    }


    /**
     * 修改 或者 添加 导航
     * @param request
     * @param red
     * @return
     * @throws Exception
     */
    public String doSaveOrUpdateFixedRed (HttpServletRequest request, ReFixedRed red){

        Integer fixedId = red.getFixedId();

        if (StringUtils.isEmpty(fixedId)) { //ID空,则为添加

            reFixedRedDAO.insertSelective(red);

            return JsonUtil.buildSuccess();

        }else { //有ID,则为修改

            reFixedRedDAO.updateByPrimaryKeySelective(red);
            return JsonUtil.buildSuccess();
        }
    }

}
