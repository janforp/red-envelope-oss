package com.envolope.oss.service.mission;

import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.RePasswordRedDAO;
import com.envolope.oss.model.RePasswordRed;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/11/21.
 * 口令红包
 */
@Service
public class PasswordService {
    @Autowired
    private RePasswordRedDAO rePasswordRedDAO;

    /**
     * 总数,分页用
     * @return
     */
    public Integer getNum(HttpServletRequest request){
        return rePasswordRedDAO.getNum();
    }

    /**
     * 列表查询
     * @param request
     * @return
     */
    public List<RePasswordRed> getList(HttpServletRequest request, Integer pageNum){
        int offset = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offset,ValueConsts.PAGE_SIZE);
        List<RePasswordRed> details = rePasswordRedDAO.getList(bounds);
        return details;
    }


    /**
     * 添加或修改任务
     * @param request
     * @param red
     * @return
     */
    public String save(HttpServletRequest request,RePasswordRed red){

        Long redId = red.getId();
        red.setUpdateTime(ElBase.fmtTime(System.currentTimeMillis()));
        if (StringUtil.isEmpty(redId)){
            red.setCreateTime(ElBase.fmtTime(System.currentTimeMillis()));
            rePasswordRedDAO.insertSelective(red);
        }else {
            rePasswordRedDAO.updateByPrimaryKeySelective(red);
        }
        return JsonUtil.buildSuccess();
    }

    /**
     * 修改数据获取
     * @param request
     * @param redId
     * @return
     */
    public RePasswordRed modifyData(HttpServletRequest request,Long redId){

        RePasswordRed red = rePasswordRedDAO.selectByPrimaryKeyAndLock(redId);
        return red;
    }

}

