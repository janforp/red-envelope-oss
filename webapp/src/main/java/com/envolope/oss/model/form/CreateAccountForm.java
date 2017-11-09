package com.envolope.oss.model.form;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by Jan on 16/6/22.
 *
 * 生成 机器人 帐号
 */
public class CreateAccountForm {

    private MultipartFile txtFile;

    public MultipartFile getTxtFile() {
        return txtFile;
    }

    public void setTxtFile(MultipartFile txtFile) {
        this.txtFile = txtFile;
    }
}
