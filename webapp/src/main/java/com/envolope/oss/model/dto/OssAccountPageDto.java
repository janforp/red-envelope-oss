package com.envolope.oss.model.dto;

import com.envolope.oss.model.OssAdmin;

/**
 * Created by wuqiang on 16-2-2.
 *
 * @author wuqiang
 */
public class OssAccountPageDto extends OssAdmin {
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
