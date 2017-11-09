package com.envolope.oss.model.dto;

import com.envolope.oss.model.OssRole;

import java.util.List;

/**
 * Created by wuqiang on 16-2-2.
 *
 * @author wuqiang
 */
public class OssRoleDetailDto extends OssRole {
    private List<Integer> functionIds;

    public List<Integer> getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(List<Integer> functionIds) {
        this.functionIds = functionIds;
    }
}
