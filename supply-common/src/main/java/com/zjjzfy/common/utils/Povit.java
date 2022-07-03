package com.zjjzfy.common.utils;

import com.zjjzfy.pojo.TbRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: hsmz
 * @date: 2019/4/2 下午5:07
 */
public class Povit {

    /**
     * 将扁平化数据转化成列式数据
     *
     * @param rs         需要转化的数据
     * @param hCaption   横向扩展字段
     * @param hl         横向扩张列数据
     * @param vCaption   纵向扩展字段
     * @param vl         纵向扩展数据
     * @param valCaption 度量字段
     * @return 列化后的列表
     */
    public static List<HashMap> povit(List<HashMap> rs, String hCaption, List<String> hl, String vCaption, List<String> vl, String valCaption) {
        List<HashMap> r = new ArrayList<HashMap>();

        HashMap<String, Integer> item = new HashMap<String, Integer>();
        for (int j = 0; j < hl.size(); j++) {
            item.put(hl.get(j), 0);
        }

        for (int i = 0; i < vl.size(); i++) {
            HashMap o = (HashMap) item.clone();
            o.put("name", vl.get(i));
            r.add(o);
        }

        for (int f = 0; f < rs.size(); f++) {
            HashMap rv = (HashMap) rs.get(f);
            for (int h = 0; h < r.size(); h++) {
                if (r.get(h).get("name").equals(rv.get(vCaption))) {
                    r.get(h).put(rv.get(hCaption), rv.get(valCaption));
                }
            }
        }
        return r;
    }

    /**
     * 将扁平化数据转化成列式数据
     *
     * @param rs 需要转化的数据
     * @param hl 横向扩张列数据
     * @param vl 纵向扩展数据
     * @return 列化后的列表
     */
    public static List<HashMap> povit(List<TbRepository> rs, List<String> hl, List<String> vl) {
        List<HashMap> r = new ArrayList<HashMap>();

        HashMap<String, Integer> item = new HashMap<String, Integer>();
        for (int j = 0; j < hl.size(); j++) {
            item.put(hl.get(j), 0);
        }
        for (int i = 0; i < vl.size(); i++) {
            HashMap o = (HashMap) item.clone();
            o.put("name", vl.get(i));
            r.add(o);
        }

        for (int f = 0; f < rs.size(); f++) {
            TbRepository rv = (TbRepository) rs.get(f);
            for (int h = 0; h < r.size(); h++) {
                if (r.get(h).get("name").equals(rv.getSupplierId())) {
//                    r.get(h).put(rv.getGiftname(),rv.getGiftcount());

                }
            }
        }
        return r;
    }
}
