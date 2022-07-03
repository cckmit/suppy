package com.zjjzfy.exchange.utils;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/5/6
 * Time: 15:50
 */
public class BillnoHelper {

    public static final int BILLNO_LEN = 19;

    public static String build(String prefix, Date date,String other){

        return prefix+date.getTime()+other.substring(0,4);
    }
}
