package com.zjjzfy.product.service.impl;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbProductImg;

public interface ProductImgService {

    int insert(TbProductImg productImg);

    int deleteById(int id);

    SupplyResult selectProductImg(Integer id, String type);
}
