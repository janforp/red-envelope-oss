package com.envolope.oss.web.controller.page.console.auth.mission_manager;

import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.model.ReAndriodIntegralWall;
import com.envolope.oss.model.ReAppMarket;
import com.envolope.oss.model.ReTaskLabel;
import com.envolope.oss.model.form.SelfIntegralForm;
import com.envolope.oss.model.form.UploadApkForm;
import com.envolope.oss.model.para.SelfIntegralSelectParam;
import com.envolope.oss.model.vo.mission.SelfIntegralListVo;
import com.envolope.oss.service.UploadService;
import com.envolope.oss.service.integralWall.IntegralWallService;
import com.envolope.oss.service.integralWall.LabelService;
import com.envolope.oss.service.mission.SelfIntegralService;
import com.envolope.oss.service.userManager.ChannelPackageService;
import com.envolope.oss.util.CmMethod;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.pager.PagerHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 2016/12/2.
 * 自己的积分墙  re_andriod_integral_wall
 */
@Controller
@RequestMapping(value = "/page/console/auth/selfIntegral" ,produces = RequestConsts.CONTENT_TYPE_HTML,method = {RequestMethod.POST,RequestMethod.GET})
public class SelfIntegralController {

    @Autowired
    private SelfIntegralService selfIntegralService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private IntegralWallService integralWallService;
    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/selfIntegralPage")
    public String goListPage(){

        return "/console/mission-management/self-integral/self-integral-list";
    }

    /**
     * 获取数据
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String getData(HttpServletRequest request,SelfIntegralSelectParam param){

        int total = selfIntegralService.getNum(param);
        int totalPage = total / ValueConsts.PAGE_SIZE;
        if (total % ValueConsts.PAGE_SIZE > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }
        List<SelfIntegralListVo> details = selfIntegralService.getList(param);
        // 分页控件
        String page = PagerHtml.buildHtml(totalPage, param.getPageNum());

        Map<String,Object> map = new HashMap<>(3);
        map.put("details", details);
        map.put("page", page);

        return JsonUtil.buildData(map);
    }


    /**
     * 添加，修改页面
     * @param request
     * @param wallId
     * @return
     */
    @RequestMapping(value = "/edit")
    public String goEditPage(HttpServletRequest request,
                             @RequestParam(value = "wallId",required = false)Long wallId){
        if (wallId == null){
            request.setAttribute("add",1);
        }else {
            request.setAttribute("update",1);
            ReAndriodIntegralWall wall = selfIntegralService.getWall(wallId);
            request.setAttribute("wall",wall);
        }
        List<ReTaskLabel> labels = labelService.getAll();
        request.setAttribute("labels",labels);

        List<ReAppMarket> markets = integralWallService.getMarketList();
        request.setAttribute("markets", markets);

        return "/console/mission-management/self-integral/edit-self-integral";
    }

    /**
     * 保存
     * @param request
     * @param wall
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request,ReAndriodIntegralWall wall){

        Long wallId = wall.getWallId();
        if (wallId == 0){
            wall.setWallId(null);
        }

        return selfIntegralService.save(request,wall);
    }


    /**
     * 上传积分墙apk包
     *
     * @param request
     * @param selfIntegralForm
     * @return
     */
    @RequestMapping(value = "/uploadApk", method = RequestMethod.POST)
    @ResponseBody
    public String uploadApk(HttpServletRequest request, @Valid SelfIntegralForm selfIntegralForm, BindingResult result) {

        MultipartFile apk = selfIntegralForm.getApk();
        String fileName = apk.getOriginalFilename();
        Long wallId = selfIntegralForm.getWallId();

        // 获得扩展名
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length()).toLowerCase();

        if(CmMethod.isApkTypeValidate(ext)) {
            String apkURL = uploadService.uploadApk(apk, fileName);
            if(apkURL != null) {

                return selfIntegralService.updateApk(wallId, apkURL);
            }else{
                return JsonUtil.buildError("上传失败！");
            }
        }else{
            return JsonUtil.buildError("请选择.apk文件");
        }
    }
}