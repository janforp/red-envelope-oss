package com.envolope.oss.model.vo;

import java.io.Serializable;

/**
 * Created by Summer on 16/4/16.
 */
public class TaobaoIP implements Serializable {

    private String code;

    private IPdata data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public IPdata getData() {
        return data;
    }

    public void setData(IPdata data) {
        this.data = data;
    }
}
