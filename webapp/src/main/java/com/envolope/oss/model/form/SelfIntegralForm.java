package com.envolope.oss.model.form;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Created by Jan on 2016/12/5.\
 * 自己积分墙任务上传apk的form
 */
public class SelfIntegralForm implements Serializable{

    private Long wallId;
    private MultipartFile apk;

    public Long getWallId() {
        return wallId;
    }

    public void setWallId(Long wallId) {
        this.wallId = wallId;
    }

    public MultipartFile getApk() {
        return apk;
    }

    public void setApk(MultipartFile apk) {
        this.apk = apk;
    }
}
