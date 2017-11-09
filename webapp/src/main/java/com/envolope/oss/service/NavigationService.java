package com.envolope.oss.service;

import com.envolope.oss.dao.ReNavigationDAO;
import com.envolope.oss.model.OssAdmin;
import com.envolope.oss.model.ReNavigation;
import com.envolope.oss.model.dto.PageDto;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jan on 16/8/15.
 *
 * 导航
 */
@Service
public class NavigationService {

    @Autowired
    private ReNavigationDAO reNavigationDAO;

    /**
     * 获取列表
     * @return
     */
    public PageDto selectByPage(int pageNo, int pageSize) {
        return reNavigationDAO.selectByPage(pageNo, pageSize);
    }

    /**
     * 查询导航信息
     * @param id
     * @return
     */
    public ReNavigation selectNavigation(Integer id) {
        return reNavigationDAO.selectByPrimaryKey(id);
    }

    /**
     * 保存导航
     * @param request
     * @param navigation
     * @return
     * @throws Exception
     */
    public String save(HttpServletRequest request, ReNavigation navigation){

        String maxVersion = navigation.getMaxVersion();

        if (StringUtils.isEmpty(maxVersion)){

            maxVersion = "9.9.9";
        }

        navigation.setMaxVersion(maxVersion);

        Integer navigationId = navigation.getNavigationId();

        if (StringUtils.isEmpty(navigationId)) { //ID空,则为添加

            reNavigationDAO.insertSelective(navigation);

        }else { //有ID,则为修改

            reNavigationDAO.updateByPrimaryKeySelective(navigation);

        }

        return JsonUtil.buildSuccess();

    }

    /**
     * 删除导航
     * @param navigationIds
     * @return
     */
    public boolean delete(List<Integer> navigationIds) {
        for (Integer navigationId : navigationIds) {
            ReNavigation navigation = reNavigationDAO.selectByPrimaryKey(navigationId);
            if (navigation != null) {
                reNavigationDAO.deleteByPrimaryKey(navigationId);
            }
        }
        return true;
    }

}
