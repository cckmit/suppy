package com.zjjzfy.shiro.token;

import com.zjjzfy.common.config.ShiroPassType;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author yzx
 */
public class NoPasswordToken extends UsernamePasswordToken {
    /**
     * 0 用户名密码登录 1 微信登录
     */
    private static final long serialVersionUID = -2564928913725078138L;

    private String loginType;


    /**
     * 账号密码登录
     *
     * @param username
     * @param password
     */
    public NoPasswordToken(String username, String password) {
        super(username, password);

        this.loginType = ShiroPassType.PASSWORD.getType();
    }

    /**
     * 免密登录
     */
    public NoPasswordToken(String username) {
        super(username, "");

        this.loginType = ShiroPassType.NOPASSWD.getType();
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}