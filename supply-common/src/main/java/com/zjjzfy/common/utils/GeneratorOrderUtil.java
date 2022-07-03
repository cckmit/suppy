package com.zjjzfy.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: hsmz
 * @date: 2019/3/13 下午3:02
 */
public class GeneratorOrderUtil {

    public static String getOrderNumber(){
        String orderNumber = (System.currentTimeMillis()+"").substring(1)+ (System.nanoTime()+"").substring(7,10);
        return orderNumber;
    }

    public static void main(String[] args) {
        // 测试多线程调用订单号生成工具
        try {
            for (int i = 0; i < 200; i++) {
                Thread t1 = new Thread(new Runnable() {
                    public void run() {
                        GeneratorOrderUtil.getOrderNumber();
                    }
                }, "at" + i);
                t1.start();

                Thread t2 = new Thread(new Runnable() {
                    public void run() {
                        GeneratorOrderUtil makeOrder = new GeneratorOrderUtil();
                        GeneratorOrderUtil.getOrderNumber();
                    }
                }, "bt" + i);
                t2.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}