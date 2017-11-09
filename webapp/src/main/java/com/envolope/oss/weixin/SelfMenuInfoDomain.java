package com.envolope.oss.weixin;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Summer on 16/8/9.
 */
public class SelfMenuInfoDomain implements Serializable {
    private List<SelfMenuInfoButtonDomain> button;

    public SelfMenuInfoDomain() {
    }

    public List<SelfMenuInfoButtonDomain> getButton() {
        return this.button;
    }

    public void setButton(List<SelfMenuInfoButtonDomain> button) {
        this.button = button;
    }
}
