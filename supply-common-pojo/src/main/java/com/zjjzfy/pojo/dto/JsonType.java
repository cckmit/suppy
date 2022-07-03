package com.zjjzfy.pojo.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackshenonly on 2016/7/25.
 */
public class JsonType {
    private List<CartDto> list = new ArrayList<CartDto>();

    public List<CartDto> getList() {
        return list;
    }

    public void setList(List<CartDto> list) {
        this.list = list;
    }
}
