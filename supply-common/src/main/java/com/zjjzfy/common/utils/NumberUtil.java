package com.zjjzfy.common.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {
    private static final String numbers = "1234567890";

    /**
     * 校验电话号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobile(String mobiles) {
        boolean flag = false;
        try {
            String regExp = "^((13[0-9])|(15[^4])|(18[0,1,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
            Pattern p = Pattern.compile(regExp);
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 获取固定长度随机数字串
     *
     * @param length
     * @return
     */
    public static String getRandomNumber(int length) {
        //由Random生成随机数
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        //长度为几就循环几次
        for (int i = 0; i < length; ++i) {
            //产生0-61的数字
            int number = random.nextInt(length);
            //将产生的数字通过length次承载到sb中
            sb.append(numbers.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

    /**
     * 校验密码
     * 密码长度为8到20位,必须包含字母和数字，字母区分大小写
     *
     * @param password
     * @return
     */
    public static boolean checkPassword(String password) {
        Pattern Password_Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,20})$");
        Matcher matcher = Password_Pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}