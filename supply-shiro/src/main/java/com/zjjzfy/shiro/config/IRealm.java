package com.zjjzfy.shiro.config;


import com.zjjzfy.common.config.ShiroPassType;
import com.zjjzfy.interfaces.RoleMapper;
import com.zjjzfy.interfaces.TbUserInfoMapper;
import com.zjjzfy.interfaces.UserInfoMapper;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.pojo.TbUserInfoExample;
import com.zjjzfy.shiro.token.NoPasswordToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jackshenonly
 * @description realm
 * @date 2019-03-13 20:38
 */

@Slf4j
@Component
public class IRealm extends AuthorizingRealm {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private TbUserInfoMapper tbUserInfoMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("权限配置-->IRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        TbUserInfo userInfo = (TbUserInfo) principalCollection.getPrimaryPrincipal();
        List<String> roles = roleMapper.findRolesByUid(userInfo.getUid());
        List<String> permissions = roleMapper.findPermissionsByRoles(roles);
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(permissions);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("IRealm.doGetAuthenticationInfo()");

        NoPasswordToken token = (NoPasswordToken) authenticationToken;
        TbUserInfo userInfo = null;
        String username = token.getUsername();
//        String username = token.getUsername();
        if (token.getLoginType().equals(ShiroPassType.PASSWORD.getType())) {
            //用户名密码登录
            userInfo = userInfoMapper.findUserByUsername(username);
        } else {
            //免密登录
            TbUserInfoExample example = new TbUserInfoExample();
            example.createCriteria().andColumn1EqualTo(username);
            List<TbUserInfo> tbUserInfos = tbUserInfoMapper.selectByExample(example);
            if (tbUserInfos != null && tbUserInfos.size() > 0) {
                userInfo = tbUserInfos.get(0);
            }
        }
        log.info("用户开始登陆--->>userInfo=" + userInfo);
        if (userInfo == null) {
            return null;
        }

        SecurityUtils.getSubject().getPrincipal();

        if (!"1".equals(userInfo.getState().toString())) {
            throw new AuthenticationException("账户状态被锁定。");
        }

//        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, "ok", this.getClass().getSimpleName());
//        SimpleAuthenticationInfo authenticationInfo =
//                new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), ByteSource.Util.bytes(userInfo.getMixSalt()), getName());

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), this.getName());
        if (userInfo.getSalt() != null) {
            authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userInfo.getMixSalt()));
        }
        //永康供货
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userInfo.getMixSalt()));
        return authenticationInfo;
    }
}
