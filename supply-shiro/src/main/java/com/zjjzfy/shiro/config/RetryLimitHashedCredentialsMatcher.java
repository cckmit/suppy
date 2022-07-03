package com.zjjzfy.shiro.config;


import com.zjjzfy.common.config.ShiroPassType;
import com.zjjzfy.shiro.token.NoPasswordToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.stereotype.Component;

@Component
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
//    @Override
//    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//        String username = (String) token.getPrincipal();
//        UsernamePasswordToken autoken = (UsernamePasswordToken) token;
//        SimpleAuthenticationInfo sinfo = (SimpleAuthenticationInfo) info;
//        String pwdhash = new String(sinfo.getCredentialsSalt().getBytes());
////这个CipherUtil.generatePassword是自定义的static方法，用于生成加密后的密码
//        String inputCredential = Md5.getMD5(String.valueOf(autoken.getPassword()) + pwdhash, false);
////生成的加密是大写，但mysql不区分大小写，对比会失败
//        String accountCredentials = String.valueOf(getCredentials(info));
//        boolean match = equals(inputCredential, accountCredentials);
//        if (match) {
//            //passwordRetryCache.remove(username);
//        }
//        return match;
//    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        NoPasswordToken noPasswordToken = (NoPasswordToken) token;
        //如果是免密，就不需要核对密码了
        if (noPasswordToken.getLoginType().equals(ShiroPassType.NOPASSWD.getType())) {
            return true;
        }
        return super.doCredentialsMatch(token, info);
    }
}