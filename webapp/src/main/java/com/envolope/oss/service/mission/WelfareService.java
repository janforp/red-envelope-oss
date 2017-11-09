package com.envolope.oss.service.mission;

import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReWelfareDAO;
import com.envolope.oss.dao.ReWelfareTypeDAO;
import com.envolope.oss.model.ReWelfare;
import com.envolope.oss.model.ReWelfareType;
import com.envolope.oss.model.para.ArticleSelectParamVo;
import com.envolope.oss.model.para.WelfareSelectParamVo;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Janita on 2016/12/12.
 * 福利
 */
@Service
public class WelfareService {

    @Autowired
    private ReWelfareDAO reWelfareDAO;
    @Autowired
    private ReWelfareTypeDAO reWelfareTypeDAO;

    /**
     * 保存／修改
     * @param welfare
     * @return
     */
    public String save(ReWelfare welfare){

        long now = System.currentTimeMillis();
        String timeNow = ElBase.fmtTime(now);

        welfare.setUpdateTime(timeNow);

        Long welfareId = welfare.getWelfareId();

        if (StringUtil.isEmpty(welfareId)){

            welfare.setCreateTime(timeNow);

            reWelfareDAO.insertSelective(welfare);
        }else{
            reWelfareDAO.updateByPrimaryKeySelective(welfare);
        }

        return JsonUtil.buildSuccess();
    }

    /**
     * 根据id查询
     * 不允许修改次数，否则需要lock
     * @param welfareId
     * @return
     */
    public ReWelfare getWelfare(long welfareId){

        return reWelfareDAO.selectByPrimaryKey(welfareId);
    }

    /**
     * 获得数量
     * @param param
     * @return
     */
    public Integer getNum(WelfareSelectParamVo param){

        return reWelfareDAO.getNum(param);
    }

    /**
     * 列表，分页查询
     * @param param
     * @return
     */
    public List<ReWelfare> getList(WelfareSelectParamVo param){

        int offset = (param.getPageNum() -1)* ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offset, ValueConsts.PAGE_SIZE);

        List<ReWelfare> welfares = reWelfareDAO.getList(param,bounds);

        return reWelfareDAO.getList(param,bounds);
    }

    /**
     * 获取福利所以的类型
     * @return
     */
    public List<ReWelfareType> getTypes(){

        return reWelfareTypeDAO.getTypes();
    }

    /**
     * 获取子类型
     * @param id
     * @return
     */
    public String getSubTypeById(int id){
        ReWelfareType type = reWelfareTypeDAO.selectByPrimaryKey(id);
        return type.getSubTypeName();
    }
}
