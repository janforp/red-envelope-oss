package com.envolope.oss.service;

import com.envolope.oss.api.AliOss;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * Created by Summer on 16/3/1.
 */
@Service
public class UploadService {

    /**
     * 上传图片
     *
     * @param file
     * @param type
     * @return
     */
    public String upload(MultipartFile file, String type) {
        String url = null;
        try {
            url =  AliOss.uploadMultipartFile("hongbao/"+UUID.randomUUID().toString(), file, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }


    /**
     * 上传Apk文件
     *
     * @param file
     * @return
     */
    public String uploadApk(MultipartFile file, String fileName) {
        String url = null;
        try {
            url =  AliOss.uploadMultipartApk("apk/"+fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 上传网络资源
     *
     * @param url
     * @param suffix
     * @return
     */
    public String uploadByImageUrl(String url, String suffix){
        String resultPath = null;
        try {
            resultPath = AliOss.uploadByUrl(url, "wxImg/"+UUID.randomUUID().toString()+"." + suffix, suffix);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultPath;
    }

}
