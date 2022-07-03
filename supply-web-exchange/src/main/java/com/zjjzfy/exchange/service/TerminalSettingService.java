package com.zjjzfy.exchange.service;

import com.zjjzfy.common.entity.SupplyResult;

/**
 * 
 * @author      jackshenonly
 * @description 类说明
 * @date        2020-03-24 09:50
 */
public interface TerminalSettingService {
    /**
     * 柜员终端设置认证
     * @param uname
     * @param pwd
     * @return
     */
    SupplyResult login(String uname, String pwd);
}
