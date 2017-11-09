package com.envolope.oss.model.vo;

import java.io.Serializable;

/**
 * Created by Summer on 16/6/27.
 */
public class JuheBack implements Serializable{

    // 返回码
    private int error_code;
    // 返回说明
    private String reason;
    // 返回结果集
    private JuheBackResult result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public JuheBackResult getResult() {
        return result;
    }

    public void setResult(JuheBackResult result) {
        this.result = result;
    }

}
