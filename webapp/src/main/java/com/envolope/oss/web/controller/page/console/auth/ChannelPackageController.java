package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.ParamConsts;
import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.RePackageChannel;
import com.envolope.oss.model.dto.PageDto;
import com.envolope.oss.model.form.UploadApkForm;
import com.envolope.oss.service.UploadService;
import com.envolope.oss.service.userManager.ChannelPackageService;
import com.envolope.oss.util.CmMethod;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/page/console/auth/packageChannel", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class ChannelPackageController {

    @Autowired
    private ChannelPackageService channelPackageService;
    @Autowired
    private UploadService uploadService;

    /**
     * 去渠道包名列表页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/list")
    public String gotoChannelPackagePage(HttpServletRequest request,
                                         @RequestParam(value = ParamConsts.pageNo, required = false) Integer pageNo){

        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }

        PageDto pageDto = channelPackageService.selectByPage(pageNo, 100);
        request.setAttribute("pg", pageDto);

        return "/console/user-management/package-channel";

    }

    /**
     * 查询渠道信息
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public String edit(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.id, required = false) String id){
        RequestContext requestContext = new RequestContext(request);
        if (id == null) {
            return JsonUtil.buildError(requestContext.getMessage("记录不存在"));
        }
        RePackageChannel rePackageChannel = channelPackageService.selectPackageChannel(id);
        if (rePackageChannel != null) {
            return JsonUtil.buildData(rePackageChannel);
        }
        return JsonUtil.buildError(requestContext.getMessage("记录不存在"));
    }

    /**
     * 保存渠道
     *
     * @param request
     * @param rePackageChannel
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request, RePackageChannel rePackageChannel) throws Exception {
        return channelPackageService.save(rePackageChannel);
    }


    /**
     * 删除渠道
     *
     * @param request
     * @param ids
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public String remove(HttpServletRequest request,
                         @RequestParam(value = ParamConsts.ids, required = false) List<String> ids) {
        RequestContext requestContext = new RequestContext(request);
        if (ids == null || ids.isEmpty()) {
            return JsonUtil.buildError(requestContext.getMessage("common.error.required-select", new Object[]{"记录"}));
        }
        if (channelPackageService.delete(ids)) {
            return JsonUtil.buildSuccess(requestContext.getMessage("msg.success"));
        }
        return JsonUtil.buildError(requestContext.getMessage("msg.fail"));
    }

    /**
     * 上传渠道包
     *
     * @param request
     * @param uploadApkForm
     * @return
     */
    @RequestMapping(value = "/uploadApk", method = RequestMethod.POST)
    @ResponseBody
    public String uploadApk(HttpServletRequest request, @Valid UploadApkForm uploadApkForm, BindingResult result) {

        String channelPackage = uploadApkForm.getChannelPackage();
        MultipartFile apk = uploadApkForm.getApk();
        String fileName = apk.getOriginalFilename();

        // 获得扩展名
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length()).toLowerCase();
        if(CmMethod.isApkTypeValidate(ext)) {
            String apkURL = uploadService.uploadApk(apk, fileName);
            if(apkURL != null) {
                return channelPackageService.updateApk(channelPackage, apkURL);
            }else{
                return JsonUtil.buildError("上传失败！");
            }
        }else{
            return JsonUtil.buildError("请选择.apk文件");
        }

    }

}
