package com.envolope.oss.service.integralWall;

import com.envolope.oss.dao.ReAppDAO;
import com.envolope.oss.dao.ReAppMarketDAO;
import com.envolope.oss.model.ReApp;
import com.envolope.oss.model.ReAppMarket;
import com.envolope.oss.model.vo.integralWall.AppListVo;
import com.envolope.oss.model.vo.integralWall.AppSelectParamVo;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/10/14.
 * 积分墙管理
 */
@Service
public class IntegralWallService {

    @Autowired
    private ReAppDAO reAppDAO;
    @Autowired
    private ReAppMarketDAO reAppMarketDAO;

    /**
     * 获取市场
     * @return
     */
    public List<ReAppMarket> getMarketList(){

        return reAppMarketDAO.getMarketList();
    }

    /**
     * 处理查询参数
     * @return
     */
    public AppSelectParamVo handleParamVo(AppSelectParamVo paramVo){

        if (StringUtils.isEmpty(paramVo.getPageSize()) || paramVo.getPageSize() == 0){
            paramVo.setPageSize(10);
        }
        if (StringUtils.isEmpty(paramVo.getPageNum()) || paramVo.getPageNum() == 0) {
            paramVo.setPageNum(1);
        }

        String appName = paramVo.getName();
        if (StringUtil.isEmpty(appName)){
            appName = null;
        }
        paramVo.setName(appName);

        Integer market = paramVo.getMarketId();
        if (StringUtil.isEmpty(market)){
            market = null;
        }
        paramVo.setMarketId(market);

        String packageName = paramVo.getPackageName();
        if (StringUtil.isEmpty(packageName)){
            packageName = null;
        }
        paramVo.setPackageName(packageName);

        String startDate = paramVo.getStartReleaseTime();
        if (StringUtil.isEmpty(startDate)){
            startDate = null;
        }
        paramVo.setStartReleaseTime(startDate);

        String endDate = paramVo.getEndReleaseTime();
        if (StringUtil.isEmpty(endDate)){
            endDate = null;
        }
        paramVo.setEndReleaseTime(endDate);


        return paramVo;
    }


    /**
     * 总数,分页用
     * @param paramVo
     * @return
     */
    public Integer getNum(HttpServletRequest request, AppSelectParamVo paramVo){

        paramVo = handleParamVo(paramVo);

        return reAppDAO.getNum(paramVo);
    }

    /**
     * 列表查询
     * @param request
     * @param paramVo
     * @return
     */
    public List<AppListVo> getList(HttpServletRequest request, AppSelectParamVo paramVo){

        paramVo = handleParamVo(paramVo);

        int offset = (paramVo.getPageNum() - 1) * paramVo.getPageSize() ;
        RowBounds bounds = new RowBounds(offset,paramVo.getPageSize());

        List<ReApp> details = reAppDAO.getList(paramVo,bounds);

        List<AppListVo> vos = new ArrayList<>(details.size());

        for (ReApp app : details){

            AppListVo vo = turnVo(app);

            vos.add(vo);
        }

        return vos;
    }

    public AppListVo turnVo(ReApp app){

        AppListVo vo = null;
        if (app != null){
            vo = new AppListVo();

            vo.setAppId(app.getAppId());
            vo.setAppName(app.getAppName());
            vo.setAppIcon(app.getAppIcon());
            vo.setAppPackage(app.getAppPackage());
            vo.setAppSize(app.getAppSize());

            Integer marketId = app.getMarketId() ;

            ReAppMarket market = reAppMarketDAO.selectByPrimaryKey(marketId);
            String marketName = market.getMarketName();
            vo.setMarketName(marketName);
        }

        return vo;

    }

    /**
     * 修改时,获取原来的数据
     * @param id
     * @return
     */
    public ReApp getAppById(Long id){

        return reAppDAO.selectByPrimaryKey(id);
    }

    /**
     * 添加或者修改
     * @param request
     * @param app
     * @return
     */
    public String  save(HttpServletRequest request, ReApp app){

        Long id = app.getAppId();

        if (StringUtils.isEmpty(id)) { //ID空,则为添加

            app.setCreateTime(ElBase.fmtDay(System.currentTimeMillis()));

            reAppDAO.insertSelective(app);

        } else { //有ID,则为修改

            reAppDAO.updateByPrimaryKeySelective(app);

        }

        return JsonUtil.buildSuccess();
    }

    /**
     * 删除
     * @param appIds
     * @return
     */
    public boolean delete(List<Long> appIds) {
        for (Long appId : appIds) {
            ReApp app = reAppDAO.selectByPrimaryKey(appId);
            if (app != null) {
                reAppDAO.deleteByPrimaryKey(appId);
            }
        }
        return true;
    }
}
