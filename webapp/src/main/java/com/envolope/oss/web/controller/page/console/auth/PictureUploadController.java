package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.service.UploadService;
import com.envolope.oss.util.CmMethod;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Summer on 16/3/1.
 */
@RequestMapping(value = "/p/picture")
@Controller
public class PictureUploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam(value = "fileX") MultipartFile imageFile) {

        String fileName = imageFile.getOriginalFilename();
        //获得扩展名
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length()).toLowerCase();
        if(CmMethod.isImageTypevalidate(ext)) {
            String imgURL = uploadService.upload(imageFile, ext);
            if(imgURL != null) {
                return JsonUtil.buildSuccess("上传成功！",imgURL);
            }else{
                return JsonUtil.buildError("上传失败！");
            }
        }else{
            return JsonUtil.buildError("不支持该格式的图片");
        }
    }


    @RequestMapping(value = "/ue")
    @ResponseBody
    public Map<String,Object> upload(HttpServletRequest req) {

        Map<String,Object> result = new HashMap<String, Object>();

        MultipartHttpServletRequest mReq = (MultipartHttpServletRequest)req;
        MultipartFile imageFile = mReq.getFile("upfile");
        String fileName = imageFile.getOriginalFilename();
        //获得扩展名
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length()).toLowerCase();
        if(CmMethod.isImageTypevalidate(ext)) {
            String imgURL = uploadService.upload(imageFile,ext);

            if(imgURL != null){
                result.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
                result.put("url", imgURL);
                result.put("title", fileName);
                result.put("original", fileName);
            }else{
                result.put("state", "上传失败！");
                result.put("url","");
                result.put("title", "");
                result.put("original", "");
            }
        }else{
            result.put("state", "不支持该格式的图片!");
            result.put("url","");
            result.put("title", "");
            result.put("original", "");
        }

        return result;
    }
}
