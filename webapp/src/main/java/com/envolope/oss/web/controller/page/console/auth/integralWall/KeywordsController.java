package com.envolope.oss.web.controller.page.console.auth.integralWall;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.ReApp;
import com.envolope.oss.model.ReAppKeywords;
import com.envolope.oss.model.ReTaskLabel;
import com.envolope.oss.service.integralWall.IntegralWallService;
import com.envolope.oss.service.integralWall.KeywordsService;
import com.envolope.oss.service.integralWall.LabelService;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/10/14.
 * 关键词
 */
@Controller
@RequestMapping(value = "/page/console/auth/keyword", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class KeywordsController {

    @Autowired
    private KeywordsService keywordsService;
    @Autowired
    private IntegralWallService integralWallService;
    @Autowired
    private LabelService labelService;

    /**
     * 关键词列表
     * @param id appid
     * @return
     */
    @RequestMapping(value = "/keywordList")
    public String listPage(HttpServletRequest request,Long id){

        List<ReAppKeywords> words = keywordsService.getAllList(request,id);

        request.setAttribute("words",words);

        ReApp app = integralWallService.getAppById(id);

        request.setAttribute("app",integralWallService.turnVo(app));

        List<ReTaskLabel> labels = labelService.getAll();

        request.setAttribute("labels",labels);

        return "/console/integralWall/keywords-list";
    }



    /**
     * 查询
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public String edit(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.id, required = false) Long id){
        RequestContext requestContext = new RequestContext(request);
        if (id == null) {
            return JsonUtil.buildError(requestContext.getMessage("记录不存在"));
        }
        ReAppKeywords word = keywordsService.getKeywordById(id);
        if (word != null) {

            return JsonUtil.buildData(word);
        }
        return JsonUtil.buildError(requestContext.getMessage("记录不存在"));
    }


    /**
     * 保存
     * @param request
     * @param word
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request, ReAppKeywords word) throws Exception {
        return keywordsService.save(request, word);
    }


    /**
     * 删除
     * @param request
     * @param wordIds
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public String remove(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.ids, required = false) List<Long> wordIds) {
        RequestContext requestContext = new RequestContext(request);
        if (wordIds == null || wordIds.isEmpty()) {
            return JsonUtil.buildError(requestContext.getMessage("common.error.required-select", new Object[]{"记录"}));
        }
        if (keywordsService.delete(wordIds)) {
            return JsonUtil.buildSuccess(requestContext.getMessage("msg.success"));
        }
        return JsonUtil.buildError(requestContext.getMessage("msg.fail"));
    }
}