package com.zjjzfy.exchange.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zjjzfy.common.entity.SupplyResult;


/**
 * 
 * @author      jackshenonly
 * @description 类说明
 * @date        2020-03-30 09:17
 */
public interface ApkVersionService {
    /**
     *
     * @param productFlavor 设备型号，不同设备不同的安装包。
     * @return
     * @throws Exception
     */
    SupplyResult checkVersion(String productFlavor) throws Exception;

    /**
     * 检测设备型号是否在已知列表内  true 在，false 不在
     * @param productFlavor
     * @return
     */
    Boolean checkProductFlavor(String productFlavor);
}
