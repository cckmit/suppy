package com.zjjzfy.shiro.service.impl;

import com.zjjzfy.common.utils.Md5;
import com.zjjzfy.shiro.config.ShiroProperties;
import com.zjjzfy.shiro.service.PasswordService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jackshenonly
 * @description 密码生成类
 * @date 2019-03-15 00:18
 */
@Service
public class PasswordServiceImpl implements PasswordService {


    @Autowired
    private ShiroProperties shiroProperties;

    /**
     * @param password 明文密码
     * @param mixSalt  混合盐 = uername + salt
     * @return
     * @description 生成密码存储到mysql
     */
    @Override
    public String doGetPassword(String password, String mixSalt) {

        String pass = "";
//        //供货
        ByteSource iSalt = ByteSource.Util.bytes(mixSalt);
        Object md = new SimpleHash(shiroProperties.getHashAlgorithmName(),
                password,
                iSalt,
                shiroProperties.getHashIterations());
        pass = md.toString();

        //永康
//        pass = Md5.getMD5(password + mixSalt, false);
        System.out.println("--------------------" + pass);

        return pass;
    }
}
