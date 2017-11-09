package com.envolope.oss.model.dto;

import com.envolope.oss.model.vo.LoginAdminInfo;

/**
 * Created by wuqiang on 16-1-28.
 *
 * @author wuqiang
 */
public class LoginServiceResultDto implements java.io.Serializable {
    /**
     * 0:用户名或密码错误
     * 1:登录成功；
     * 2:账号被禁用
     */
    private int loginResult;

    private LoginAdminInfo loginAdminInfo;

    public boolean isLoginSuccess() {
        return 1 == loginResult && loginAdminInfo != null;
    }

    public int getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(int loginResult) {
        this.loginResult = loginResult;
    }

    public LoginAdminInfo getLoginAdminInfo() {
        return loginAdminInfo;
    }

    public void setLoginAdminInfo(LoginAdminInfo loginAdminInfo) {
        this.loginAdminInfo = loginAdminInfo;
    }
}
