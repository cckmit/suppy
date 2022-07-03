package com.zjjzfy.shiro.service;

import com.zjjzfy.common.entity.SupplyResult;

/**
 * @author yzx
 */
public interface ShiroService {
    /**
     * 用户名密码登录
     *
     * @param username
     * @param password
     * @return
     */
    SupplyResult login(String username, String password);

    /**
     * 免密登录
     *
     * @param openId
     * @return
     */
    SupplyResult densityFreeLogin(String openId);

    Boolean hasPermit(String permitName);

    SupplyResult changePwd(String oldPwd, String newPwd);

    SupplyResult resetPwd(Integer uid);
}