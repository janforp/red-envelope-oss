package com.envolope.oss.model.vo;

/**
 * Created by Jan on 16/7/15.
 *  添加红包 页面 显示数据
 */
public class AddRedPageShowVo {

    //推广id
    private Integer id;
    //微信名称
    private String  wxName;
    //微信号
    private String  wxId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }
}
