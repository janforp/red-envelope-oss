package com.envolope.oss.service;

import com.envolope.oss.dao.ReDiscoverDAO;
import com.envolope.oss.model.ReDiscover;
import com.envolope.oss.model.dto.PageDto;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/8/15.
 * 发现
 */
@Service
public class DiscoverService {

    @Autowired
    private ReDiscoverDAO reDiscoverDAO;
    
    /**
     * 获取列表
     * @return
     */
    public PageDto selectByPage(int pageNo, int pageSize) {
        return reDiscoverDAO.selectByPage(pageNo, pageSize);
    }

    /**
     * 删除发现
     * @param discoverIds
     * @return
     */
    public boolean delete(List<Integer> discoverIds) {
        for (Integer discoverId : discoverIds) {
            ReDiscover discover = reDiscoverDAO.selectByPrimaryKey(discoverId);
            if (discover != null) {
                reDiscoverDAO.deleteByPrimaryKey(discoverId);
            }
        }
        return true;
    }

    /**
     * 查询发现信息
     * @param discoverId
     * @return
     */
    public ReDiscover getDiscoverById(Integer discoverId) {

        return reDiscoverDAO.selectByPrimaryKey(discoverId);
    }

    /**
     * 保存发现
     * @param request
     * @param discover
     * @return
     * @throws Exception
     */
    public String save(HttpServletRequest request, ReDiscover discover) {

        String maxVersion = discover.getMaxVersion();

        if (StringUtils.isEmpty(maxVersion)){

            maxVersion = "9.9.9";
        }

        discover.setMaxVersion(maxVersion);

        Integer discoverId = discover.getDiscoverId();

        if (StringUtils.isEmpty(discoverId)) { //ID空,则为添加

            reDiscoverDAO.insertSelective(discover);

        } else { //有ID,则为修改

            reDiscoverDAO.updateByPrimaryKeySelective(discover);

        }

        return JsonUtil.buildSuccess();
    }


}
