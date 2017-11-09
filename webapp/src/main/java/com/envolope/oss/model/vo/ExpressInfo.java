package com.envolope.oss.model.vo;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jan on 16/6/17.
 * 快递信息
 */
public class ExpressInfo implements Serializable {

    //快递公司名称(1,2,3,4,5,6)
    private Integer     name;
    //快递单号
    private String      code;
    //运费
    private BigDecimal  price;
    //订单状态
    private String      statusDesc;
    //快递状态
    private String      expressStatus;

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getExpressStatus() {
        return expressStatus;
    }

    public void setExpressStatus(String expressStatus) {
        this.expressStatus = expressStatus;
    }
}
