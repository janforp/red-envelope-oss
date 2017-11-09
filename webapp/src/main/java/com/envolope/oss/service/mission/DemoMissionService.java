package com.envolope.oss.service.mission;

import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReAppDAO;
import com.envolope.oss.dao.ReAppKeywordsDAO;
import com.envolope.oss.dao.ReAppMarketDAO;
import com.envolope.oss.model.ReApp;
import com.envolope.oss.model.ReAppKeywords;
import com.envolope.oss.model.RePasswordRed;
import com.envolope.oss.model.para.DemoSelectParam;
import com.envolope.oss.model.vo.mission.AppKeywordList;
import com.envolope.oss.model.vo.mission.KeywordListVo;
import com.envolope.oss.model.vo.mission.ParseData;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 2016/11/22.
 * 试玩人物
 */
@Service
public class DemoMissionService {

    @Autowired
    private ReAppKeywordsDAO reAppKeywordsDAO;
    @Autowired
    private ReAppMarketDAO reAppMarketDAO;
    @Autowired
    private ReAppDAO reAppDAO;


    public DemoSelectParam handleParam(DemoSelectParam param){

        String keyword = param.getKeyword();
        if (StringUtil.isEmpty(keyword)){
            param.setKeyword(null);
        }
        if (StringUtil.isEmpty(param.getAppName())){
            param.setAppName(null);
        }
        if (StringUtil.isEmpty(param.getMarketId())){
            param.setMarketId(null);
        }
        if (StringUtil.isEmpty(param.getStatus())){
            param.setStatus(null);
        }
        if (StringUtil.isEmpty(param.getReleaseTime())){
            param.setReleaseTime(null);
        }
        if (StringUtil.isEmpty(param.getPageNum())){
            param.setPageNum(1);
        }
        return param;
    }

    /**
     * 总数,分页用
     * @return
     */
    public Integer getNum(HttpServletRequest request,DemoSelectParam param){

        return reAppKeywordsDAO.getNum(param);
    }

