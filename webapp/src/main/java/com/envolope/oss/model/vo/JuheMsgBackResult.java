package com.envolope.oss.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/6/29.
 */
public class JuheMsgBackResult  implements Serializable {

    //发送数量
    private String count;

    //扣除条数
    private String fee;

    //短信ID
    private String sid;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
