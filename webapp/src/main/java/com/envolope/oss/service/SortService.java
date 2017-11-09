package com.envolope.oss.service;

import com.envolope.oss.dao.ReMissionSortDAO;
import com.envolope.oss.model.ReMission;
import com.envolope.oss.model.ReMissionSort;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jan on 16/8/11.
 * 任务管理
 */
@Service
public class SortService {

    @Autowired
    private ReMissionSortDAO reMissionSortDAO;

    /**
     * 取得所有的分类信息
     * @param request
     * @return
     */
    public List<ReMissionSort> getAllSort(HttpServletRequest request) {

        return reMissionSortDAO.getAllSort();
    }

    /**
     * sort
     * @param sortId
     * @return
     */
    public ReMissionSort getSortById(Integer sortId){
        return reMissionSortDAO.selectByPrimaryKey(sortId);
    }

    /**
     * 修改分类
     * @param sort
     * @return
     */
    public String doUpdateSort(ReMissionSort sort){

        Integer sortId = sort.getSortId();

        if (StringUtils.isEmpty(sortId)) {  //添加

            reMissionSortDAO.insertSelective(sort);
            return JsonUtil.buildSuccess();

        }else { //修改
            ReMissionSort sortUp = reMissionSortDAO.selectByPrimaryKey(sortId);
            if (sortUp == null){
                return JsonUtil.buildError("分类不存在");
            }
            reMissionSortDAO.updateByPrimaryKey(sort);

            return JsonUtil.buildSuccess();
        }
    }

    /**
     * 删除
     * @param sortIds
     * @return
     */
    public String doDeleteSort (String sortIds) {

        int n = 0 ;

        if (!StringUtils.isEmpty(sortIds)) {
            String[] sorts = sortIds.split("&");
            List<String> sortList = Arrays.asList(sorts);

            for (String sort : sortList) {
                Integer sortId = Integer.valueOf(sort);
                ReMissionSort reMissionSort = reMissionSortDAO.selectByPrimaryKey(sortId);
                if (reMissionSort != null) {
                    reMissionSortDAO.deleteByPrimaryKey(sortId);
                    n ++ ;
                }
            }
        }

        return JsonUtil.buildSuccess("成功删除"+n+"条纪录");
    }





}
