package com.envolope.oss.web.controller.page.console.auth.mission_manager;

import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.model.ReArticleAd;
import com.envolope.oss.model.ReArticleMission;
import com.envolope.oss.model.para.ArticleSelectParamVo;
import com.envolope.oss.service.UploadService;
import com.envolope.oss.service.mission.ArticleMissionService;
import com.envolope.oss.util.EntityUtil;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.StringUtil;
import com.envolope.oss.util.pager.PagerHtml;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 2016/12/6.
 * 转发文章任务
 */
@RequestMapping(value = "/page/console/auth/article", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class ArticleMissionController {

    @Autowired
    private ArticleMissionService articleMissionService;
    @Autowired
    private UploadService uploadService;

    /**
     * 文章列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/articlePage")
    public String goListPage(HttpServletRequest request){

        return "console/mission-management/article/article-list";
    }

    /**
     * 去添加文章页面
     * @param request
     * @return
     */
    @RequestMapping("/toAddPage")
    public String addPage(HttpServletRequest request){

        request.setAttribute("add",1);

        return "console/mission-management/article/edit-article";
    }


    /**
     * 列表查询
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request,ArticleSelectParamVo param){

        param = EntityUtil.emptyStringToNull(param);

        int total = articleMissionService.getNum(param);
        int totalPage = total / ValueConsts.PAGE_SIZE;
        if (total % ValueConsts.PAGE_SIZE > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }
        List<ReArticleMission> details = articleMissionService.getList(param);
        // 分页控件
        String page = PagerHtml.buildHtml(totalPage, param.getPageNum());

        Map<String,Object> map = new HashMap<>(3);
        map.put("details", details);
        map.put("page", page);

        return JsonUtil.buildData(map);
    }

    /**
     * 修改文章
     * @param request
     * @param articleId
     * @return
     */
    @RequestMapping("/toUpdatePage")
    public String editPage(HttpServletRequest request,
                           @RequestParam(value = "articleId")Long articleId){

        ReArticleMission article = articleMissionService.getArticle(articleId);

        request.setAttribute("update",1);

        request.setAttribute("article",article);

        return "console/mission-management/article/edit-article";
    }

    /**
     * 保存修改／添加
     * @param request
     * @param article
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request, ReArticleMission article){

        return articleMissionService.save(article);
    }

    /**
     * 获取文章
     * @param request
     * @param articleUrl
     * @return
     */
    @RequestMapping(value = "/getArticle")
    @ResponseBody
    public String parsUrlToGetArticle(HttpServletRequest request,
                                      @RequestParam(value = "articleUrl")String articleUrl){

        //请求参数
        Map<String, String> params = new HashMap<>();
        params.put("searcharg", "java");
        params.put("searchtype", "t");
        params.put("SORT", "DZ");
        params.put("extended", "0");

        Document doc = null;             //使用get方法，对应还有post()
        try {
            doc = Jsoup.connect(articleUrl)
                    .userAgent("Mozilla")  //声明了浏览器用于 HTTP 请求的用户代理头的值
                    .timeout(100*1000)   //超时时间
                    .data(params)       //请求参数
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements img = doc.select("img");

        if (img != null && img.size() >0){

            for (Element element : img){

                String resultUrl = null;
                String uploadImg ;
                String srcUrl = element.attr("src");
                String dataSrc = element.attr("data-src");

                uploadImg = !StringUtil.isEmpty(srcUrl)?srcUrl:(!StringUtil.isEmpty(dataSrc)?dataSrc:"");

                element.removeAttr("data-src");

                if (!StringUtil.isEmpty(uploadImg)){

                    String exe = null;

                    if (! uploadImg.startsWith("http")){
                        uploadImg = "http:"+uploadImg;
                    }
                    if (uploadImg.contains("wx_fmt=gif")){
                        exe = "gif";
                    }else if (uploadImg.contains("wx_fmt=png")){
                        exe = "png";
                    }else {
                        exe = "jpeg";
                    }

                    resultUrl = uploadService.uploadByImageUrl(uploadImg,exe);

                    element.attr("src",resultUrl);
                }
            }
        }



        String title = doc.select("h2#activity-name").first().html();
        String content = doc.select("div#js_content").first().html();

        String displayTime = doc.select("#post-date").text();
        String author = doc.select("#post-user").text();

        Map<String, String> article = new HashMap<>(4);
        article.put("title", title);
        article.put("content", content);
        article.put("displayTime",displayTime);
        article.put("articleAuthor",author);

        return JsonUtil.buildData(article);
    }

    /**
     * 跳转到该文章对应的广告页面
     * @param request
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/adList")
    public String adList(HttpServletRequest request,
                         @RequestParam(value = "articleId")Long articleId){

        List<ReArticleAd> ads = articleMissionService.adList(request,articleId);
        request.setAttribute("ads",ads);
        request.setAttribute("articleId",articleId);

        return "console/mission-management/article/ad-list";
    }

    /**
     * 保存广告
     * @param request
     * @param ad
     * @return
     */
    @RequestMapping(value = "/saveAd")
    @ResponseBody
    public String saveAd(HttpServletRequest request,ReArticleAd ad){

        return articleMissionService.saveAd(request,ad);
    }

    /**
     * 修改广告
     * @param request
     * @param adId
     * @return
     */
    @RequestMapping(value = "/editAd")
    @ResponseBody
    public String editAd(HttpServletRequest request,Long adId){

        ReArticleAd ad = articleMissionService.getAdById(adId);

        Map<String, Object> map = new HashMap<>(4);
        map.put("ad", ad);

        return JsonUtil.buildData(map);
    }


    /**
     * 添加点击次数
     * @param articleId
     * @param addNum
     * @return
     */
    @RequestMapping(value = "/addNum")
    @ResponseBody
    public String addNum( @RequestParam(value = "articleId")Long articleId,
                          @RequestParam(value = "addNum")Integer addNum){

        return articleMissionService.addNum(articleId,addNum);
    }
}
