package com.envolope.oss.service.mission;

import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReAndriodIntegralWallDAO;
import com.envolope.oss.model.ReAndriodIntegralWall;
import com.envolope.oss.model.para.SelfIntegralSelectParam;
import com.envolope.oss.model.vo.mission.SelfIntegralListVo;
import com.envolope.oss.util.EntityUtil;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 2016/12/3.
 * 自己的积分墙  re_andriod_integral_wall
 */
@Service
public class SelfIntegralService {

    @Autowired
    private ReAndriodIntegralWallDAO reAndriodIntegralWallDAO;

    /**
     * 分页查询出数据
     * @param param
     * @return
     */
    public List<SelfIntegralListVo> getList(SelfIntegralSelectParam param){

        param = EntityUtil.emptyStringToNull(param);
        Integer pageNum = param.getPageNum();
        if (StringUtil.isEmpty(pageNum)){
            param.setPageNum(1);
        }
        int offset = param.getPageNum() - 1;

        RowBounds bounds = new RowBounds(offset, ValueConsts.PAGE_SIZE);

        return reAndriodIntegralWallDAO.getList(param,bounds);
    }


    /**
     * 获取总数
     * @param param
     * @return
     */
    public int getNum(SelfIntegralSelectParam param){

        param = EntityUtil.emptyStringToNull(param);

        return reAndriodIntegralWallDAO.getNum(param);
    }

    /**
     * 根据id找到积分墙任务
     * @param wallId
     * @return
     */
    public ReAndriodIntegralWall getWall(Long wallId){

        return reAndriodIntegralWallDAO.selectByPrimaryKey(wallId);
    }

    /**
     * 保存
     * @param request
     * @param wall
     * @return
     */
    public String save(HttpServletRequest request,ReAndriodIntegralWall wall){

        Integer totalNum = wall.getTotalNum();
        Integer leftNum = wall.getLeftNum();

        Long wallId = wall.getWallId();
        if (StringUtil.isEmpty(wallId)){//添加

            wall.setUpdateTime(ElBase.fmtTime(System.currentTimeMillis()));
            wall.setCreateTime(ElBase.fmtTime(System.currentTimeMillis()));

            Integer isLimitNum = wall.getIsLimitNum();
            if (isLimitNum == 0){//不限量
                wall.setTotalNum(null);
                wall.setLeftNum(null);
            }

            reAndriodIntegralWallDAO.insertSelective(wall);
        }else {

            wall.setUpdateTime(ElBase.fmtTime(System.currentTimeMillis()));
            wall.setTotalNum(null);
            wall.setLeftNum(null);
            wall.setIsLimitNum(null);

            reAndriodIntegralWallDAO.updateByPrimaryKeySelective(wall);
        }

        return JsonUtil.buildSuccess();
    }

    /**
     * 针对某个积分墙任务更新下载apk包的链接
     * @param wallId
     * @param apkUrl
     * @return
     */
    public String updateApk(Long wallId,String apkUrl){

        ReAndriodIntegralWall wall = reAndriodIntegralWallDAO.selectByPrimaryKey(wallId);
        wall.setAppUrl(apkUrl);
        wall.setUpdateTime(ElBase.fmtTime(System.currentTimeMillis()));

        reAndriodIntegralWallDAO.updateByPrimaryKeySelective(wall);

        return JsonUtil.buildSuccess("上传APK成功");
    }

}
