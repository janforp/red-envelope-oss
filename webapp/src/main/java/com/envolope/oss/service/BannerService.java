package com.envolope.oss.service;

import com.envolope.oss.dao.ReBannerDAO;
import com.envolope.oss.model.ReBanner;
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
 * Created by Jan on 16/8/12.
 * banner 列表
 */
@Service
public class BannerService {

    @Autowired
    private ReBannerDAO reBannerDAO;


    /**
     * 获取列表
     * @return
     */
    public PageDto selectByPage(int pageNo, int pageSize) {
        return reBannerDAO.selectByPage(pageNo, pageSize);
    }

    /**
     * 删除导航
     * @param bannerIds
     * @return
     */
    public boolean delete(List<Integer> bannerIds) {
        for (Integer bannerId : bannerIds) {
            ReBanner banner = reBannerDAO.selectByPrimaryKey(bannerId);
            if (banner != null) {
                reBannerDAO.deleteByPrimaryKey(bannerId);
            }
        }
        return true;
    }
    /**
     *
     * @param bannerId
     * @return
     */
    public ReBanner getBannerById(Integer bannerId) {

        return reBannerDAO.selectByPrimaryKey(bannerId);
    }

    /**
     * 保存导航
     * @param request
     * @param banner
     * @return
     * @throws Exception
     */
    public String save(HttpServletRequest request, ReBanner banner) {

        String maxVersion = banner.getMaxVersion();

        if (StringUtils.isEmpty(maxVersion)){

            maxVersion = "9.9.9";
        }

        banner.setMaxVersion(maxVersion);

        Integer id = banner.getBannerId();

        if (StringUtils.isEmpty(id)) { //ID空,则为添加

            reBannerDAO.insertSelective(banner);

        } else { //有ID,则为修改

            reBannerDAO.updateByPrimaryKeySelective(banner);

        }

        return JsonUtil.buildSuccess();
    }
}
