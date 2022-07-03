package com.zjjzfy.shiro.service.impl;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.common.utils.NumberUtil;
import com.zjjzfy.interfaces.TbUserInfoMapper;
import com.zjjzfy.pojo.TbUserInfo;
import com.zjjzfy.shiro.service.PasswordService;
import com.zjjzfy.shiro.service.ShiroService;
import com.zjjzfy.shiro.token.NoPasswordToken;
import com.zjjzfy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private TbUserInfoMapper tbUserInfoMapper;

    @Override
    public SupplyResult login(String username, String password) {
        try {
            NoPasswordToken token = new NoPasswordToken(username, password);
//            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException e1) {

            log.info("UnknownAccountException -- > 账号不存在：");
            return SupplyResult.build(300, "账号不存在");
        } catch (IncorrectCredentialsException e2) {

            log.info("IncorrectCredentialsException -- > 密码不正确：");
            return SupplyResult.build(300, "密码不正确");
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
            log.info("else -- >" + ae.getMessage());
            return SupplyResult.build(300, ae.getMessage());
        } catch (Exception e3) {

            log.info("else -- >" + e3.getMessage());
            e3.printStackTrace();
            return SupplyResult.build(300, "登录异常");
        }
        return SupplyResult.ok();
    }

    @Override
    public SupplyResult densityFreeLogin(String openId) {
        try {
            NoPasswordToken token = new NoPasswordToken(openId);
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException e1) {

            log.info("UnknownAccountException -- > 账号不存在：");
            return SupplyResult.build(300, "账号不存在");
        } catch (IncorrectCredentialsException e2) {

            log.info("IncorrectCredentialsException -- > 密码不正确：");
            return SupplyResult.build(300, "密码不正确");
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
            log.info("else -- >" + ae.getMessage());
            return SupplyResult.build(300, ae.getMessage());
        } catch (Exception e3) {

            log.info("else -- >" + e3.getMessage());
            e3.printStackTrace();
            return SupplyResult.build(300, "登录异常");
        }
        return SupplyResult.ok();
    }

    @Override
    public Boolean hasPermit(String permitName) {
        Subject current = SecurityUtils.getSubject();
        return current.isPermitted(permitName);
    }

    @Override
    public SupplyResult changePwd(String oldPwd, String newPwd) {
        TbUserInfo currentUser = userService.getCurrentTbUserInfo();
        String oldPwdSalt = passwordService.doGetPassword(oldPwd, currentUser.getUsername() + currentUser.getSalt());
        if (!oldPwdSalt.equals(currentUser.getPassword())) {
            return SupplyResult.build(300, "原密码不正确");
        }

        String password = passwordService.doGetPassword(newPwd, currentUser.getUsername() + currentUser.getSalt());

        currentUser.setPassword(password);
        tbUserInfoMapper.updateByPrimaryKeySelective(currentUser);
        return SupplyResult.ok();
    }

    @Override
    public SupplyResult resetPwd(Integer uid) {
        TbUserInfo currentUser = tbUserInfoMapper.selectByPrimaryKey(uid);
        //String resetPwd = NumberUtil.getRandomNumber(6);
        String resetPwd = "000000";
        String password = passwordService.doGetPassword(resetPwd, currentUser.getUsername() + currentUser.getSalt());

        currentUser.setPassword(password);
        tbUserInfoMapper.updateByPrimaryKeySelective(currentUser);
        return SupplyResult.ok(resetPwd);
    }
}