package com.envolope.oss.service;

import com.envolope.oss.dao.ReDiscoverBannerDAO;
import com.envolope.oss.model.ReBanner;
import com.envolope.oss.model.ReDiscoverBanner;
import com.envolope.oss.model.dto.PageDto;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/10/9.
 * banner 列表
 */
@Service
public class DiscoverBannerService {

    @Autowired
    private ReDiscoverBannerDAO reDiscoverBannerDAO;


    /**
     * 获取列表
     * @return
     */
    public PageDto selectByPage(int pageNo, int pageSize) {
        return reDiscoverBannerDAO.selectByPage(pageNo, pageSize);
    }

    /**
     * 删除导航
     * @param bannerIds
     * @return
     */
    public boolean delete(List<Integer> bannerIds) {
        for (Integer bannerId : bannerIds) {
            ReDiscoverBanner banner = reDiscoverBannerDAO.selectByPrimaryKey(bannerId);
            if (banner != null) {
                reDiscoverBannerDAO.deleteByPrimaryKey(bannerId);
            }
        }
        return true;
    }
    /**
     *
     * @param bannerId
     * @return
     */
    public ReDiscoverBanner getBannerById(Integer bannerId) {

        return reDiscoverBannerDAO.selectByPrimaryKey(bannerId);
    }

    /**
     * 保存导航
     * @param request
     * @param banner
     * @return
     * @throws Exception
     */
    public String save(HttpServletRequest request, ReDiscoverBanner banner) {

        String maxVersion = banner.getMaxVersion();

        if (StringUtils.isEmpty(maxVersion)){

            maxVersion = "9.9.9";
        }

        banner.setMaxVersion(maxVersion);

        Integer id = banner.getBannerId();

        if (StringUtils.isEmpty(id)) { //ID空,则为添加

            reDiscoverBannerDAO.insertSelective(banner);

        } else { //有ID,则为修改

            reDiscoverBannerDAO.updateByPrimaryKeySelective(banner);

        }

        return JsonUtil.buildSuccess();
    }

}
