package com.envolope.oss.model.vo;

/**
 * 购买的云购码
 * Created by Summer on 16/5/25.
 */
public class UserCodeVo implements java.io.Serializable {

    // 云购码
    private String code;
    // 个数
    private Integer num;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}