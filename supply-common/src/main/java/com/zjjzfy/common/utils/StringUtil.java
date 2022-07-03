package com.zjjzfy.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StringUtil {

    /**
     * 获取随机字符串
     *
     * @return
     */
    public static String getRandomString(int length) {
        if (length >= 32) {
            length = 32;
        }
        String str = UUID.randomUUID().toString().replace("-", "");
        return str.substring(0, length);
    }

    public static String[] stringInArrays(String fields) {
        String[] field = null;
        if (fields != null && fields.length() > 0) {
            field = fields.split(",");
            String first = field[0];
            field[0] = first.substring(first.indexOf("[") + 1);
            String last = field[field.length - 1];
            field[field.length - 1] = last.substring(0, last.indexOf("]"));


        }
        return field;
    }

    public static List<Integer> strArrayToIntArray(String[] a){
        List<Integer> b = new ArrayList<>(a.length);
        for (int i = 0; i < a.length; i++) {
            b.add(Integer.parseInt(a[i]));
        }

        return b;
    }
}