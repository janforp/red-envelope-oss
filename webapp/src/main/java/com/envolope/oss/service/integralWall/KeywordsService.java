package com.envolope.oss.service.integralWall;

import com.envolope.oss.dao.ReAppKeywordsDAO;
import com.envolope.oss.model.ReAppKeywords;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wujie5 on 16/10/14.
 */
@Service
public class KeywordsService {

    @Autowired
    private ReAppKeywordsDAO reAppKeywordsDAO;

    /**
     *
     * @param request
     * @param id
     * @return
     */
    public List<ReAppKeywords> getAllList(HttpServletRequest request,Long id){

        return reAppKeywordsDAO.getAllList(id);
    }

    /**
     * 修改时,获取原来的数据
     * @param id
     * @return
     */
    public ReAppKeywords getKeywordById(Long id){

        return reAppKeywordsDAO.selectByPrimaryKey(id);
    }

    /**
     * 添加或者修改
     * @param request
     * @return
     */
    public String  save(HttpServletRequest request, ReAppKeywords word){

        Long id = word.getKeywordId();

        if (StringUtils.isEmpty(id)) { //ID空,则为添加

            word.setCreateTime(ElBase.fmtDay(System.currentTimeMillis()));

            reAppKeywordsDAO.insertSelective(word);

        } else { //有ID,则为修改

            reAppKeywordsDAO.updateByPrimaryKeySelective(word);

        }

        return JsonUtil.buildSuccess();
    }

    /**
     * 删除
     * @param wordsIds
     * @return
     */
    public boolean delete(List<Long> wordsIds) {
        for (Long wordsId : wordsIds) {
            ReAppKeywords word = reAppKeywordsDAO.selectByPrimaryKey(wordsId);
            if (word != null) {
                reAppKeywordsDAO.deleteByPrimaryKey(wordsId);
            }
        }
        return true;
    }

}
