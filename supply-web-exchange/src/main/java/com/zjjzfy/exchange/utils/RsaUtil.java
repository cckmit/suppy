package com.zjjzfy.exchange.utils;

import com.zjjzfy.exchange.exception.RsaCheckException;
import com.zjjzfy.exchange.pojo.Param;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @author      jackshenonly
 * @description 类说明 适用于本项目的简单封装
 * @date        2019-12-15 09:39
 */
@Slf4j
public class RsaUtil {

    /**
     * 用公钥加密，返回加密后的Param
     * @param param
     * @param publicKey
     * @return
     */
    public static Param encrypt(Param param, String publicKey) throws RsaCheckException {
        try {
            param.setCs(RsaCode.encryptByPublicKey(param.getCs(),publicKey));
        }catch (Exception e){
            String msg = "公钥加密异常";
            log.error(msg,e);
            throw new RsaCheckException(msg,e);
        }
        return param;
    }

    /**
     * 用私钥解密，并返回解密后的 param
     * @param param
     * @param privateKey
     * @return
     */
    public static Param decrypt(Param param,String privateKey) throws RsaCheckException {
        try {
            param.setCs(RsaCode.decryptByPrivateKey(param.getCs(),privateKey));
        }catch (Exception e){
            String msg = "用私钥解密异常";
            log.error(msg,e);
            throw new RsaCheckException(msg,e);
        }
        return param;
    }

    /**
     * 用私钥加签，并返回加签后的 Param
     * @param param
     * @param privateKey
     * @return
     */
    public static Param sign(Param param, String privateKey) throws RsaCheckException {

        try {
            param.setJym(RsaCode.sign(param.getCs(),privateKey));
        }catch (Exception e){
            String msg = "用私钥加签异常";
            log.error(msg,e);
            throw new RsaCheckException(msg,e);
        }
        return param;
    }

    /**
     * 用公钥验签，返回 true or false
     * @param param
     * @param publicKey
     * @return
     */
    public static boolean verify(Param param,String publicKey) throws RsaCheckException {
        boolean t = false;
        try {
            t = RsaCode.verify(param.getCs(),publicKey,param.getJym());
        }catch (Exception e){
            String msg = "用公钥验签异常";
            log.error(msg,e);
            throw new RsaCheckException(msg,e);
        }
        return t;
    }

}
