package com.envolope.oss.service.entrance;

import com.envolope.oss.dao.ReIndexSortDAO;
import com.envolope.oss.model.ReIndexSort;
import com.envolope.oss.model.dto.PageDto;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Summer on 16/9/18.
 */
@Service
public class IndexSortService {

    @Autowired
    private ReIndexSortDAO reIndexSortDAO;

    /**
     * 获取列表
     * @return
     */
    public PageDto selectByPage(int pageNo, int pageSize){
        return reIndexSortDAO.selectByPage(pageNo, pageSize);
    }


    /**
     * 保存分类
     * @param request
     * @param indexSort
     * @return
     * @throws Exception
     */
    public String save(HttpServletRequest request, ReIndexSort indexSort) {

        Integer sortId = indexSort.getSortId();

        if (StringUtils.isEmpty(sortId)) { //ID空,则为添加

            reIndexSortDAO.insertSelective(indexSort);

        } else { //有ID,则为修改

            reIndexSortDAO.updateByPrimaryKeySelective(indexSort);

        }

        return JsonUtil.buildSuccess();
    }

    /**
     * 删除分类
     * @param sortIds
     * @return
     */
    public boolean delete(List<Integer> sortIds) {
        for (Integer sortId : sortIds) {
            ReIndexSort indexSort = reIndexSortDAO.selectByPrimaryKey(sortId);
            if (indexSort != null) {
                reIndexSortDAO.deleteByPrimaryKey(sortId);
            }
        }
        return true;
    }


    /**
     * 查询分类详情
     * @param sortId
     * @return
     */
    public ReIndexSort getIndexSortById(Integer sortId) {
        return reIndexSortDAO.selectByPrimaryKey(sortId);
    }

}
