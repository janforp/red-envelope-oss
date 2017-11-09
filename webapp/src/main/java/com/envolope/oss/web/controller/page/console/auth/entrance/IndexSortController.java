package com.envolope.oss.web.controller.page.console.auth.entrance;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.SizeConsts;
import com.envolope.oss.model.ReIndexSort;
import com.envolope.oss.model.RePackageChannel;
import com.envolope.oss.model.dto.PageDto;
import com.envolope.oss.service.ChannelAndPackageService;
import com.envolope.oss.service.entrance.IndexSortService;
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
 * Created by Summer on 16/9/18.
 * 首页分页
 */
@RequestMapping(value = "/page/console/auth/indexSort", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class IndexSortController {

    @Autowired
    private IndexSortService indexSortService;
    @Autowired
    private ChannelAndPackageService channelAndPackageService;

    /**
     * 首页分类列表
     * @param request
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "sortList")
    public String sortList(HttpServletRequest request,
                           @RequestParam(value = ParamConsts.pageNo, required = false) Integer pageNo) {

        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        PageDto pageDto = indexSortService.selectByPage(pageNo, SizeConsts.PAGE_SIZE_DEFAULT);

        request.setAttribute("pg", pageDto);

        List<String> channels = channelAndPackageService.getDistinctChannel();

        request.setAttribute("channels",channels);

        List<String > packages = channelAndPackageService.getDistinctPackage();

        request.setAttribute("packages",packages);

        List<RePackageChannel> packageChannels = channelAndPackageService.getPackageChannelList();

        request.setAttribute("packageChannels",packageChannels);

        return "console/deploy/index_sort-list";

    }

    /**
     * 保存分类
     * @param request
     * @param indexSort
     * @return
     */
    @RequestMapping(value = "/saveSort")
    @ResponseBody
    public String saveSort(HttpServletRequest request, ReIndexSort indexSort) throws Exception {
        return indexSortService.save(request, indexSort);
    }


    /**
     * 查询分类信息
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public String edit(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.id, required = false) Integer id){
        RequestContext requestContext = new RequestContext(request);
        if (id == null) {
            return JsonUtil.buildError(requestContext.getMessage("记录不存在"));
        }
        ReIndexSort indexSort = indexSortService.getIndexSortById(id);
        if (indexSort != null) {
            return JsonUtil.buildData(indexSort);
        }
        return JsonUtil.buildError(requestContext.getMessage("记录不存在"));
    }


    /**
     * 删除分类
     * @param request
     * @param sortIds
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public String remove(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.ids, required = false) List<Integer> sortIds) {
        RequestContext requestContext = new RequestContext(request);
        if (sortIds == null || sortIds.isEmpty()) {
            return JsonUtil.buildError(requestContext.getMessage("common.error.required-select", new Object[]{"记录"}));
        }
        if (indexSortService.delete(sortIds)) {
            return JsonUtil.buildSuccess(requestContext.getMessage("msg.success"));
        }
        return JsonUtil.buildError(requestContext.getMessage("msg.fail"));
    }

}
