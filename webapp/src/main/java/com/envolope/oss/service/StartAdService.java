package com.envolope.oss.service;

import com.envolope.oss.dao.ReStartAdDAO;
import com.envolope.oss.model.ReDiscover;
import com.envolope.oss.model.ReStartAd;
import com.envolope.oss.model.dto.PageDto;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jan on 16/8/16.
 * 启动app的广告
 */
@Service
public class StartAdService {

    @Autowired
    private ReStartAdDAO reStartAdDAO;



    /**
     * 获取列表
     * @return
     */
    public PageDto selectByPage(int pageNo, int pageSize) {
        return reStartAdDAO.selectByPage(pageNo, pageSize);
    }

    /**
     * 删除导航
     * @param adIds
     * @return
     */
    public boolean delete(List<Integer> adIds) {
        for (Integer adId : adIds) {
            ReStartAd ad = reStartAdDAO.selectByPrimaryKey(adId);
            if (ad != null) {
                reStartAdDAO.deleteByPrimaryKey(adId);
            }
        }
        return true;
    }
    /**
     *
     * @param adId
     * @return
     */
    public ReStartAd getDiscoverById(Integer adId) {

        return reStartAdDAO.selectByPrimaryKey(adId);
    }

    /**
     * 保存导航
     * @param request
     * @param ad
     * @return
     * @throws Exception
     */
    public String save(HttpServletRequest request, ReStartAd ad) {

        Integer adId = ad.getAdId();

        if (StringUtils.isEmpty(adId)) { //ID空,则为添加

            reStartAdDAO.insertSelective(ad);

        } else { //有ID,则为修改

            reStartAdDAO.updateByPrimaryKeySelective(ad);

        }

        return JsonUtil.buildSuccess();
    }


}
