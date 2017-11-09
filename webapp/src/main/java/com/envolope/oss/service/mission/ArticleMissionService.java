package com.envolope.oss.service.mission;

import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReArticleAdDAO;
import com.envolope.oss.dao.ReArticleMissionDAO;
import com.envolope.oss.model.ReArticleAd;
import com.envolope.oss.model.ReArticleMission;
import com.envolope.oss.model.para.ArticleSelectParamVo;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 2016/12/6.
 * 转发文章任务
 */
@Service
public class ArticleMissionService {

    @Autowired
    private ReArticleMissionDAO reArticleMissionDAO;
    @Autowired
    private ReArticleAdDAO reArticleAdDAO;


    /**
     * 保存／修改
     * @param article
     * @return
     */
    public String save(ReArticleMission article){

        long now = System.currentTimeMillis();
        String timeNow = ElBase.fmtTime(now);

        article.setUpdateTime(timeNow);

        Long articleId = article.getArticleId();

        if (StringUtil.isEmpty(articleId)){

            article.setCreateTime(timeNow);


            reArticleMissionDAO.insertSelective(article);
        }else{
            reArticleMissionDAO.updateByPrimaryKeySelective(article);
        }

        return JsonUtil.buildSuccess();
    }

    /**
     * 根据id查询
     * 不允许修改次数，否则需要lock
     * @param articleId
     * @return
     */
    public ReArticleMission getArticle(long articleId){

        return reArticleMissionDAO.selectByPrimaryKey(articleId);
    }

    /**
     * 获得数量
     * @param param
     * @return
     */
    public Integer getNum(ArticleSelectParamVo param){

        return reArticleMissionDAO.getNum(param);
    }

    /**
     * 列表，分页查询
     * @param param
     * @return
     */
    public List<ReArticleMission> getList(ArticleSelectParamVo param){

        int offset = (param.getPageNum() -1)*ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offset, ValueConsts.PAGE_SIZE);

        List<ReArticleMission> missions = reArticleMissionDAO.getList(param,bounds);

        for (ReArticleMission mission : missions){

            String url = mission.getArticleUrl();
            if (url.length() >= 20 ){
                url = url.substring(0,19)+"...";
                mission.setArticleUrl(url);
            }
        }
        return missions;
    }

    /**
     * 文章对应的广告
     * @param request
     * @param articleId
     * @return
     */
    public List<ReArticleAd> adList(HttpServletRequest request, Long articleId){

        return reArticleAdDAO.getAdListByArticleId(articleId);
    }

    /**
     * 保存广告
     * @param request
     * @param ad
     * @return
     */
    public String saveAd(HttpServletRequest request,ReArticleAd ad){

        long now = System.currentTimeMillis();
        String nowTime = ElBase.fmtTime(now);
        ad.setUpdateTime(nowTime);

        Long adId = ad.getAdId();
        if (StringUtil.isEmpty(adId)){
            ad.setCreateTime(nowTime);
            reArticleAdDAO.insertSelective(ad);
        }else {
            reArticleAdDAO.updateByPrimaryKeySelective(ad);
        }
        return JsonUtil.buildSuccess();
    }

    /**
     * 获取广告
     * @param adId
     * @return
     */
    public ReArticleAd getAdById(Long adId){

        return reArticleAdDAO.selectByPrimaryKey(adId);
    }

    /**
     * 添加点击次数
     * @param articleId
     * @param addNum
     * @return
     */
    public String addNum(Long articleId,Integer addNum){

        ReArticleMission mission = reArticleMissionDAO.getArticleByIdLock(articleId);

        mission.setLeftClickTimes(mission.getLeftClickTimes()+addNum);
        mission.setTotalClickTimes(mission.getTotalClickTimes()+addNum);
        mission.setUpdateTime(ElBase.fmtTime(System.currentTimeMillis()));

        reArticleMissionDAO.updateByPrimaryKeySelective(mission);

        return JsonUtil.buildSuccess("成功添加"+addNum+"次点击次数");
    }
}
