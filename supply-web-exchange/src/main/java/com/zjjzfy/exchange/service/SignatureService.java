package com.zjjzfy.exchange.service;

import com.zjjzfy.common.entity.SupplyResult;

import java.io.FileNotFoundException;

/**
 * 
 * @author      jackshenonly
 * @description 类说明
 * @date        2020-03-30 09:17
 */
public interface SignatureService {
    SupplyResult uploadSignature(String imgStr,String billno) throws Exception;
}