    /**
     * 列表查询
     * @param request
     * @return
     */
    public List<KeywordListVo> getList(HttpServletRequest request,DemoSelectParam param){
        int offset = (param.getPageNum() - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offset,ValueConsts.PAGE_SIZE);
        Integer status = param.getStatus();
        long now = System.currentTimeMillis();
        List<KeywordListVo> details = reAppKeywordsDAO.getList(bounds,param);
        if (status != null){
            if (status==0){
                for (KeywordListVo vo : details){
                    vo.setStatus("未开始");
                }
            }else if (status == 1){
                for (KeywordListVo vo : details){
                    vo.setStatus("进行中");
                }
            }else if (status == 2){
                for (KeywordListVo vo : details){
                    vo.setStatus("已结束");
                }
            }
        } else {
            for (KeywordListVo vo : details){
                long releaseTime=0 ;
                long endTime = 0;
                try {
                    releaseTime = Long.parseLong(ElBase.get13Timestamp(vo.getReleaseTime()));
                    endTime = Long.parseLong(ElBase.get13Timestamp(vo.getEndTime()));
                }catch (Exception e){
                    try {
                        throw e;
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                if (vo.getLeftNum() <= 0 || now >= endTime){
                    vo.setStatus("已结束");
                }else if (releaseTime > now){
                    vo.setStatus("未开始");
                }else if (vo.getLeftNum()>0 && now >= releaseTime && now < endTime){
                    vo.setStatus("进行中");
                }
            }
        }
        return details;
    }


    /**
     * 新解析出来的对象，若是有些数是空的，则去查看数据库中是否有次app
     * 把此app的描述，icon查询出来
     * @param mission
     * @return
     */
    public ParseData handleNullData(ParseData mission){

        if (mission!=null){
            ReApp app = reAppDAO.selectAppByMarketIdAndPackageName(mission.getMarketId(),mission.getAppPackage());
            if (app != null){
                if (StringUtil.isEmpty(mission.getAppName())){
                    mission.setAppName(app.getAppName());
                }
                if (StringUtil.isEmpty(mission.getSize())){
                    mission.setSize(app.getAppSize());
                }
                if (StringUtil.isEmpty(mission.getAppDesc())){
                    mission.setAppDesc(app.getAppDesc());
                }
                if (StringUtil.isEmpty(mission.getAppIcon())){
                    mission.setAppIcon(app.getAppIcon());
                }
            }
        }
        return mission;
    }

    /**
     * 根据市场ID及包名查app
     * @param marketId
     * @param packageName
     * @return
     */
    public ReApp getAppByMarketAndPackage(Integer marketId,String packageName){

        return reAppDAO.selectAppByMarketIdAndPackageName(marketId,packageName);
    }

    /**
     * 添加或修改任务
     * @param request
     * @param word
     * @return
     */
    public String save(HttpServletRequest request,ParseData word){

        Long keywordId = word.getKeywordId();
        ReAppKeywords keyword;
        ReApp app;

        //先处理re_app表
        Integer marketId = word.getMarketId();
        String  packageName = word.getAppPackage();
        app = reAppDAO.selectAppByMarketIdAndPackageName(marketId,packageName);
        String now = ElBase.fmtTime(System.currentTimeMillis());


        if (app != null){//此app之前已经有过关键词任务
            //保存修改app的数据
            app.setAppIcon(word.getAppIcon());
            app.setAppName(word.getAppName());
            app.setAppPackage(packageName);
            app.setMarketId(marketId);
            app.setAppSize(word.getSize());
            app.setAppDesc(word.getAppDesc());
            app.setUpdateTime(ElBase.fmtTime(System.currentTimeMillis()));
            reAppDAO.updateByPrimaryKeySelective(app);

            //再看是否是修改还是添加关键词任务
            if (StringUtil.isEmpty(keywordId)){//添加关键词任务

                keyword = new ReAppKeywords();
                keyword.setAppId(app.getAppId());
                keyword.setKeyword(word.getKeyword());
                keyword.setMoney(word.getMoney());
                keyword.setTotalNum(word.getTotalNum());
                keyword.setLeftNum(word.getLeftNum());
                keyword.setTaskLabel(word.getLabel());
                keyword.setKeywordWeight(word.getKeywordWeight());
                keyword.setReleaseTime(word.getReleaseTime());
                keyword.setCreateTime(now);
                keyword.setEndTime(word.getEndTime());
                keyword.setUpdateTime(now);

                reAppKeywordsDAO.insertSelective(keyword);
            }else {//修改关键词任务

                keyword = reAppKeywordsDAO.selectByPrimaryKey(keywordId);
                keyword.setAppId(app.getAppId());
                keyword.setKeyword(word.getKeyword());
                keyword.setMoney(word.getMoney());
//                keyword.setTotalNum(word.getTotalNum());
//                keyword.setLeftNum(word.getLeftNum());
                keyword.setTaskLabel(word.getLabel());
                keyword.setKeywordWeight(word.getKeywordWeight());
                keyword.setReleaseTime(word.getReleaseTime());
                keyword.setEndTime(word.getEndTime());
                keyword.setUpdateTime(now);

                reAppKeywordsDAO.updateByPrimaryKeySelective(keyword);
            }

        }else{//此app为第一次添加任务，即：新的app

            app = new ReApp();

            app.setAppIcon(word.getAppIcon());
            app.setAppName(word.getAppName());
            app.setAppPackage(packageName);
            app.setMarketId(marketId);
            app.setAppSize(word.getSize());
            app.setAppDesc(word.getAppDesc());
            app.setUpdateTime(now);
            app.setCreateTime(now);
            //添加此app到表
            reAppDAO.insertSelective(app);

            //若是新app，则此关键词任务必然是新到，则添加
            keyword = new ReAppKeywords();
            keyword.setAppId(app.getAppId());
            keyword.setKeyword(word.getKeyword());
            keyword.setMoney(word.getMoney());
            keyword.setTotalNum(word.getTotalNum());
            keyword.setLeftNum(word.getLeftNum());
            keyword.setTaskLabel(word.getLabel());
            keyword.setKeywordWeight(word.getKeywordWeight());
            keyword.setReleaseTime(word.getReleaseTime());
            keyword.setCreateTime(now);
            keyword.setEndTime(word.getEndTime());
            keyword.setUpdateTime(now);

            reAppKeywordsDAO.insertSelective(keyword);

        }
        return JsonUtil.buildSuccess();
    }
    /**
     * 修改数据获取
     * @param request
     * @param keywordId
     * @return
     */
    public ReAppKeywords modifyData(HttpServletRequest request,Long keywordId){

        ReAppKeywords word = reAppKeywordsDAO.selectByPrimaryKey(keywordId);

        return word;
    }

    /**
     * 获取修改页面带过去到数据
     * @param keywordId
     * @return
     */
    public ParseData getModifyData(Long keywordId){

        ParseData data = null ;

        if (keywordId != null&& keywordId != 0){

            ReAppKeywords keywords = reAppKeywordsDAO.selectByPrimaryKey(keywordId);
            if (keywords != null){
                ReApp app = reAppDAO.selectByPrimaryKey(keywords.getAppId());
                if (app != null){

                    data = new ParseData();
                    data.setKeywordId(keywordId);
                    data.setMarketId(app.getMarketId());
                    data.setAppName(app.getAppName());
                    data.setAppPackage(app.getAppPackage());
                    data.setAppDesc(app.getAppDesc());
                    data.setAppIcon(app.getAppIcon());
                    data.setSize(app.getAppSize());
                    data.setKeyword(keywords.getKeyword());
                    data.setMoney(keywords.getMoney());
                    data.setTotalNum(keywords.getTotalNum());
                    data.setLeftNum(keywords.getLeftNum());
                    data.setLabel(keywords.getTaskLabel());
                    data.setKeywordWeight(keywords.getKeywordWeight());
                    data.setReleaseTime(keywords.getReleaseTime());
                    data.setEndTime(keywords.getEndTime());
                }
            }
        }
        return data;
    }
}