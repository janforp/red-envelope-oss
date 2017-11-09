package com.envolope.oss.model.para;

/**
 * Created by Jan on 16/8/24.
 * 兑换码红包 查询条件
 */
public class CodeRedSelectParamVo {

    private String      customerName;

    private Integer     codeStatus;

    private Integer     pageSize;

    private Integer     pageNum;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(Integer codeStatus) {
        this.codeStatus = codeStatus;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
