package com.zjjzfy.exchange.api;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/5/7
 * Time: 14:14
 */
public class BusinessFactory {
    public static Business build(String region){
        if("TAIZHOU".equals(region)){
            return new TaiZhou();
        }
        return null;
    }
}
