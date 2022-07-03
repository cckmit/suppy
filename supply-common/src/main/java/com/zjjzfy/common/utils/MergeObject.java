package com.zjjzfy.common.utils;

import java.lang.reflect.Field;

/**
 * 将一个对象中的不为空的值复制到另一个对象中
 * author ：ZT
 */
public class MergeObject {

    public static  <T> T merge(T origin, T destination) {
        if (origin == null || destination == null)
            return null;
        if (!origin.getClass().equals(destination.getClass()))
            return null;

        Field[] fields = origin.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                String name = fields[i].getName();
                if (name.equals("serialVersionUID")){
                    continue;
                }
                fields[i].setAccessible(true);
                Object value = fields[i].get(origin);
                if (null != value) {
                    fields[i].set(destination, value);
                }
                fields[i].setAccessible(false);
            } catch (Exception e) {
                return null;
            }
        }

        return destination;
    }

}
