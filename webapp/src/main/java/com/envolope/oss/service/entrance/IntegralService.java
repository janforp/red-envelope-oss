package com.envolope.oss.service.entrance;

import com.envolope.oss.dao.ReIntegralWallDAO;
import com.envolope.oss.model.ReIntegralWall;
import com.envolope.oss.model.dto.PageDto;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/10/12.
 * 积分墙管理
 */
@Service
public class IntegralService {

    @Autowired
    private ReIntegralWallDAO reIntegralWallDAO;


    /**
     * 获取列表
     * @return
     */
    public PageDto selectByPage(int pageNo, int pageSize) {
        return reIntegralWallDAO.selectByPage(pageNo, pageSize);
    }

    /**
     * 删除
     * @param wallIds
     * @return
     */
    public boolean delete(List<Integer> wallIds) {
        for (Integer wallId : wallIds) {
            ReIntegralWall wall = reIntegralWallDAO.selectByPrimaryKey(wallId);
            if (wall != null) {
                reIntegralWallDAO.deleteByPrimaryKey(wallId);
            }
        }
        return true;
    }
    /**
     *
     * @param wallId
     * @return
     */
    public ReIntegralWall getWallById(Integer wallId) {

        return reIntegralWallDAO.selectByPrimaryKey(wallId);
    }

    /**
     * 保存
     * @param request
     * @param wall
     * @return
     * @throws Exception
     */
    public String save(HttpServletRequest request, ReIntegralWall wall) {

        Integer id = wall.getWallId();

        if (StringUtils.isEmpty(id)) { //ID空,则为添加

            reIntegralWallDAO.insertSelective(wall);

        } else { //有ID,则为修改

            reIntegralWallDAO.updateByPrimaryKeySelective(wall);

        }

        return JsonUtil.buildSuccess();
    }
}
