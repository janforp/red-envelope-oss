package com.envolope.oss.model.dto;

import com.envolope.oss.model.OssRole;

/**
 * Created by wuqiang on 16-2-1.
 *
 * @author wuqiang
 */
public class OssRolePageDto extends OssRole {
    private Long adminCount;
    private Long authorityCount;

    public Long getAdminCount() {
        return adminCount;
    }

    public void setAdminCount(Long adminCount) {
        this.adminCount = adminCount;
    }

    public Long getAuthorityCount() {
        return authorityCount;
    }

    public void setAuthorityCount(Long authorityCount) {
        this.authorityCount = authorityCount;
    }
}
