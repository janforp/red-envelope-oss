package com.envolope.oss.model.form;

/**
 * Created by Jan on 16/7/1.
 *
 * 新增支付方式
 */
public class PaySaveForm {

    //名称
    private String name;
    // 支付参数
    private String type;
    // 支付图片
    private String img;
    // 支付描述
    private String content;
    // 0 关闭 1 开启 2 IOS 3 Android
    private Integer no;
    // 排序
    private Integer xu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getXu() {
        return xu;
    }

    public void setXu(Integer xu) {
        this.xu = xu;
    }
}
