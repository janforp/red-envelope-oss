package com.envolope.oss.model.form;

import org.springframework.web.multipart.MultipartFile;

public class UploadApkForm {

    // 渠道包名
    private String channelPackage;
    // 文件
    private MultipartFile apk;

    public String getChannelPackage() {
        return channelPackage;
    }

    public void setChannelPackage(String channelPackage) {
        this.channelPackage = channelPackage;
    }

    public MultipartFile getApk() {
        return apk;
    }

    public void setApk(MultipartFile apk) {
        this.apk = apk;
    }
}
