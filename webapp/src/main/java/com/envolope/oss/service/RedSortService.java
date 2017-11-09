package com.envolope.oss.service;

import com.envolope.oss.dao.ReSortDAO;
import com.envolope.oss.dao.ReStartAdDAO;
import com.envolope.oss.model.ReSort;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jan on 16/8/16.
 * 红包分类
 */
@Service
public class RedSortService {

    @Autowired
    private ReStartAdDAO reStartAdDAO;
    @Autowired
    private ReSortDAO reSortDAO;


    /**
     * 获取列表
     * @param request
     * @return
     */
    public List<ReSort> getAllReSorts (HttpServletRequest request){

        return reSortDAO.getAllReSorts();
    }

    /**
     * delete navigation by id1&id2&...
     * @param request
     * @param sortIds
     * @return
     */
    public String doDeleteRedSorts(HttpServletRequest request,String sortIds) {


        int n = 0 ;

        if (!StringUtils.isEmpty(sortIds)) {
            String[] sorts = sortIds.split("&");
            List<String> sortList = Arrays.asList(sorts);

            for (String sort : sortList) {
                Integer sortId = Integer.valueOf(sort);
                ReSort reSort = reSortDAO.selectByPrimaryKey(sortId);
                if (reSort != null) {
                    reSortDAO.deleteByPrimaryKey(sortId);
                    n ++ ;
                }
            }
        }

        return JsonUtil.buildSuccess("成功删除"+n+"条纪录");
    }

    /**
     *
     * @param sortId
     * @return
     */
    public ReSort getRedSortById(Integer sortId) {

        return reSortDAO.selectByPrimaryKey(sortId);
    }


    /**
     * 修改 或者 添加 导航
     * @param request
     * @param sort
     * @return
     * @throws Exception
     */
    public String doSaveOrUpdateRedSort (HttpServletRequest request, ReSort sort){

        Integer sortId = sort.getSortId();

        if (StringUtils.isEmpty(sortId)) { //ID空,则为添加

            reSortDAO.insertSelective(sort);

            return JsonUtil.buildSuccess();

        }else { //有ID,则为修改

            reSortDAO.updateByPrimaryKeySelective(sort);
            return JsonUtil.buildSuccess();
        }
    }

}
