package com.zjjzfy.exchange.service.impl;

import com.zjjzfy.common.config.UserState;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.cache.OrgCache;
import com.zjjzfy.exchange.pojo.UserInfoReturnBody;
import com.zjjzfy.exchange.service.BaseService;
import com.zjjzfy.exchange.service.TerminalSettingService;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.shiro.service.PasswordService;
import com.zjjzfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author      jackshenonly
 * @description 类说明
 * @date        2020-03-24 09:50
 */
@Service
public class TerminalSettingServiceImpl extends BaseService implements TerminalSettingService {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordService passwordService;
    @Override
    public SupplyResult login(String uname, String pwd) {
        TbUserInfo userInfo = userService.findUserByUsername(uname);

        if(userInfo == null){
            return SupplyResult.build(404,"账户或密码错误-001");
        }else if(UserState.DISABLE.getType() == userInfo.getState()){
            return SupplyResult.build(404,"账户锁定");
        }
        String encPwd = passwordService.doGetPassword(pwd,userInfo.getMixSalt());
        if(!userInfo.getPassword().equals(encPwd)){
            return SupplyResult.build(405,"账户或密码错误-002");
        }
        UserInfoReturnBody returnBody = new UserInfoReturnBody();
        returnBody.setUid(userInfo.getUid());
        returnBody.setUsername(userInfo.getUsername());
        returnBody.setName(userInfo.getName());
        String branchno = OrgCache.getInstance().getOrgInfobyId(userInfo.getOrgid()).getBranchno();
        returnBody.setBranchno(branchno);
        return SupplyResult.ok(returnBody);
    }
}
